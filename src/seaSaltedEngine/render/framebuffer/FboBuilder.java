package seaSaltedEngine.render.framebuffer;

import java.util.HashMap;
import java.util.Map;

import org.lwjgl.opengl.GL30;

public class FboBuilder {

	private final int width;
	  
	private final int height;
	
	private final int samples;
	
	private Map<Integer, FboComponent> colourAttachments = new HashMap<>();
	
	private FboComponent depthAttachment;
	
	protected FboBuilder(int width, int height, int samples) {
	  this.width = width;
	  this.height = height;
	  this.samples = samples;
	}
	
	public FboBuilder addColourAttachment(int index, FboComponent attachment) {
	  this.colourAttachments.put(Integer.valueOf(index), attachment);
	  return this;
	}
	
	public FboBuilder addDepthAttachment(FboComponent attachment) {
	  this.depthAttachment = attachment;
	  attachment.setAsDepthAttachment();
	  return this;
	}
	
	public Fbo init() {
		int fboId = createFbo();
		createColourAttachments();
	  	createDepthAttachment();
	  	return new Fbo(fboId, this.width, this.height, this.colourAttachments, this.depthAttachment);
	}
	
	private int createFbo() {
	  int fboId = GL30.glGenFramebuffers();
	  GL30.glBindFramebuffer(36160, fboId);
	  return fboId;
	}
	
	private void createColourAttachments() {
		for (Map.Entry<Integer, FboComponent> entry : this.colourAttachments.entrySet()) {
			FboComponent attachment = entry.getValue();
			int attachmentId = 36064 + ((Integer)entry.getKey()).intValue();
	    	attachment.init(attachmentId, this.width, this.height, this.samples);
	  	} 
	}
	
	private void createDepthAttachment() {
		if (this.depthAttachment != null)
			this.depthAttachment.init(36096, this.width, this.height, this.samples); 
	}

}
