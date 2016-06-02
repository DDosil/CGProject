uniform mat4 worldMat;
uniform mat4 viewMat;
uniform mat4 projMat;

uniform vec3 eyePos;
uniform vec3 lightPos;

attribute vec3 position;
attribute vec3 normal;
attribute vec2 texCoord;
attribute vec3 tangent;

varying vec3 v_tangent, v_normal;
varying vec2 v_texCoord;
varying vec3 v_lightDir, v_viewDir;

void main() {
   vec4 posWS = worldMat * vec4(position, 1.0);
   gl_Position = projMat * viewMat * posWS;
   v_texCoord = texCoord;

   v_normal = mat3(worldMat) * normal;
   v_tangent = mat3(worldMat) * normalize(tangent);
   v_lightDir = normalize(lightPos - posWS.xyz);
   v_viewDir = normalize(eyePos - posWS.xyz);
}