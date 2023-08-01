# planogram-nomenclature
![logo-planogram](https://github.com/arabro15/planogram-nomenclature/assets/82226100/1467b9d4-0b3c-41c1-a8d5-cc3990143067)   
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
# REST API  
### [Просмотреть REST API](RESTAPI.md)
---
# Design
### Concept design by [dopexxv](https://github.com/dopexxv)  
#### Login
![Login](https://github.com/arabro15/planogram-nomenclature/assets/82226100/8ce4b918-1264-4fd6-b376-60e0e3f3cc12)
#### Menu
![MainMenu](https://github.com/arabro15/planogram-nomenclature/assets/82226100/6f755f61-dca2-4d6a-825e-d94b4c0542c1)
#### Plan market
![ViewPlanMarket](https://github.com/arabro15/planogram-nomenclature/assets/82226100/bf50dafb-92d5-454f-bae0-8eff480a1fbd)
#### Planogram
![Planogram](https://github.com/arabro15/planogram-nomenclature/assets/82226100/f71e9330-53d3-4d37-8559-ce9a8c6b9869)

