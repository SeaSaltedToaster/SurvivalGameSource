#version 150

in vec2 pass_textureCoords;

out vec4 out_colour;

uniform vec3 color;
uniform sampler2D fontAtlas;

void main(void){

	out_colour = vec4(color, texture(fontAtlas, pass_textureCoords).a);

}
