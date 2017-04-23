# API Incidencias

Todos los endpoints vienen precedidos de `/api`.

## /incidencia (GET)

Petición GET que devuelve una lista con las incidencias (objetos *CampusIncidence*) almacenadas en la BBDD.

Estos objetos incidencia contienen los siguientes atributos:

| Atributo | Required? | Descripción |
| ----- | --------- | ----------- | 
| id | required | Identificador de la incidencia | 
| name | required | Nombre de la incidencia |
| description | required | Descripción de la incidencia |
| place | required | Lugar de la incidencia |
| building | required | Edificio de la incidencia |
| status | required | Estado actual de la incidencia. Puede ser "UNASSIGNED", "ASSIGNED", "INPROGRESS", "INVALID" o "FINALIZED" |
| workerEmail |  | Email del personal de mantenimiento al que se le ha asignado la incidencia |
| fecha | required | Fecha en la que la incicidencia fue añadida a la BBDD |

## /incidencia (POST)

Body de la petición:

* *CampusIncidence* (required): objeto con el nombreEspacio, descripción, lugar y edificio de la incidencia a crear.

Petición POST para añadir una nueva incidencia. Si el objeto pasado en el body de la petición no cumple lo especificado,
es devuelto un código HTTP de *BAD REQUEST*. En caso contrario, la incidencia es añadida a la BBDD y se devuelve un código
HTTP de OK.

## /incidencia/{id} (GET)

Parámetros de la petición:

* *id* (required): identificador de la incidencia

Petición GET que devuelve la incidencia representada en la BBDD por el *id*. Si no existe incidencia con tal identificador
en la BBDD se devuelve un objeto JSON vacío ({}).

## /incidencia/{id} (PUT)

Parámetros de la petición:

* *id* (required): identificador de la incidencia

Body de la petición:

* *CampusIncidence* (required): objeto con el estado y Email del personal de mantenimiento.

Petición PUT para modificar el estado y el personal de mantenimiento (worker) de la incidencia identificada con *id*.
Si el estado del objeto incidencia no cumple las especificaciones es devuelto un código HTTP de *BAD REQUEST*. Si no existe
incidencia con tal identificador o no existe worker con el Email pasado en el objeto incidencia en la BBDD, se devuelve 
un código HTTP de *NOT FOUND*. En caso contrario (ejecución correcta), se modifica la incidencia en la BBDD y se devuelve 
un código HTTP de *OK*, junto con el objeto incidecia actualizado.

## /incidencia/{id} (DELETE)

Parámetros de la petición:

* *id* (required): identificador de la incidencia

Petición DELETE para eliminar la incidencia identificada con *id*. Si no existe incidencia con tal indentificador, es 
devuelto un código HTTP de *NOT FOUND*. En caso contrario, elimina la incidencia de la BBDD y devuelve un código HTTP de *OK*.

## /incidencia/worker/{workerEmail} (GET)

Parámetros de la petición:

* *workerEmail* (required): identificador del worker.

Petición GET que devuelve una lista de objetos *CampusIncidence* con las incidencias asignadas al personal de mantenimiento (worker) con email 
especificado en el parámetro. Si no existe worker con tal email devuelve una lista vacía.

## /incidencia/estado/{status} (GET)

Parámetros de la petición:

* *status* (required): identificador del estado.

Petición GET que devuelve una lista de objetos *CampusIncidence* con las incidencias las cuales su estado actual es el 
especificado en el parámetro. 