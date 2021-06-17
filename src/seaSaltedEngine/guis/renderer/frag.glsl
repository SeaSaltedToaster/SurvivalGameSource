#version 400 core

in vec2 textureCoords;

out vec4 out_Color;

uniform sampler2D guiTexture;
uniform vec4 uiOverrideColor;
uniform float alpha;
uniform float uiWidth;
uniform float uiHeight;
uniform bool hasTexture;

float radius = 0.5f;
const float cornerSmooth = 0.55f;

float square(float val) {
    return val * val;
}

float distanceSquared(vec2 p1, vec2 p2) {
    vec2 vector = p2 - p1;
    return vector.x * vector.x + vector.y * vector.y;
}

float calcRoundedCorners() {
    if (radius <= 0.0) {
        return 1.0;
    }
    vec2 pixelPos = textureCoords * vec2(uiWidth, uiHeight);
    vec2 minCorner = vec2(radius, radius);
    vec2 maxCorner = vec2(uiWidth - radius, uiHeight - radius);

    vec2 cornerPoint = clamp(pixelPos, minCorner, maxCorner);
    float lowerBound = square(radius - cornerSmooth);
    float upperBound = square(radius + cornerSmooth);
    return smoothstep(upperBound, lowerBound, distanceSquared(pixelPos, cornerPoint));
}

void main(void){

	out_Color = uiOverrideColor;

	if(hasTexture) {
		vec4 textureColor = texture(guiTexture,textureCoords);
		if(textureColor.a < 0.25) {
			discard;
		}
		out_Color = textureColor;
	}

	out_Color.a = alpha;

}
