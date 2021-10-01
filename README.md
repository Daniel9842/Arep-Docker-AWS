# TALLER DE DE MODULARIZACIÓN CON VIRTUALIZACIÓN E INTRODUCCIÓN A DOCKER Y A AWS
### Daniel Santiago Ducuara Ardila
### 30/09/2021

## EJERCICIO 1
Para la tarea usted debe construir una aplicación con la arquitectura propuesta y desplegarla en AWS usando EC2 y Docker.
El servicio MongoDB es una instancia de MongoDB corriendo en un container de docker en una máquina virtual de EC2
LogService es un servicio REST que recibe una cadena, la almacena en la base de datos y responde en un objeto JSON con las 10 ultimas cadenas almacenadas en la base de datos y la fecha en que fueron almacenadas.
La aplicación web APP-LB-RoundRobin está compuesta por un cliente web y al menos un servicio REST. El cliente web tiene un campo 
y un botón y cada vez que el usuario envía un mensaje, este se lo envía al servicio REST y actualiza la pantalla con la información 
que este le regresa en formato JSON. El servicio REST recibe la cadena e implementa un algoritmo de balanceo de cargas de Round Robin, 
delegando el procesamiento del mensaje y el retorno de la respuesta a cada una de las tres instancias del servicio LogService.

Para ejecutar el programa por consola utilizar:

java -cp "target/classes;target/dependency/*" edu.escuelaing.arep.sparkwebDocker.SparkWebServer

## Diagrama de clases

![Design Diagram](images/proyectos.PNG "Diagram")<br>

### Paquete Square
![Design SquareDiagram](Design/Square.PNG "SquareDiagram")<br>
En el paquete Square se divide en dos clases, el cliente y el servidor, en el método main de ambas clases se inicia el proceso
de conexión entre ambos, en el servidor el método calculateSquare recibe como entrada el número que vaya ingresando la clase cliente 
y el método le retorna el número que recibió al cuadrado. Para cerrar la conexión es necesario escribir "Bye.".
Para la ejecución es necesario iniciar primero el servidor y luego el cliente. la clase cliente empieza a enviar números y el servidor
irá respondiendo.
### Paquete URL
![Design URLDiagram](Design/URL.PNG "URLDiagram")<br>
El URLReader en el método main recibe una url, el método guarda la información html de la página y envía esta información al método writeFile,
esté método se encarga de crear un archivo llamado resultado.html con la información guardada, este archivo se creará en la raiz de la carpeta.

El URLScanner recibe una url en el método main y la envía al método methodsValue que retorna la información de los 8 métodos que retornan lainformación del
objeto URL.
### Paquete HttpServer
![Design HttpServerDiagram](Design/HttpServer.PNG "HttpServerDiagram")<br>
El paquete HttpServer se encarga de crear un servidor que soporte múltiples solicitudes, la clase cuenta con métodos como startServer en el cual se inicia la conexión 
al servidor, en el método processRequest se crea la solicitud del servidor y en el método createTextResponse retorna la información del archivo html.
Para la ejecución es necesario iniciar el servidor y en el navegador colocar 127.0.0.1:35000/archivo, para la parte del archivo se encuentran 3 archivos en la 
carpeta TestHttpServer para probar con dichos archivos.

## Reporte de pruebas
![Tests Test Report](Design/Test.PNG "Test Report")<br>
Se realiza el reporte de pruebas con todas las pruebas satisfactorias.
Para las pruebas de HttpServer se encuentra la carpeta TestHttpServer con 3 archivos html y 3 imágenes en formato png,jpg y jfif.
Al ejecutar las pruebas se crea un archivo resultado.html en la raiz de la carpeta, si este archivo se crea quiere decir que la prueba de 
URLReader es satisfactoria. 
