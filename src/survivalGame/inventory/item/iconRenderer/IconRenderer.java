package survivalGame.inventory.item.iconRenderer;

import seaSaltedEngine.Engine;
import seaSaltedEngine.basic.objects.Color;
import seaSaltedEngine.basic.objects.Transform;
import seaSaltedEngine.render.framebuffer.Fbo;
import seaSaltedEngine.render.framebuffer.FboBuilder;
import seaSaltedEngine.render.model.Mesh;
import seaSaltedEngine.render.model.texture.Texture;
import seaSaltedEngine.tools.OpenGL;
import seaSaltedEngine.tools.math.MathUtils;
import seaSaltedEngine.tools.math.Matrix4f;

public class IconRenderer {

	private static IconShader shader = new IconShader();
	private static Color iconBackground = new Color(1f,0f,0f);
	
	private static Fbo fbo;
	private static Fbo result;
	
	public static void init() {
		IconRenderer.shader = new IconShader();
	}
	
	public static Texture renderIcon(Mesh mesh) {
		fbo = createIconFbo();
		fbo.bindFrameBuffer();
		result = createIconFbo();
		beginRendering();
		mesh.getMeshVao().render();
		result.bindFrameBuffer();
		result.createTextureAttachment(true, true);
		endRendering();
		return new Texture(result.getColourTexture());
	}

	private static Fbo createIconFbo() {
		FboBuilder fboBuilder = Fbo.newFbo(256, 256);
		Fbo fbo = fboBuilder.init();
		return fbo;
	}
	
	private static void beginRendering() {
		shader.start();
		shader.getViewMatrix().loadMatrix(MathUtils.createViewMatrix(Engine.getCamera()));
		shader.getProjectionMatrix().loadMatrix(Engine.getRenderer().getProjectionMatrix());
		shader.getTransformationMatrix().loadMatrix(getTransformation(Transform.Default));
		shader.getLightPosition().loadVec3(Engine.getCamera().getPosition());
		shader.getLightAttenuation().loadVec3(1, 1, 1);
		
		OpenGL.clearColor(iconBackground.toVector(),1f);
		OpenGL.enableCull();
		OpenGL.setDepthTest(true);
	}
	
	private static Matrix4f getTransformation(Transform transform) {
		Matrix4f transformation = MathUtils.createTransformationMatrix(transform.getPosition(), transform.getRx(), transform.getRy(), transform.getRz(), transform.getScale());
		return transformation;
	}
	
	private static void endRendering() {
		shader.stop();
		fbo.unbindAfterRender();
		OpenGL.disableCull();
	}

	public IconShader getShader() {
		return shader;
	}

}
