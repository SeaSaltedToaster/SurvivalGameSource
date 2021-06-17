#version 400 core

in vec3 pass_color;
in vec3 surfaceNormal;
in vec3 toLightVector;
in vec3 toCameraVector;

out vec4 outColor;

uniform vec3 lightPosition;
uniform vec3 lightAttenuation;

const float levels = 6;
const vec3 lightColor = vec3(1.0f,1.0f,1.0f);

vec3 light() {
	float distance = min(length(toLightVector), 0);
	float attFactor = lightAttenuation.x + (lightAttenuation.y * distance) + (lightAttenuation.z * distance * distance);

	vec3 unitNormal = normalize(surfaceNormal);
	vec3 unitLightVector = normalize(toLightVector);
	float nDot1 = dot(unitNormal, unitLightVector);
	float brightness = max(nDot1,0.5);
	float level = floor(brightness*levels);
	brightness = (level / levels);
	vec3 diffuse = (brightness * lightColor) / attFactor;
	return (diffuse);
}

void main(void) {

	vec3 diffuse = light() / 1.5f;
	vec4 textureColor = vec4(pass_color.x,pass_color.y,pass_color.z,1);

	outColor = (vec4(diffuse, 1.0) * textureColor);

}
