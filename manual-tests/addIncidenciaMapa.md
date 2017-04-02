# Pruebas manuales sobre el mapa al añadir nuevas incidencias

1. En la pantalla principal de la aplicación `/`, no debe ser posible añadir una nueva incidencia. Así, cuando se clickea
 sobre cualquier punto del mapa, solo se debe mostrar un marcador con la leyenda "¡Estás aquí!".
2. Al acceder a cualquiera de los edificios (desplegable `Información` y seleccionar cualquiera `Edificio Ada Byron`, 
`Edificio Betancourt` o `Edificio Torres Quevedo`) debe mostrarse el plano del edificio seleccionado.
3. Si se clickea sobre el mismo mapa, pero en un punto externo al edificio, sobre algunos polígonos que no tienen 
información o sobre algunos polígonos generales (como "Baños" o "Rellano"), debe mostrarse solo un marcador con la leyenda
"¡Estás aquí!".
4. Si se clickea sobre un polígono con información, debe mostrarse un marcador con la info del polígono (que aula, 
despacho, etc...) representa, un mensaje "¿Tienes alguna incidencia que reportar?" y un link `Reportar`.
    1. Al clickearse sobre este link debe abrirse un popup para crear la incidencia.
    1. Se deben incluir 2 campos ("Nombre" y "Descripción") y si alguno de ellos es vacío, debe informarse al usuario para 
    que lo complete.
    1. Una vez rellenado los 2 campos, si se clickea sobre el botón de submit `Crear`, debe crearse la incidencia, siendo
    almacenada en la BBDD.
    1. Por último, si todo ha salido bien, el popup debe cerrarse y se debe mostrar un mensaje de "Success" diciendo
    que la leyenda ha sido registrada correctamente en la ubicación seleccionada.