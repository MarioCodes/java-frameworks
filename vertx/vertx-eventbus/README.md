### Vertx Eventbus examples
The consumer and the producer have been created as 2 projects, to be able to execute them as standalone Verticles from CLI. 
To start them, with must do so with -cluster option enabled, so Vertx searches for other verticles and manages the connection.   

Example:  
>_mvn clean install && java -jar target/vertx-eventbus-simple-consumer-0.0.1-SNAPSHOT-fat.jar **-cluster**_
