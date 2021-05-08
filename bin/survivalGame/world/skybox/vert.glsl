#version 400

in vec3 in_position;
out vec4 out_position;

uniform mat4 projectionMatrix;
uniform mat4 viewMatrix;
uniform mat4 transformationMatrix;

void main(void){
	
	vec4 position =  transformationMatrix * vec4(in_position, 1.0);
	out_position = position;
	gl_Position =  projectionMatrix * viewMatrix * position;
	
}
