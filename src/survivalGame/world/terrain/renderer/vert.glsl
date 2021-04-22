#version 400 core

in vec3 in_position;
in vec3 in_vertexColor;
in vec3 in_normal;

out vec3 pass_color;
out vec3 surfaceNormal;
out vec3 toLightVector;
out vec3 toCameraVector;

uniform mat4 transformationMatrix;
uniform mat4 viewMatrix;
uniform mat4 projectionMatrix;
uniform vec3 lightPosition;

void main(void) {

	vec4 worldPosition = transformationMatrix * vec4(in_position.x, in_position.y, in_position.z, 1.0);
	vec4 positionRelativeToCamera = viewMatrix * worldPosition;
	gl_Position =  projectionMatrix * positionRelativeToCamera;
	
	vec3 lightPos = lightPosition;

	surfaceNormal = (transformationMatrix * vec4(in_normal, 0.0)).xyz;
	toLightVector = lightPos - worldPosition.xyz;
	toCameraVector = (inverse(viewMatrix) * vec4(0.0,0.0,0.0,1.0)).xyz - worldPosition.xyz;

	pass_color = in_vertexColor;
	
}
