# Evaluación GlobalLogic - BCI
    Microservicio desarrollado con Springboot 2.5.14 y Gradle hasta 7.4.
    En este microservicio se pueden crear y consultar usuarios.

## Diagramas
    Dentro de la carpeta del microservicio hay una carpeta llamada "diagrams" donde
    se puede encontrar los diagramas de componentes y secuencia del microservicio.

## Endpoints
     En este microservicio se encuentran 2 endpoint.
    
    El primero es el endpoint con ruta "localhost:8080/sign-up" para la creación de un
    usuario donde se registra el email además de otros datos y se retorna un token junto con el ID y fecha de creación y último login.

    El segundo endpoint con ruta en "localhost:8080/login/" es para la consulta de usuarios junto con la 
    actualización del token. Se retorna los datos del usuario junto con el nuevo token de autorizacion.

    De todas formas se adjunta una colección de Postman para la ejecución de los endpoint.

## Construcción del proyecto
    Para la construcción del proyecto en un IDE como por ejemplo Intellj. Se clona el proyecto
    desde el repositorio remoto. Se importa como proyecto gradle seleccionando el archivo "gradle.build",
    y luego se abre la solapa de herramientas gradle en Intellj donde se encuentran las "task" de gradle
    para la limpieza, compilación y ejecución de test entre otras. Se ejecutan las tareas "clean" y "build"
    y luego desde Intellj se ejecuta la clase "Main" o el boton para iniciarlo donde se hace el 
    "Run LoginApplication".

    Si no se quiere construir y ejecutar desde el IDE se pueden utilizar la linea de comandos
    para la limpieza, construcción y ejecución del proyecto.

    Desde la carpeta del proyecto, utilizamos los siguientes comandos gradle para la construcción:

     .\gradlew clean (Limpieza de la carpeta target)
     .\gradlew build (compilación del proyecto y creación de la carpeta build y las clases .class)
     .\gradlew test (ejecución de test)
     .\gradlew jacocoTestReport (Ejecucion de pruebas unitarias junto con reporte de covertura)
     .\gradlew run (Ejecución)

    Una vez que se ejecuta el proyecto compilado se puede empezar a utilizar los endpoint que
    por defecto van a estar en el host local: "localhost:8080".

## Cobertura de pruebas

    Una vez finalizada la tarea de "jacocoTestReport" se genera un reporte en la carpeta
    "/build/reports/jacoco" en el archivo "index.html" donde podemos visualizar una cobertura del 96%

## Consumir los endpoint

    /Sign-up:
        Method: POST
        Path: localhost:8080/sign-up
        Request:
        {
            "name": "Nombre",
            "email": "nombre@email.com",
            "password": "A3fgh4asd",
            "phones": [
                {
                    "number": 1112244,
                    "citycode": 1,
                    "countrycode":"54"
                },
                {
                    "number":1113345,
                    "citycode": 1,
                    "countrycode":"54"
                }
            ]
        }
        Response:
        {
            "id": 2,
            "created": "Oct 09, 2024 11:38:13 AM",
            "lastLogin": "Oct 09, 2024 11:38:13 AM",
            "token": "eyJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6Im5vbWJyZUBlbWFpbC5jb20iLCJzdWIiOiJOb21icmUiLCJpYXQiOjE3Mjg0ODQ2OTMsImV4cCI6MTcyODQ4NDY5MzI4NH0.p88a4FNAl4HL9HqDvCjSMcOuSe2YjTA6sJz4uY-8GU8",
            "isActive": true
        }

    /login:
        Method: POST
        Path: localhost:8080/login
        Headers:
            Authorization: **token del sign-up**
        response:
        {
            "id": 2,
            "created": "Oct 09, 2024 11:38:13 AM",
            "lastLogin": "Oct 09, 2024 11:40:42 AM",
            "token": "eyJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6Im5vbWJyZUBlbWFpbC5jb20iLCJzdWIiOiJOb21icmUiLCJpYXQiOjE3Mjg0ODQ4NDIsImV4cCI6MTcyODQ4NDg0MjE1OH0.iuRAMMLDF20PoTgXtfxTWKvsCMNy_o-ujcycO2Z6WoU",
            "isActive": true,
            "name": "Nombre",
            "email": "nombre@email.com",
            "password": "$2a$12$eTRy2j9rDt0JCG2.YsiCvOQF8vGEpYG7mdqpeX2ZH1iFRk2KtEihi",
            "phones": [
                {
                    "number": 1112244,
                    "citycode": 1,
                    "countrycode": "54"
                },
                {
                    "number": 1113345,
                    "citycode": 1,
                    "countrycode": "54"
                }
            ]
        }

## Base de datos H2

    La base de datos está configurada para trabajar en memoria. Si se
    quiere persistir se puede modificar la propiedad en el archivo "application.properties"
    en el campo *spring.datasource.url* poniendo el nombre de archivo que nos interesa. "file"
    en el ejemplo.

        spring.datasource.url=jdbc:h2:file:/data/demo

## Acceder a la base de datos en memoria

    Una vez el microservcio esté en ejecución podemos ir a la siguiente url:
    "localhost:8080/h2-console" con los siguientes datos:
        user: sa
        password: password