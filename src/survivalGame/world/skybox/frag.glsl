#version 400

in vec4 out_position;
out vec4 out_Color;

uniform float skyboxSize;

void main(void){

	vec2 uv = vec2(out_position.x,(out_position.y/(skyboxSize+(skyboxSize/90))));

	highp float pi = 3.141592653589793;
	vec4 c1 = vec4(1.0, 1.0, 1.0, 0.0); //Top Color
	vec4 c2 = vec4(1.0, 1.0, 1.0, 0.1); //Horizon Color
	vec4 c3 = vec4(0.0, 0.7, 1.0, 0.0); //Bottom Color

	highp float f = 1.0-acos(uv.y)/pi;

	float top = 0.5f;
	float middle = 0.25f;
	float bottom = 0.0f;

	if(f < bottom){
		out_Color = mix(c1,c2,uv.y);
	}
	else if(f >= bottom && f < middle){
	    out_Color = c2;
	}
	else if(f >= middle){
	    out_Color = mix(c2, c3, (f-middle)/0.5f);
	}
}
