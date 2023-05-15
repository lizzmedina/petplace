# API REST de reservas de Guarderia para mascotas PetPlace :hotel: :paw_prints:

<div align= "rigth">Esta API REST de reservas de habitación de hotel es una aplicación Spring Boot que te permite gestionar reservas de guarderia para mascotas. El proyecto esta construido con la versión de '3.0.6' de Spring Boot, un gestor de dependecias con  Maven compatible con Java 17 :coffee:, con un motor de base de datos en MySQL y persistencia de datos co JPA e Hibernate.

Las principales dependencias utilizadas son:

- Spring Data JPA (Persiste bases de datos SQL utilizando Java Persistence API mediante Spring Data y Hibernate.)
- Spring Web (Construye aplicaciones web, incluyendo RESTful, utilizando Spring MVC. Utiliza Apache Tomcat como contenedor integrado predeterminado.)
- JUnit (Testeo de pruebas unitarias)


## :computer: Endpoints:

### Endpoint de creación de producto :hotel:

#### POST: localhost:8080/api/v1/petDayCare

Crea un nuevo producto "Guarderia" en la base de datos con la información proporcionada en el cuerpo de la solicitud.

##### Parámetros de entrada:

Ejemplo de solicitud:

```java
{
        "id": 1,
        "name": "Huellas de amor",
        "type": "Campestre",
        "capacity": 50,
        "city": "Cali",
        "address": "Calle 3, via Yumbo",
        "detail": " somo una guarderia campestre con amplias zonas verdes para tu peludo",
        "image": "/images.png",
        "basicPrice": 30.000
        }
```

La API devolverá el nuevo producto creado en formato JSON:
```java
{
        "id": 1,
        "name": "Huellas de amor",
        "type": "Campestre",
        "capacity": 50,
        "city": "Cali",
        "address": "Calle 3, via Yumbo",
        "detail": " somo una guarderia campestre con amplias zonas verdes para tu peludo",
        "image": "/images.png",
        "basicPrice": 30.0
        }
```

### Endpoint de listar  productos "Guarderias" :hotel:

#### GET: localhost:8080/api/v1/petDayCareList

La API devolverá el nuevo producto creado en formato JSON:
```java

[
        {
        "id": 1,
        "name": "Huellas de amor",
        "type": "Campestre",
        "capacity": 50,
        "city": "Cali",
        "address": "Calle 3, via Yumbo",
        "detail": " somo una guarderia campestre con amplias zonas verdes para tu peludo",
        "image": "/images.png",
        "basicPrice": 30.0
        },
        {
        "id": 2,
        "name": "Huellas de amor",
        "type": "Campestre",
        "capacity": 50,
        "city": "Cali",
        "address": "Calle 3, via Yumbo",
        "detail": " somo una guarderia campestre con amplias zonas verdes para tu peludo",
        "image": "/images.png",
        "basicPrice": 30.0
        }
]

```

### Endpoint de eliminar  productos "Guarderias" :hotel:

#### DELETE: localhost:8080/api/v1/petDayCare/{id}

La API devolverá el siguiente mensaje tipo String:

```java
"El producto fue eliminado "
```

