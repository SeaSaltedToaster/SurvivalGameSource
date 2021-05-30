package seaSaltedEngine.guis.text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import seaSaltedEngine.Engine;
import seaSaltedEngine.guis.text.font.FontType;
import seaSaltedEngine.guis.text.renderer.FontRenderer;
import seaSaltedEngine.guis.text.renderer.TextMeshData;
import seaSaltedEngine.render.model.Vao;

public class TextMaster {
	
    private static Map<FontType, List<Text>> texts = new HashMap<FontType, List<Text>>();
    private static List<Text> textMap = new ArrayList<Text>();
    
    private static FontRenderer renderer;
     
    public static void init() {
        renderer = new FontRenderer();
    }
     
    public static void render(){
        renderer.render(texts);
    }
    
    public static void clear() {
    	texts.clear();
    	textMap.clear();
    }
     
    public static void loadText(Text text){
        FontType font = text.getFont();
        TextMeshData data = font.loadText(text);
        Vao vao = Engine.getRenderer().getLoader().loadToVAO(data.getVertexPositions(), data.getTextureCoords());
        text.setTextMeshVao(vao);
        text.setVertexCount(data.getVertexCount());
        List<Text> textBatch = texts.get(font);
        if(textBatch == null){
            textBatch = new ArrayList<Text>();
            texts.put(font, textBatch);
        }
        textBatch.add(text);
        textMap.add(text);
    }
     
    @SuppressWarnings("unlikely-arg-type")
	public static void removeText(Text text){
        List<Text> textBatch = texts.get(text.getFont());
        textBatch.remove(text);
        if(textBatch.isEmpty()){
            texts.remove(texts.get(text.getFont()));
        }
        textMap.remove(text);
    }
     
    public static void cleanUp(){
        renderer.cleanUp();
    }

	public static Map<FontType, List<Text>> getTexts() {
		return texts;
	}

	public static List<Text> getTextz() {
		return textMap;
	}

}
