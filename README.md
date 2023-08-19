# REST-API приложение продуктов и витрин
## Стек технологий
- Java 17 (весь синтаксис использовался Java 8)
- Maven
- Spring Boot 3.1.2
- Hibernate Validator - Валидация
- Spring Data JPA - ORM
- Spring WEB - написание REST-API 
- PostgreSQL - БД
- Liquibase - Миграция БД
- Swagger - Документация
## Описание REST-API / Описание end-поинтов
Для удобства рекомендую перейти по ссылке http://localhost:8080/swagger-ui/index.html при запущенном приложении.
Запуск описан в следующей главе.

### Витрины
**Получение витрин и их фильтрация: (GET)**

(GET) http://localhost:8080/api/v1/showcase

У данного запроса имеются параметры для фильтрации витрин (все не обязательные)
- type (string) Тип витрины
- address (string) Адрес витрины
- createdDateFrom string ($date-time) Начало промежутка создания витрины
- createdDateTo string ($date-time) Конец промежутка создания витрины
- updatedDateFrom string ($date-time) Начало промежутка обновления витрины
- updatedDateTo string ($date-time) Конец промежутка обновления витрины

OUTPUT: 
```JSON
[
  {
    "uuid": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
    "name": "string",
    "type": "string",
    "address": "string",
    "createdAt": "2023-08-19T12:16:09.325Z",
    "updatedAt": "2023-08-19T12:16:09.325Z",
    "products": [
      {
        "uuid": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
        "name": "string"
      }
    ]
  }
]
```

#### **Получение витрин и их фильтрация: (PUT)**

(PUT) http://localhost:8080/api/v1/showcase

INPUT (все поля не обязательные):
```JSON
{
  "name": "string",
  "type": "string",
  "address": "string",
  "createdDateFrom": "2023-08-19T12:13:39.009Z",
  "createdDateTo": "2023-08-19T12:13:39.009Z",
  "updatedDateFrom": "2023-08-19T12:13:39.009Z",
  "updatedDateTo": "2023-08-19T12:13:39.009Z"
}
```
#### **Создание витрины: (POST)**

(POST)http://localhost:8080/api/v1/showcase

INPUT:
```JSON
{
  "name": "string",
  "type": "string",
  "address": "string"
}
```

#### **Получение витрины по UUID (GET)**

(GET)http://localhost:8080/api/v1/showcase/{uuid}

OUTPUT:

```JSON
{
  "uuid": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
  "name": "string",
  "type": "string",
  "address": "string",
  "createdAt": "2023-08-19T12:24:49.811Z",
  "updatedAt": "2023-08-19T12:24:49.811Z",
  "products": [
    {
      "uuid": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
      "name": "string"
    }
  ]
}
```
#### **Удаление витрины по UUID (DELETE)**
(DELETE) http://localhost:8080/api/v1/showcase/{uuid}

#### **Обновление витрины (PATCH)**
(PATCH) http://localhost:8080/api/v1/showcase/{uuid}

INPUT:

```JSON
{
  "name": "string",
  "type": "string",
  "address": "string",
  "products": [
    {
      "uuid": "3fa85f64-5717-4562-b3fc-2c963f66afa6"
    }
  ]
}
```

### Продукты
#### **Получение продуктов витрины по UUID витрины (GET)**
http://localhost:8080/api/v1/products/{showcaseUUID}

OUTPUT:

```JSON
[
  {
    "uuid": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
    "position": 10,
    "name": "string",
    "type": "string",
    "price": 1.0,
    "createdAt": "2023-08-19T12:34:05.017Z",
    "updatedAt": "2023-08-19T12:34:05.017Z",
    "owner": {
      "uuid": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
      "name": "string"
    },
    "priceNotValid": true
  }
]
```

#### **Получение продуктов витрины по UUID витрины и фильтрация (PUT)**
http://localhost:8080/api/v1/products/{showcaseUUID}

INPUT:
```JSON
{
  "type": "string",
  "priceFrom": 0.01,
  "priceTo": 0.02
}
```

OUTPUT:

```JSON
[
  {
    "uuid": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
    "position": 10,
    "name": "string",
    "type": "string",
    "price": 1.0,
    "createdAt": "2023-08-19T12:34:05.017Z",
    "updatedAt": "2023-08-19T12:34:05.017Z",
    "owner": {
      "uuid": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
      "name": "string"
    },
    "priceNotValid": true
  }
]
```

#### **Создание продукта (POST)**
http://localhost:8080/api/v1/products
INPUT:
```JSON
{
  "position": 10,
  "name": "string",
  "type": "string",
  "price": 0.01,
  "owner": "3fa85f64-5717-4562-b3fc-2c963f66afa6"
}
```
#### **Удаление продукта по его UUID (DELETE)**
http://localhost:8080/api/v1/products/{uuid}

#### **Обновление продукта (PATCH)**
http://localhost:8080/api/v1/products/{uuid}
INPUT:
```JSON
{
  "position": 10,
  "name": "string",
  "type": "string",
  "price": 0.01,
  "owner": "3fa85f64-5717-4562-b3fc-2c963f66afa6"
}
```

## Сборка и запуск

Перед запуском убедитесь что у Вас на компьютере установленна JDK версии не раньше 17

- Для сборки проекта скачайте архив с проектом или склонируйте его в удобную для Вас директорию.
- Далее требуется изменить один конфигурационный файл: application-to-commit.yml
  * перейдите в директорию src/main/resources/
  * переименуйте файл в 'application.yml'
  * далее измените поля url (адрес БД), username (пользователь БД), password (пароль пользователя БД)
  ``` yaml
  spring:
  liquibase:
    enabled: true
  datasource:
    url: # пример - jdbc:postgresql://localhost:5432/ShowcaseProduct
    username:
    password:
  jpa:
    properties:
      hibernate:
        dialect: org.hibernate.dialect.PostgreSQLDialect
        show_sql: true
  ```
- Из корня проекта (Вы должны находиться в папке ProductShowcase-master) запустите сборку проекта через консоль (cmd, powershel, git bash и т.д.) данной командой:
```
./mvnw spring-boot:run
```
- Проект собран и запущен. Для теста всех доступных end-поинтов перейдите по ссылке http://localhost:8080/swagger-ui/index.html

