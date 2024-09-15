# User Api Spec

## Register User

Endpoint : POST /noteify/auth/register

Request Body : 

```json
{
  "username" : "fiqriturhamz",
  "password" : "rahasia123",
  "fullName" : "Muhammad Fiqri Turham",
  "address" : "Taman Ciruas Permai Blok E5 no 6",
  "phone" : "0828832738",
  "email" : "tmuhammadfiqri@gmail.com"
}
```

Response Body (Success) :

```json
{
  "data" : "berhasil register user"
}
```

Response Body (Failed) :

```json
{
  "errors": "username must not be blank"
}
```
## Login User

Endpoint : POST /noteify/auth/login

Request Body :

```json
{
  "username" : "fiqriturhamz",
  "password" : "rahasia123"
}
```

Response Body (Success) :

```json
{
  "data": {
    "token": "Token",
    "expired_at": 232323
  }
}
```

Response Body (Failed, 401) :

```json
{
  "errors": "username or password is wrong"
}
```

## Get User

Endpoint : GET /noteify/users/current-user

Request Header :

- X-API-TOKEN : TOKEN (Mandatory)

Response Body (Success) : 

```json
{
  "data": {
    "username" : "fiqriturhamz",
    "fullName" : "Muhammad Fiqri Turham",
    "address" : "Taman Ciruas Permai Blok E5 no 6",
    "phone" : "0828832738",
    "email" : "tmuhammadfiqri@gmail.com"
  }
}
```

Response Body (Failed) :

```json
{
 "errors" : "unauthorized" 
}
```

## Update User

Endpoint : PATCH noteify/users/current-user

Request Header :

- X-API-TOKEN : TOKEN (Mandatory)

Request Body :

```json
{
    "password" : "rahasia123",
    "fullName" : "Muhammad Fiqri Turham",
    "address" : "Taman Ciruas Permai Blok E5 no 6",
    "phone" : "0828832738",
    "email" : "tmuhammadfiqri@gmail.com"
}
```

Response Body (Success) :

```json
{
  "data" : {
    "password" : "rahasia123",
    "fullName" : "Muhammad Fiqri Turham",
    "address" : "Taman Ciruas Permai Blok E5 no 6",
    "phone" : "0828832738",
    "email" : "tmuhammadfiqri@gmail.com"
  }
}
```

Response Body (Failed) :
                       
```json
{
  "errors" : "unauthorized"
}

```

## Logout User

Endpoint : DELETE /noteify/auth/logout

Request Header :  

- X-API-TOKEN : TOKEN (Mandatory)   

Response Body (Success) :

```json 
{
   "data" : "Logout berhasil"
}

```