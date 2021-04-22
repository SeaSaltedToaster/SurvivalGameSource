#version 400

in vec3 in_position;

uniform mat4 projectionMatrix;
uniform mat4 viewMatrix;
uniform mat4 transformationMatrix;

void main(void){
	
	gl_Position = transformationMatrix * (projectionMatrix * (viewMatrix * vec4(in_position.x, in_position.y, in_position.z, 1.0)));
	
}
