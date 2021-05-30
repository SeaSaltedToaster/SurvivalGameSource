package seaSaltedEngine.guis.text.font;

import seaSaltedEngine.guis.text.Text;
import seaSaltedEngine.guis.text.renderer.TextMeshCreator;
import seaSaltedEngine.guis.text.renderer.TextMeshData;

public class FontType {
	
	private int textureAtlas;
    private TextMeshCreator loader;

    public FontType(int textureAtlas, String fontFile) {
        this.textureAtlas = textureAtlas;
        this.loader = new TextMeshCreator(fontFile);
    }
 
    public int getTextureAtlas() {
        return textureAtlas;
    }

    public TextMeshData loadText(Text text) {
        return loader.createTextMesh(text);
    }

}
