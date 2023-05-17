# API REST de reservas de Guarderia para mascotas PetPlace :hotel: :feet:

<div align= "rigth">Esta API REST de reservas de habitación de hotel es una aplicación Spring Boot que te permite gestionar reservas de guarderia para mascotas. El proyecto esta construido con la versión de '3.0.6' de Spring Boot, un gestor de dependecias con  Maven compatible con Java 17 :coffee:, con un motor de base de datos en MySQL y persistencia de datos co JPA e Hibernate.

Las principales dependencias utilizadas son:

- Spring Data JPA (Persiste bases de datos SQL utilizando Java Persistence API mediante Spring Data y Hibernate.)
- Spring Web (Construye aplicaciones web, incluyendo RESTful, utilizando Spring MVC. Utiliza Apache Tomcat como contenedor integrado predeterminado.)
- JUnit (Testeo de pruebas unitarias)

# Prerequisitos

Debemos tener en cuenta que para poder ejecutar nuestro proyecto, se tiene que contar con el siguiente entorno configurado.

1. Java 17+
2. Maven
3. MySQL, una instancia de MySQL corriendo en `localhost` en el puerto `3306`

# Guía de ejecución

Para ejecutar el programa, debemos clonar el repositorio mediante el siguiente comando:

```sh
git clone https://gitlab.ctd.academy/ctd/hispanos/proyecto-integrador-1/proyecto-integrador-0523/1021pt-c3/equipo-02.git
```

Una vez termine la ejecución, nos dirigimos a la carpeta donde se encuentra el código fuente y para ejecutar el servicio, realizamos los siguientes comandos:

```sh
cd equipo-02/Back/IntegradorMaven
mvn clean install
mvn spring-boot:run --spring.datasource.password=${DB_PASSWORD}
```
**Teniendo en cuenta que debemos reemplazar la variable `DB_PASSWORD` por el valor que hayamos asignado en la instancia de MySQL**

Una vez ejecutemos los comandos, accedemos a nuestro Swagger para ver las APIs creadas mediante la URL:

http://localhost:8080/swagger-ui/index.html

## :computer: Endpoints:

### Endpoint de creación de producto :hotel:

#### :new: POST: localhost:8080/api/v1/petDayCare

Crea un nuevo producto "Guarderia" en la base de datos con la información proporcionada en el cuerpo de la solicitud.

##### Parámetros de entrada:

Ejemplo de solicitud:

La API devolverá el nuevo producto creado en formato JSON:
```java
{
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

### Endpoint de listar productos "Guarderias" :hotel:

#### :scroll: GET: localhost:8080/api/v1/petDayCare/all

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



### Endpoint de filtrar por categoria de productos "perro, gato, canario, conejo" :hotel:

#### :mag_right: GET: localhost:8080/api/v1/petDayCare/category/{categoria}

La API devolverá una lista con un array con los objetos que corresponden a la categoria=gatos:

```java
[
        {
        "id": 4,
        "name": "Huellas de amor",
        "type": "gato",
        "capacity": 50,
        "city": "Cali",
        "address": "Calle 3, via Yumbo",
        "detail": " somo una guarderia campestre con amplias zonas verdes para tu peludo",
        "image": "/images.png",
        "basicPrice": 30.0
        },
        {
        "id": 8,
        "name": "Huellas de amor",
        "type": "gato",
        "capacity": 50,
        "city": "Cali",
        "address": "Calle 3, via Yumbo",
        "detail": " somo una guarderia campestre con amplias zonas verdes para tu peludo",
        "image": "/images.png",
        "basicPrice": 30.0
        }
        ]

```

### Endpoint de mostrar el detalle del producto según el id  :hotel:

#### :mag_right: GET: localhost:8080/api/v1/petDayCare/detail/{id}

La API devolverá un objeto con la guardería encontrada, por ejemplo utilizando el id=11:

```java
{
        "id": 11,
        "name": "Huellas de amor",
        "type": "conejo",
        "capacity": 50,
        "city": "Cali",
        "address": "Calle 3, via Yumbo",
        "detail": " somo una guarderia campestre con amplias zonas verdes para tu peludo",
        "image": "/images.png",
        "basicPrice": 30.0
}

```

### Endpoint de eliminar  productos "Guarderias" :hotel:

#### :x: DELETE: localhost:8080/api/v1/petDayCare/{id}

La API devolverá el siguiente mensaje tipo String:

```java
"El producto fue eliminado "
```


# 

### Endpoint de creación de Cliente :raising_hand: y Mascotas :feet:

#### :new: POST: localhost:8080/api/v1/customer

Crea un nuevo cliente con sus mascotas con la información proporcionada en el cuerpo de la solicitud.

##### Parámetros de entrada:

Ejemplo de solicitud:

La API devolverá el nuevo cliente creado en formato JSON:

```java
{
        "id": 123,
        "address": "av 123",
        "cellPhone": "1223",
        "email": "vivi@gmail.com",
        "lastName": "guzman",
        "name": "vivi",
        "password": "123",
        "type": "cliente",
        "pets": [
            {
            "id": 1,
            "petName": "paca",
            "petSize": "mediano",
            "petType": "gato"
            },
            {
            "id": 2,
            "petName": "Leon",
            "petSize": "pequeno",
            "petType": "perro"
            }
        ]
}
```

### Endpoint de listar clientes :raising_hand:

#### :scroll: GET: localhost:8080/api/v1/petDayCareList

La API devolverá una array de los clientes en formato JSON:
```java
[
        {
        "id": 3,
        "name": "vivi",
        "lastName": null,
        "email": "vivi@gmail.com",
        "password": "123",
        "cellPhone": null,
        "address": "av 123",
        "type": "cliente",
        "pets": []
        },
        {
        "id": 4,
        "name": "vivi",
        "lastName": "guzman",
        "email": "vivi@gmail.com",
        "password": "123",
        "cellPhone": "1223",
        "address": "av 123",
        "type": "cliente",
        "pets": []
        }
]
```

### Endpoint de eliminar un Cliente  :raising_hand:

#### :x: DELETE: localhost:8080/api/v1/customer/{id}

La API devolverá el siguiente mensaje tipo String:

```java
"El cliente fue eliminado"
```

# 


### Endpoint de crear un Administrador :key: 

#### :new: POST: localhost:8080/api/v1/manager

##### Parámetros de entrada:


Ejemplo de solicitud:

```java
{
        "id":12999899,
        "name":"Andrea",
        "lastName": "Bedoya",
        "email":"abaaaaa@gmail.com",
        "passwork": "1233345",
        "cellPhone": "321654987",
        "address": "Carrera A # b-c",
        "type": "Administrador"
}
```

La API devolverá el nuevo Manager creado en formato JSON:
```java
{
        "id": 12999899,
        "name": "Andrea",
        "lastName": "Bedoya",
        "email": "abaaaaa@gmail.com",
        "passwork": "1233345",
        "cellPhone": "321654987",
        "address": "Carrera A # b-c",
        "type": "Administrador"
}
```

### Endpoint de buscar un Administrador :key: 

#### :mag_right: GET: localhost:8080/api/v1/manager/{id}

La API devolverá el nuevo producto encontrado por id, que sería la cédula, por ejemplo utilizando como id=12999899, en formato JSON:
```java
{
        "id": 12999899,
        "name": "Andrea",
        "lastName": "Bedoya",
        "email": "abaaaaa@gmail.com",
        "passwork": "1233345",
        "cellPhone": "321654987",
        "address": "Carrera A # b-c",
        "type": "Administrador"
}

```

### Endpoint de eliminar un Administrador :key:

#### :x: DELETE: localhost:8080/api/v1/manager/{id}

La API devolverá el siguiente mensaje tipo String:

```java
"El Administrador fue eliminado"
```
