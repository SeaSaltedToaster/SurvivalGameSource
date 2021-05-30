package seaSaltedEngine.guis.text.renderer;

import seaSaltedEngine.render.shaders.Shader;
import seaSaltedEngine.render.shaders.objects.UniformVec2;
import seaSaltedEngine.render.shaders.objects.UniformVec3;

public class FontShader extends Shader {

	private static final String VERTEX_FILE = "/seaSaltedEngine/guis/text/renderer/fontVertex.glsl";
    private static final String FRAGMENT_FILE = "/seaSaltedEngine/guis/text/renderer/fontFragment.glsl";
     
    private UniformVec3 color = new UniformVec3("color");
    private UniformVec2 translation = new UniformVec2("translation");
     
    public FontShader() {
        super(VERTEX_FILE, FRAGMENT_FILE, "position", "textureCoords");
        super.storeAllUniformLocations(color, translation);
    }

	public UniformVec3 getColor() {
		return color;
	}

	public UniformVec2 getTranslation() {
		return translation;
	}

}
