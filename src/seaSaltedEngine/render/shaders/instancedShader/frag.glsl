#version 400 core

in vec3 pass_color;
in vec3 surfaceNormal;

out vec4 outColor;

void main(void) {

	outColor = vec4(pass_color.x,pass_color.y,pass_color.z,1);
//	outColor = vec4(0,1,0,1);

}
