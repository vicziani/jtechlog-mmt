# Spring Boot 3 p�lda

Ez a program a JTechLog (<http://jtechlog.hu>) blog "Mi v�rhat� a Spring Boot 3-ban?" posztj�hoz k�sz�lt p�ldaprogram.
Spring Boot alkalmaz�s.

Futtat�s�hoz Zipkin sz�ks�ges, mely Dockerrel a k�vetkez�k�pp ind�that�:

```
docker run -d -p 9411:9411 --name zipkin openzipkin/zipkin
```