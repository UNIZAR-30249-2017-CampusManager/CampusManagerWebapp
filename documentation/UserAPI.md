# API Usuarios

Todos los endpoints vienen precedidos de `/api`.

## /user (GET)

Petición GET que devuelve una lista con los usuarios en formato JSON almacenados en la BBDD.

Estos usuarios contienen los siguientes atributos:

| Atributo | Required? | Descripción |
| ----- | --------- | ----------- | 
| email | required | Email(único) del usuario | 
| password | required | Contraseña del usuario |
| name | required | Nombre del usuario |
| surname | required | Apellidos del usuario |
| role | required | Rol del usuario. Puede ser "ADMIN", "WORKER" o "PROFFESOR" 

## /user (POST)

Body de la petición:

* *Usuario* (required): objeto con el email, contraseña, nombre, apellidos y  rol  del usuario a crear.

Petición POST para añadir un nuevo usuario. Si el objeto pasado en el body de la petición no cumple lo especificado, o si ya existe un usuario con ese email en el sistema se devuelve un código HTTP de *BAD REQUEST*, junto con un mensaje indicando la causa del error. En caso contrario, el usuario es añadido a la BBDD y se devuelve un código HTTP de CREATED.

## /user/{email} (GET)

Parámetros de la petición:

* *email* (required): identificador del usuario.

Petición GET que devuelve la información del usuario en la BBDD cuyo *email* sea el especificado. Si no existe ningún usuario con ese email en la BBDD se devuelve un objeto JSON vacío ({}).

## /user/{email} (PUT)

Parámetros de la petición:

* *email* (required): identificador del usuario

Body de la petición:

* *Usuario* (required): objeto con los datos a cambiar.

Petición PUT para modificar la información del usuario identificado con *email*.
Si no aparece alguno de los campos del objeto usuario o sí no existe un usuario con ese email, se devuelve un código HTTP de *BAD REQUEST*. En caso contrario (ejecución correcta), se modifica el usuario en la BBDD y se devuelve un código HTTP de *OK*, junto con el objeto usuario actualizado.

## /user/{email} (DELETE)

Parámetros de la petición:

* *email* (required): identificador del usuario

Petición DELETE para eliminar al usuario identificado con *email*. Si no existe ningún usuario con ese email, se 
devuelve un código HTTP de *BAD REQUEST*. En caso contrario, elimina al usuario de la BBDD y devuelve un código HTTP de *OK*.
