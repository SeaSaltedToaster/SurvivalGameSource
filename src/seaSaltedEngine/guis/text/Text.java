package seaSaltedEngine.guis.text;

import seaSaltedEngine.guis.core.UiComponent;
import seaSaltedEngine.guis.text.font.FontType;
import seaSaltedEngine.render.model.Vao;
import seaSaltedEngine.tools.math.Vector3f;

public class Text extends UiComponent {

	//Text and font settings
	private String textString;
    private float fontSize;
    private FontType font;
    private Vector3f colour = new Vector3f(0f, 0f, 0f);
    
    private boolean centerText = false;
    private float lineMaxSize;
    private int numberOfLines;
    
    //Text mesh settings
    private Vao textMeshVao;
    private int vertexCount;
	
	public Text(String text) {
		super(0);
		this.textString = text;
		this.fontSize = 5;
        this.font = Fonts.ARIAL;
        this.lineMaxSize = 50;
        this.centerText = true;
        
        TextMaster.loadText(this);
	}
	
	public Text(String text, float fontSize, FontType font, float maxLineLength, boolean centered)  {
		super(0);
		this.textString = text;
        this.fontSize = fontSize;
        this.font = font;
        this.lineMaxSize = maxLineLength;
        this.centerText = centered;
        
        TextMaster.loadText(this);
	}
	
	@Override
	public void updateComponent() {
		super.getAnimator().update(this);
		super.updateClick();
		super.updateSelf();
		super.updateChildren();
	}
	
	public void reset() {
		TextMaster.removeText(this);
		TextMaster.loadText(this);
	}

	public String getTextString() {
		return textString;
	}

	public float getFontSize() {
		return fontSize;
	}

	public FontType getFont() {
		return font;
	}

	public boolean isCenterText() {
		return centerText;
	}

	public float getLineMaxSize() {
		return lineMaxSize;
	}

	public int getNumberOfLines() {
		return numberOfLines;
	}

	public Vao getTextMeshVao() {
		return textMeshVao;
	}

	public int getVertexCount() {
		return vertexCount;
	}

	public void setTextString(String textString) {
		this.textString = textString;
	}

	public void setFontSize(float fontSize) {
		this.fontSize = fontSize;
	}

	public void setFont(FontType font) {
		this.font = font;
	}

	public void setCenterText(boolean centerText) {
		this.centerText = centerText;
	}

	public void setLineMaxSize(float lineMaxSize) {
		this.lineMaxSize = lineMaxSize;
	}

	public void setNumberOfLines(int numberOfLines) {
		this.numberOfLines = numberOfLines;
	}

	public void setTextMeshVao(Vao textMeshVao) {
		this.textMeshVao = textMeshVao;
	}

	public void setVertexCount(int vertexCount) {
		this.vertexCount = vertexCount;
	}

	public Vector3f getColour() {
		return colour;
	}

	public void setColour(Vector3f colour) {
		this.colour = colour;
	}

}
