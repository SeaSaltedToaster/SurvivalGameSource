package seaSaltedEngine.guis.text.renderer;

import java.util.List;
import java.util.Map;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import seaSaltedEngine.guis.text.Text;
import seaSaltedEngine.guis.text.font.FontType;

public class FontRenderer {

	private FontShader shader;
	 
    public FontRenderer() {
        shader = new FontShader();
    }
     
    public void render(Map<FontType, List<Text>> texts){
        prepare();
        for(FontType font : texts.keySet()){
        	GL13.glActiveTexture(GL13.GL_TEXTURE0);
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, font.getTextureAtlas());
            for(Text text : texts.get(font)){
            	if(text.isActive())
            		renderText(text);
            }
        }
        endRendering();
    }
 
    public void cleanUp(){
        shader.cleanUp();
    }
     
    private void prepare(){
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        shader.start();
    }
     
    private void renderText(Text text){
    	text.getTextMeshVao().bind(0,1);
        shader.getColor().loadVec3(text.getColour());
        shader.getTranslation().loadVec2(text.getPosition());
        GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, text.getVertexCount());
	    text.getTextMeshVao().unbind(0,1);
    }
     
    private void endRendering(){
        shader.stop();
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
    }
	
}
