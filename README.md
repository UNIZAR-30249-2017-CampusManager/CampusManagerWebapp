# Campus Manager Webapp

#Pasos previos para trabajar en la aplicación usando IntelliJ
1. Copiar la carpeta runConfigurations dentro de la carpeta .idea.

#F.A.Q.
1. Nada más crear el proyecto y/o cuando se creen nuevas dependencias tanto en el fichero package.json o bower.json, ejecutar el setup "Instalar dependencias".
1. Cuando se esté en desarrollo en local en Intellij ejecutar siempre el setup "Ejecutar Spring".
1. Si por algún motivo se tuviese que borrar la configuración de node/bower en el proyecto, borrar los siguientes directorios:
  1. node_modules
  1. node
  1. src/main/resources/static/bower_components
1. Para limpiar el proyecto en la carpeta de salida, ejecutar la operación de maven "clean".