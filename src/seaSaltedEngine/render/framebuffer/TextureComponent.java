package seaSaltedEngine.render.framebuffer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;
import org.lwjgl.system.MemoryUtil;

public class TextureComponent extends FboComponent {

	private final int format;
	  
	  private final boolean nearestFiltering;
	  
	  private final boolean clampEdges;
	  
	  public TextureComponent(int format) {
	    this.format = format;
	    this.nearestFiltering = false;
	    this.clampEdges = false;
	  }
	  
	  public TextureComponent(int format, boolean nearestFiltering, boolean clampEdges) {
	    this.format = format;
	    this.nearestFiltering = nearestFiltering;
	    this.clampEdges = clampEdges;
	  }
	  
	  public void delete() {
	    GL11.glDeleteTextures(getBufferId());
	  }
	  
	  public void init(int attachment, int width, int height, int samples) {
	    int texture = GL11.glGenTextures();
	    setBufferId(texture);
	    GL11.glBindTexture(3553, texture);
	    indicateStorageType(width, height);
	    setTextureParams();
	    GL30.glFramebufferTexture2D(36160, attachment, 3553, texture, 0);
	  }
	  
	  private void indicateStorageType(int width, int height) {
	    if (isDepthAttachment()) {
	      GL11.glTexImage2D(3553, 0, this.format, width, height, 0, 6402, 5126, MemoryUtil.NULL);
	    } else {
	      GL11.glTexImage2D(3553, 0, this.format, width, height, 0, 6408, 5121, MemoryUtil.NULL);
	    } 
	  }
	  
	  private void setTextureParams() {
	    int filterType = this.nearestFiltering ? 9728 : 9729;
	    GL11.glTexParameteri(3553, 10240, filterType);
	    GL11.glTexParameteri(3553, 10241, filterType);
	    int wrapType = this.clampEdges ? 33071 : 10497;
	    GL11.glTexParameteri(3553, 10242, wrapType);
	    GL11.glTexParameteri(3553, 10243, wrapType);
	  }

}
