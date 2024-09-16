# Note Spec API

## CREATE NOTE

Endpoint : POST /noteify/notes

REQUEST HEADER : 

- X-API-TOKEN : TOKEN (Mandatory)

Request Body :

```json
{
  "title": "Catatan hari ini",
  "description": "deskripsi hari ini",
  "isArchieved" : false
  
}
```

Response Body (Success) :

```json
{
  "data": {
    "id" : "random-string",
    "title": "Catatan hari ini",
    "description": "deskripsi hari ini",
    "isArchieved": false,
    "created_at": "12/08/2024 08:00",
    "updated_at": "12/08/2024 08:00"
  },
  "errors": null
}
```

Response Body (Failed) :

```json
{
  "data": null,
  "errors":"unauthorized"
}
```

## GET NOTE

Endpoint : GET /noteify/notes/{noteId}

REQUEST HEADER :

- X-API-TOKEN : TOKEN (Mandatory)


Response Body (Success) :

```json
{
  "data": 
    {
      "title": "Catatan hari ini",
      "description": "deskripsi hari ini",
      "isArchieved": false,
      "created_at": "12/08/2024 08:00",
      "updated_at": "12/08/2024 08:00"
    }
  ,
  "errors": null
}
```

Response Body (Failed) :

```json
{
  "data": null,
  "errors":"unauthorized"
}
```

## UPDATE NOTE

Endpoint : PATCH /noteify/notes/{noteId}

REQUEST HEADER :

- X-API-TOKEN : TOKEN (Mandatory)

Request Body :

```json
{
  "title": "Catatan hari ini",
  "description": "deskripsi hari ini",
  "isArchieved" : false
}
```

Response Body (Success) :

```json
{
  "data": {
    "title" : "Catatan hari ini",
    "description" : "deskripsi hari ini",
    "created_at": "12/08/2024 08:00",
    "updated_at": "12/08/2024 08:00"
  }, 
  "errors" : null
}
```

Response Body (Failed) :

```json
{
  "data": null,
  "errors":"unauthorized"
}
```

## DELETE NOTE

Endpoint : DELETE /noteify/notes/noteId

REQUEST HEADER :

- X-API-TOKEN : TOKEN (Mandatory)

Response Body (Success) :

```json
{
  "data": "Note berhasil dihapus", 
  "errors" : null
}
```

Response Body (Failed) :

```json
{
  "data": null,
  "errors":"unauthorized"
}
```

## SEARCH NOTE

Endpoint : GET /noteify/notes

Query Param : 

- title : String, note title, using like query
- page : Integer, start from 0, default 0
- size : Integer, default 10

REQUEST HEADER :

- X-API-TOKEN : TOKEN (Mandatory)

Request Body :

```json
{
  "title" : "Catatan hari ini",
  "description" : "deskripsi hari ini",
  "isArchieved" : false
}
```

Response Body (Success) :

```json
{
  "data": [
    {
      "title": "Catatan hari ini",
      "description": "deskripsi hari ini",
      "isArchieved": false,
      "created_at": "12/08/2024 08:00",
      "updated_at": "12/08/2024 08:00"
    }
  ],
  "paging": {
    "totalpage": 10,
    "size": 10,
    "currentPage": 0
  },
  "errors": null
}
```

Response Body (Failed) :

```json
{
  "data": null,
  "errors":"unauthorized"
}
```