# planogram-nomenclature
Planogram-nomenclature - это микросервис занимающейся хранением номенклатурой базы данных и является частью сервиса Planogram*.   
*\*Planogram* - позволяет создавать планограммы* для торговых стеллажей и располагать их на плане магазина. А также просмотр планограмм на любых устройствах.  
*\*Планограмма* - это схематическое изображение товара на полке.  
**Используемые технологий:** Clean architecture, Java, Spring (Boot, MVC, Data), PosgreSQL, REST API, JUnit и Mocikto.  
# Запуск
Docker: 
---
docker-compose.yml
```yml
version: "1"
services:
  database:
    container_name: local_db_planogram
    image: postgres:15.3-alpine
    restart: always
    expose:
      - 5432
    ports:
      - "5437:5432"
    env_file:
      - env/db.env
```
env/db.env
```env
POSTGRES_USER=local_user_planogram
POSTGRES_PASSWORD=yyq7UFmRn9pz
POSTGRES_DB=local_db_planogram
PGDATA=/var/lib/postgresql/data/pgdata
```
---
