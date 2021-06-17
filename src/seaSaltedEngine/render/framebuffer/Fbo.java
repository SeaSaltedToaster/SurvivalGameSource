package seaSaltedEngine.render.framebuffer;

import java.util.Map;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;
import org.lwjgl.system.MemoryUtil;

import seaSaltedEngine.render.display.WindowManager;

public class Fbo {

	private int fboId;
	
	private final int width;
	private final int height;
	
	private int colourTexture;
	private int depthTexture;
	private int depthBuffer;
	private int colourBuffer;
	
	private final Map<Integer, FboComponent> colourAttachments;
	private final FboComponent depthAttachment;
	
	protected Fbo(int fboId, int width, int height, Map<Integer, FboComponent> attachments, FboComponent depthAttachment) {
	  this.fboId = fboId;
	  this.width = width;
	  this.height = height;
	  this.colourAttachments = attachments;
	  this.depthAttachment = depthAttachment;
	}
	
	public void blitToScreen(int colourIndex) {
	  GL30.glBindFramebuffer(36009, 0);
	  GL11.glDrawBuffer(1029);
	  GL30.glBindFramebuffer(36008, this.fboId);
	  GL11.glReadBuffer(36064 + colourIndex);
	  GL30.glBlitFramebuffer(0, 0, this.width, this.height, 0, 0, (int) WindowManager.getWindowSizeX(), (int) WindowManager.getWindowSizeY(), 
	      16384, 9728);
	  GL30.glBindFramebuffer(36160, 0);
	}
	
	public void blitToFbo(int srcColourIndex, Fbo target, int targetColourIndex) {
	  GL30.glBindFramebuffer(36009, target.fboId);
	  GL11.glDrawBuffer(36064 + targetColourIndex);
	  GL30.glBindFramebuffer(36008, this.fboId);
	  GL11.glReadBuffer(36064 + srcColourIndex);
	  int bufferBit = (this.depthAttachment != null && target.depthAttachment != null) ? 
	    16640 : 16384;
	  GL30.glBlitFramebuffer(0, 0, this.width, this.height, 0, 0, target.width, target.height, bufferBit, 9728);
	  GL30.glBindFramebuffer(36160, 0);
	}
	
	public int getColourBuffer(int colourIndex) {
	  return ((FboComponent)this.colourAttachments.get(Integer.valueOf(colourIndex))).getBufferId();
	}
	
	public int getDepthBuffer() {
	  return this.depthAttachment.getBufferId();
	}
	
	public boolean isComplete() {
	  GL30.glBindFramebuffer(36160, this.fboId);
	  boolean complete = (GL30.glCheckFramebufferStatus(36160) == 36053);
	  GL30.glBindFramebuffer(36160, 0);
	  return complete;
	}
	
	public void bindForRender(int colourIndex) {
	  GL30.glBindFramebuffer(36009, this.fboId);
	  GL11.glDrawBuffer(36064 + colourIndex);
	  GL11.glViewport(0, 0, this.width, this.height);
	}
	
	public void cleanUp() {
	    GL30.glDeleteFramebuffers(this.fboId);
	    GL11.glDeleteTextures(this.colourTexture);
	    GL11.glDeleteTextures(this.depthTexture);
	    GL30.glDeleteRenderbuffers(this.depthBuffer);
	    GL30.glDeleteRenderbuffers(this.colourBuffer);
	  }
	  
	  public void bindFrameBuffer() {
	    GL11.glBindTexture(3553, 0);
	    GL30.glBindFramebuffer(36009, this.fboId);
	    GL11.glViewport(0, 0, this.width, this.height);
	  }
	  

	public void bindForReading(int colourIndex) {
	  GL30.glBindFramebuffer(36008, this.fboId);
	  GL11.glReadBuffer(36064 + colourIndex);
	}
	
	public void unbindAfterReading() {
	  GL30.glBindFramebuffer(36008, 0);
	}
	
	public void unbindAfterRender() {
	  GL30.glBindFramebuffer(36009, 0);
	  GL11.glDrawBuffer(1029);
	  GL11.glViewport(0, 0, (int) WindowManager.getWindowSizeX(), (int) WindowManager.getWindowSizeY());
	}
	
	public void createTextureAttachment(boolean linear, boolean clamp) {
	    this.colourTexture = GL11.glGenTextures();
	    GL11.glBindTexture(3553, this.colourTexture);
	    GL11.glTexImage2D(3553, 0, 32856, this.width, this.height, 0, 6408, 5121, MemoryUtil.NULL);
	    GL11.glTexParameteri(3553, 10240, linear ? 9729 : 9728);
	    GL11.glTexParameteri(3553, 10241, linear ? 9729 : 9728);
	    GL11.glTexParameteri(3553, 10242, 
	        clamp ? 33071 : 10497);
	    GL11.glTexParameteri(3553, 10243, 
	        clamp ? 33071 : 10497);
	    GL30.glFramebufferTexture2D(36160, 36064, 3553, this.colourTexture, 
	        0);
	  }
	
	public int getColourTexture() {
	    return this.colourTexture;
	  }
	
	public void delete() {
	  for (FboComponent attachment : this.colourAttachments.values())
	    attachment.delete(); 
	  if (this.depthAttachment != null)
	    this.depthAttachment.delete(); 
	}
	
	public static FboBuilder newFbo(int width, int height) {
	  return new FboBuilder(width, height, 0);
	}

}
