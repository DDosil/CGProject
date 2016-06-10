uniform mat4 worldMat;
uniform mat4 LightVPMat;

attribute vec3 position;

void main() {
   gl_Position = lightVPMat * worldMat * vec4(position, 1.0);
}