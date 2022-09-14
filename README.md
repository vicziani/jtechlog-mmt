# Spring Boot 3 példa

Ez a program a JTechLog (<http://jtechlog.hu>) blog "Mi várható a Spring Boot 3-ban?" posztjához készült példaprogram.
Spring Boot alkalmazás.

Futtatásához Zipkin szükséges, mely Dockerrel a következõképp indítható:

```
docker run -d -p 9411:9411 --name zipkin openzipkin/zipkin
```