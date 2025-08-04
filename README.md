# Menu Service
This service is responsible for providing active menu to website and edition options to admin panel.

## Endpoints

For more information about endpoints check swagger page while application is running. 

Local path - [https://localhost:8080/swagger-ui/index.html](https://localhost:8080/swagger-ui/index.html)

### Add dish

Add new dish to menu. The dish **name** must be **unique** because it is the object **identifier**

Local path - _localhost:8080/menu/dish/add_

Request body

```json
{
  "name": "String", 
  "description": "String",
  "category": "String",
  "price": 12.34,
  "photoBase64": "String"
}
```

### Get Menu

Get dishes from menu.

Local path - _localhost:8080/menu_

Available filters:

| Name | Description |
| ---- | ---- |
| category | Filter by specific category e.g. Drink or Soup |
| name | Filter by name. Used to get specific dish |

### Update dish

Update specific dish with new data. In request body only name is mandatory.

Local path - _localhost:8080/menu/dish/update_

Request body

```json
{
  "name": "String", 
  "description": "String",
  "category": "String",
  "price": 12.34,
  "photoBase64": "String"
}
```

### Delete dish

Removes dish from database by dishName. 

Local path - _localhost:8080/menu/dish/delete/{dishName}_ 

example - _localhost:8080/menu/dish/delete/Pancakes_
