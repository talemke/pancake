# API - Version 0
This version of the API is in development and subject to changes.


---


### `GET` `/api/v0/accounts/{account_id}`
Gets an account by its ID.


**Query Parameters:**
*TODO*


**Example Response:**
```json
{
    "account": {
        "uuid": "00000000-0000-0000-0000-000000000000",
        "name": "root"
    },
    "group": {
        "uuid": "00000000-0000-0000-0000-000000000000",
        "name": "root",
        "description": "The group of the root user. This will always be the highest available group."
    }
}
```


---


### `GET` `/api/v0/emails/{email_id}`
Gets an email by its ID.


**Query Parameters:**
*TODO*


**Example Response:**
```json
{
    "email": {
        "uuid": "735dd6d9-0c74-46be-9125-185f25471e4a",
        "timestamp": 1599910351911,
        "sender": "test@example.com",
        "recipient": "user@example.com",
        "data": "UmVjZWl2ZW...VzdC4NCg0K",
        "helo": "test.example.com",
        "remoteAddress": "/0:0:0:0:0:0:0:1:49608",
        "deleted": false,
        "draft": false,
        "outgoing": false
    },
    "owner": {
        "uuid": "00000000-0000-0000-0000-000000000000",
        "name": "root"
    },
    "inbox": null
}
```


---


### `GET` `/api/v0/groups/{group_id}`
Gets a group by its ID.


**Query Parameters:**
*TODO*


**Example Response:**
```json
{
    "uuid": "00000000-0000-0000-0000-000000000000",
    "name": "root",
    "description": "The group of the root user. This will always be the highest available group."
}
```