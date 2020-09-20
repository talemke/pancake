# API - Version 0
This version of the API is in development and subject to changes.


---


### `GET` `/api/v0/auth/login`
Attempt to login as a user.


**Internal:**
Although you theoretically can, you should never use this endpoint.


**Payload:**
| Field    | Type   | Required   | Description   |
| -------- | ------ | ---------- | ------------- |
| username | string | _required_ | The username. |
| password | string | _required_ | The password. |


**Response:**
| Field | Type   | Description                                |
| ----- | ------ | ------------------------------------------ |
| error | string | The reason why the login failed, or `nil`. |
| token | string | The session token, or `nil`.               |


**Example Response:**
```json
{
	"error": "Wrong username or password.",
	"token": null
}
```
