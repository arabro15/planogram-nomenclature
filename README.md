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
# REST API
## Brand REST API  
### POST /api/v1/create-brand  
  
Request:   
```json
{
    "name": "String"
}
```  
Response:  
```json
{
    "brandID": "UUIDStr"
}
```  
### POST /api/v1/delete-by-id-brand
  
Request:   
```json
{
    "brandID": "UUIDStr"
}
```
  
Response:   
**200**

### POST /api/v1/edit-brand
  
Request:  
```json
{
    "brandID": "UUIDStr",
    "name": "String"
}
```  
Response:   
```json
{
    "brandID": "UUIDStr",
    "name": "String"
}
```
### POST /api/v1/get-brand-by-id  
  
Request:   
```json
{
    "brandID": "UUIDStr",
}
```  
Response:   
```json
{
    "brandID": "UUIDStr",
    "name": "String"
}
```
### POST /api/v1/get-all-brands
  
Request:   
**empty**  
  
Response:  
```json
{
    "brandID": "UUIDStr",
    "name": "String"
}
```

## Producer REST API  
### POST /api/v1/create-producer 
  
Request:   
```json
{
    "name": "String"
}
```  
Response:  
```json
{
    "producerID": "UUIDStr"
}
```  
### POST /api/v1/delete-by-id-producer
  
Request:   
```json
{
    "producerID": "UUIDStr"
}
```
  
Response:   
**200**

### POST /api/v1/edit-producer
  
Request:  
```json
{
    "producerID": "UUIDStr",
    "name": "String"
}
```  
Response:   
```json
{
    "producerID": "UUIDStr",
    "name": "String"
}
```
### POST /api/v1/get-producer-by-id  
  
Request:   
```json
{
    "producerID": "UUIDStr",
}
```  
Response:   
```json
{
    "producerID": "UUIDStr",
    "name": "String"
}
```
### POST /api/v1/get-all-producers
  
Request:   
**empty**  
  
Response:  
```json
{
    "producerID": "UUIDStr",
    "name": "String"
}
```

## Category REST API  
### POST /api/v1/create-category
  
Request:   
```json
{
    "name": "String",
    "color": "String", //exapmle GREEN
    "parentID": "UUIDStr or null"
    
}
```  
Response:  
```json
{
    "categoryID": "UUIDStr"
}
```  
### POST /api/v1/delete-by-id-category
  
Request:   
```json
{
    "categoryID": "UUIDStr"
}
```
  
Response:   
**200**

### POST /api/v1/delete-by-parent-id-categories
  
Request:   
```json
{
    "parentID": "UUIDStr"
}
```
  
Response:   
**200**

### POST /api/v1/edit-category
  
Request:  
```json
{
    "categoryID": "UUIDStr",
    "name": "String",
    "color": "String", //exapmle GREEN
    "parentID": "UUIDStr or null"
}
```  
Response:   
```json
{
    "categoryID": "UUIDStr",
    "name": "String",
    "color": "String", //exapmle GREEN
    "parentID": "UUIDStr or null"
}
```
### POST /api/v1/get-category-by-id  
  
Request:   
```json
{
    "categoryID": "UUIDStr"
}
```  
Response:   
```json
{
    "categoryID": "UUIDStr",
    "name": "String",
    "color": "String", //exapmle GREEN
    "parentID": "UUIDStr or null"
}
```
### POST /api/v1/get-categories-by-parent-id  
  
Request:   
```json
{
    "parentID": "UUIDStr"
}
```  
Response:   
```json
{
    "categoryID": "UUIDStr",
    "name": "String",
    "color": "String", //exapmle GREEN
    "parentID": "UUIDStr or null"
}
```
### POST /api/v1/get-all-categories
  
Request:   
**empty**  
  
Response:  
```json
{
    "categoryID": "UUIDStr",
    "name": "String",
    "color": "String", //exapmle GREEN
    "parentID": "UUIDStr or null"
}
```

## Product REST API  
### POST /api/v1/create-product
  
Request:   
```json
{
    "code1C": "String",
    "rusName": "String",
    "kazName": "String",
    "categoryID": "UUIDStr",
    "brandID": "UUIDStr",
    "producerID": "UUIDStr",
    "barcode": "String",
    "price": "String",
    "height": "String",
    "weight": "String",
    "length": "String",
    "imagePath": "String"
}
```  
Response:  
```json
{
    "productID": "UUIDStr"
}
```  
### POST /api/v1/delete-by-id-product
  
Request:   
```json
{
    "productID": "UUIDStr"
}
```
  
Response:   
**200**

### POST /api/v1/edit-product
  
Request:  
```json
{
    "productID": "UUIDStr"
    "code1C": "String",
    "rusName": "String",
    "kazName": "String",
    "categoryID": "UUIDStr",
    "brandID": "UUIDStr",
    "producerID": "UUIDStr",
    "barcode": "String",
    "price": "String",
    "height": "String",
    "weight": "String",
    "length": "String",
    "imagePath": "String"
}
```  
Response:   
```json
{
    "productID": "UUIDStr"
}
```

### POST /api/v1/get-product-by-id  
  
Request:   
```json
{
    "productID": "UUIDStr"
}
```  
Response:   
```json
{
    "productID": "UUIDStr"
    "code1C": "String",
    "rusName": "String",
    "kazName": "String",
    "categoryID": "UUIDStr",
    "brandID": "UUIDStr",
    "producerID": "UUIDStr",
    "barcode": "String",
    "price": "String",
    "height": "String",
    "weight": "String",
    "length": "String",
    "imagePath": "String"
}
```

### POST /api/v1/get-product-code-1c  
  
Request:   
```json
{
    "code1c": "String"
}
```  
Response:   
```json
{
    "productID": "UUIDStr"
    "code1C": "String",
    "rusName": "String",
    "kazName": "String",
    "categoryID": "UUIDStr",
    "brandID": "UUIDStr",
    "producerID": "UUIDStr",
    "barcode": "String",
    "price": "String",
    "height": "String",
    "weight": "String",
    "length": "String",
    "imagePath": "String"
}
```

### POST /api/v1/get-all-products
  
Request:   
**empty**  
  
Response:  
```json
{
    "productID": "UUIDStr"
    "code1C": "String",
    "rusName": "String",
    "kazName": "String",
    "categoryID": "UUIDStr",
    "brandID": "UUIDStr",
    "producerID": "UUIDStr",
    "barcode": "String",
    "price": "String",
    "height": "String",
    "weight": "String",
    "length": "String",
    "imagePath": "String"
}
```

### POST /api/v1/get-products-by-producer
  
Request:  
```json
{
    "producerID": "UUIDStr"
}
```
Response:  
```json
{
    "productID": "UUIDStr"
    "code1C": "String",
    "rusName": "String",
    "kazName": "String",
    "categoryID": "UUIDStr",
    "brandID": "UUIDStr",
    "producerID": "UUIDStr",
    "barcode": "String",
    "price": "String",
    "height": "String",
    "weight": "String",
    "length": "String",
    "imagePath": "String"
}
```

### POST /api/v1/get-products-by-category
  
Request:   
```json
{
    "categoryID": "UUIDStr"
}
```
Response:  
```json
{
    "productID": "UUIDStr"
    "code1C": "String",
    "rusName": "String",
    "kazName": "String",
    "categoryID": "UUIDStr",
    "brandID": "UUIDStr",
    "producerID": "UUIDStr",
    "barcode": "String",
    "price": "String",
    "height": "String",
    "weight": "String",
    "length": "String",
    "imagePath": "String"
}
```

### POST /api/v1/get-products-by-brand
  
Request:   
```json
{
    "brandID": "UUIDStr"
}
``` 
Response:  
```json
{
    "productID": "UUIDStr"
    "code1C": "String",
    "rusName": "String",
    "kazName": "String",
    "categoryID": "UUIDStr",
    "brandID": "UUIDStr",
    "producerID": "UUIDStr",
    "barcode": "String",
    "price": "String",
    "height": "String",
    "weight": "String",
    "length": "String",
    "imagePath": "String"
}
```
