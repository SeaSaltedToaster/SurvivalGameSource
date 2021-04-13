#version 400 core

in vec3 in_position;
in vec3 in_vertexColor;
in vec3 in_normal;

out vec3 pass_color;

uniform mat4 transformationMatrix;
uniform mat4 viewMatrix;
uniform mat4 projectionMatrix;

void main(void) {

	vec4 worldPosition = transformationMatrix * vec4(in_position.x, in_position.y, in_position.z, 1.0);
	vec4 positionRelativeToCamera = viewMatrix * worldPosition;
	gl_Position =  projectionMatrix * positionRelativeToCamera;
	
	pass_color = in_vertexColor - vec3(0,in_position.y/20,0);
	
}
