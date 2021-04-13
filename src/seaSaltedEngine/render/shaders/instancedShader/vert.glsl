#version 400 core

in vec3 in_position;
in vec3 in_vertexColor;
in vec3 in_normal;

out vec3 pass_color;

uniform mat4 transformationMatrix[100];
uniform mat4 viewMatrix;
uniform mat4 projectionMatrix;

void main(void) {
	
	mat4 transform = transformationMatrix[gl_InstanceID];
	vec4 worldPosition = transform * vec4(in_position.x, in_position.y, in_position.z, 1.0);
	vec4 positionRelativeToCamera = viewMatrix * worldPosition;
	gl_Position =  projectionMatrix * positionRelativeToCamera;

	pass_color = in_vertexColor;
	

}

