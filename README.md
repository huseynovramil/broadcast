# broadcast
An application for broadcasting data in rooms for your contacts.

# Installation
After downloading repository you need to do some configurations to start the project.

## Creating user and database in MYSQL
For this run the following SQL:

```
create database rooms_broadcast;
create user 'username'@'%' identified by 'password';
use rooms_broadcast;
grant all on rooms_broadcast.* to 'username'@'%';
```
_If you change **'username'** and **'password'** in the above code, make sure to update **src/main/resources/application.properties** with the same data_

## Run the application
Run the following lines in commmand prompt with directory set to this project.
```
mvnw clean package
java -jar target/broadcast-0.0.1-SNAPSHOT.jar
```

# Usage
## Register user
### Request example
```
POST http://localhost:8080/register
Content-type: application/json
RequestBody
{
    "username": "user1",
    "password": "user1"
}
```
### Response example
```
{
    "id": 1,
    "username": "user1"
}
```

## Authenticating user
### Request example
```
POST http://localhost:8080/authenticate
Content-type: application/json
RequestBody
{
    "username": "user1",
    "password": "user1",
    "platform": "ios",
    "uuid": "asdjflqkwer"
}
```
### Response example
```
{
    "jwtToken": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyMSIsInVzZXJfaWQiOjEsImV4cCI6MTU5Mjg0Mjg1NSwidXVpZCI6ImFzZGpmbHFrd2VyIiwiaWF0IjoxNTkyODI0ODU1LCJwbGF0Zm9ybSI6ImlvcyJ9.tnuQzNbpNJc_cQ97aEN9pKFPWfdMb9MD1rt_jIfV381F3rF27QVAcq-ptrbWqWaf3CXm65JT_6Dpn0lkOT-l8g"
}
```

## Adding contacts
### Request example
```
POST http://localhost:8080/users/1/contacts
Content-type: application/json
RequestBody
{
    "contactId":2
}
```
### Response example
```
{
    "id": 2,
    "username": "user2"
}

```
## Adding private rooms
### Request example
```
POST http://localhost:8080/users/1/rooms
Content-type: application/json
```
### Response example
```
{
    "id": "FVb1bfz",
    "ownerId": 1
}
```

## Getting user
### Request example
```
GET http://localhost:8080/users/1
Content-type: application/json
```
### Response example
```
{
    "id": 1,
    "username": "user1"
}
```

## Getting rooms of User
### Request example
```
GET http://localhost:8080/users/1/rooms
Content-type: application/json
```
### Response example
```
[
    {
        "id": "FVb1bfz",
        "ownerId": 1
    }
]
```

## Getting contacts of User
### Request example
```
GET http://localhost:8080/users/1/contacts
Content-type: application/json
```

### Response example
```
[
    {
        "id": 2,
        "username": "user2"
    },
    {
        "id": 3,
        "username": "user3"
    }
]
```

## For testing broadcast in public room
# Go to localhost:8080/rooms/public/{roomId}

## For testing broadcast in private room
# Go to localhost:8080/rooms/private/{roomId}
