const vertShader = `
uniform float max_z;
uniform float min_z;
varying vec3 norm;
varying vec3 pos;

void main() {

  norm = normal;
  pos = position.xyz;

  gl_Position = projectionMatrix *
                modelViewMatrix *
                vec4(position, 1.0);
}
`

const fragShader = `
varying vec3 norm;
varying vec3 pos;
uniform float max_z;
uniform float min_z;

vec3 getHeightColour(in float height) {
   vec3 colours[16];
   colours[0] = vec3(1.0, 1.0, 1.0);
   colours[1] = vec3(0.5, 0.0, 0.0);
   colours[2] = vec3(1.0, 0.0, 0.0);
   colours[3] = vec3(1.0, 0.5, 0.0);
   colours[4] = vec3(1.0, 1.0, 0.0);
   colours[5] = vec3(1.0, 1.0, 0.5);
   colours[6] = vec3(1.0, 1.0, 1.0);
   colours[7] = vec3(0.5, 0.5, 0.5);
   colours[8] = vec3(1.0, 0.41, 0.71);

   vec3 ret = colours[0];
   
   if (height < 50.0) {
       ret = colours[8];
   }
   if (height < 40.0) {
       ret = colours[7];
   }
   if (height < 30.0) {
       ret = colours[6];
   }
   if (height < 25.0) {
       ret = colours[5];
   }
   if (height < 20.0) {
       ret = colours[4];
   }
   if (height < 15.0) {
       ret = colours[3];
   }
   if (height < 10.0) {
       ret = colours[2];
   }
   if (height < 5.0) {
       ret =  colours[1];
   }   
   return ret;
}

void main()
{
    vec3 light = vec3(0.5, 0.2, 1.0);

    // ensure it's normalized
    light = normalize(light);

    // calculate the dot product of
    // the light to the vertex normal
    float dProd = max(0.0,
                    dot(norm, light));

    gl_FragColor = vec4(getHeightColour(pos.z - min_z) * dProd, 1.0);

}
`

module.exports = { vertShader, fragShader }