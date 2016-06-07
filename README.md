# Cyberseminar-Microservices

[Presentation](https://drive.google.com/file/d/0B5Mz_I4Mq6FFR1BDbTlQYmVWTlU/view?usp=sharing)

## CRM
port 8001

GET /user

GET /user/{userId}

POST /user

```json
{
"firstname": "Gucio",
"lastname": "Dudek"
}
```

## Products
port 8002

GET /product

GET /product/{productId}

POST /product

```json
{
"title": "Gumowa kaczuszka"
}
```

## Orders
port 8003

GET /order

GET /order/{orderId}

POST /order

```json
{
"userId": 1,
"productId": 2,
"quantity": 10
}
```
