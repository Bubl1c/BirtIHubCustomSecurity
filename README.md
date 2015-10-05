To build for testing:
- Add libs from /lib folder to project dependencies
- build with dependencies

To build for IHub:
- build {extends iPortalSecurityAdapter class name}.jar without dependencies
- put jar to "{ihub's installation directory}/modules/BIRTiHub/iHub/web/iportal/WEB-INF/lib" catalogue

IMPORTANT!!!
Make sure that IHub instance contains
- "httpclient-{version}.jar"
- "httpcore-{version}.jar"
with {version} >= 4.4.1
Jar files can be found in "{ihub's installation directory}/modules/BIRTiHub/iHub/web/iportal/WEB-INF/lib"