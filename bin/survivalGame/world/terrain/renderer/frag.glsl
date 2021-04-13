#version 400 core

in vec3 pass_color;

out vec4 outColor;

void main(void) {

	outColor = vec4(pass_color.x,pass_color.y,pass_color.z,1);

}
