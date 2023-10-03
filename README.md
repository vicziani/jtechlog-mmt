# Spring Boot 3 példa

Ez a program a JTechLog (<http://jtechlog.hu>) blog "Spring Boot 3 újdonságai" posztjához készült példaprogram.
Spring Boot alkalmazás.

Futtatásához Zipkin szükséges, mely Dockerrel a következõképp indítható:

```
docker run -d -p 9411:9411 --name zipkin openzipkin/zipkin
```