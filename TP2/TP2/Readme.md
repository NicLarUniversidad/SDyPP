# Instrucciones

## Peer to peer

*Levantar servidor de colas asincrónicas con el docker-compose dentro de /rabbitmq con docker compose-up.
*Crear una cola en el servidor llamada "colaCompartidos".
*Ejecutar el main, se le mostrará una interfaz por consola.


*Para subir un archivo, hay que ingresar el path del mismo teniendo en cuenta la ubicación de donde se esté corriendo, ya sea desde un IDE o un .jar.

*Luego, podrá ver que su archivo está en el servidor de colas asincrónicas.

*Para descargarlo, desde la interfaz de consola de la aplicación java, seleccione la segunda opción y luego el número que en la consola se muestre a la izquierda del archivo que quiera descargar.


## Sobel

*Para ejecutar el centralizado se debe correr el main. Siempre toma un archivo llamado "prueba.jpg" del directorio en donde se corra la aplicación.

*Para ejecutar el distribuido, primero levantar el servidor de colas asincrónicas.
*Luego, correr el main de la clase Servidor. También, se preparó un .jar en https://github.com/NicLarUniversidad/SDyPP/blob/master/TP2/TP2/SobelDistribuido/out/artifacts/Peer_To_Peer_jar2/Servidor.jar

*Finalmente, correr la clase cliente, que como el centralizado, toma un archivo "prueba.jpg" de la carpeta del mismo nivel que se está ejecutando la aplicación. Su .jar está en https://github.com/NicLarUniversidad/SDyPP/blob/master/TP2/TP2/SobelDistribuido/out/artifacts/Peer_To_Peer_jar/Cliente.jar
