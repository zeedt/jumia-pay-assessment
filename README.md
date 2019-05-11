# jumia-pay-assessment

This is a simple appplication which is implemented based on the technologies recommended. These technologies include :
1. Java
2. Spring boot
3. NoSQL database
4. Message Broker

The major goal of this sample application is to persist audit details that would have been pushed to a queue. To makes testing easier, two endpoints are provided for user creation and also purchase. 

* The user creation endpoint creates the user based on information received and then send the details to the queue for proper auditing. This can be found in **UserController** class.

* Also, the purchase endpoint receives the details of the goods customer is paying for and sends the details to the queue for proper auditing. This can be found in **PurchaseController** class.
 
* MongoDb is used as the NoSQL database.

* The application makes use of ActiveMQ for pushing messages to the queue and also retrieving messages from the queue. The broker information is in the properties file.

## Audit Details

The audit details include:

1. IP Address
2. Actor
3. Date performed
4. Action type
5. Action description
6. Initial Data (Incase of update)
7. Final Data.


## Audit Retrieval

The audited records can be retrieved with this endpoint /api/v1/audit. It is a post method. 

The audit log can be filtered by the following fields. The supplied filter fields will be used to fetch the audit details.

{
	"pageNo" : 0,
	"pageSize" : 10,
	"actor" : "olamide",
  "actionDescription" : "",
  "actionType" : "",
  "fromDate" : "1548272700000",
  "toDate" : "1569267900000",
  "sortOrder" : "DESC",
  "sortField" : "actor"
}

1. pageNo : This represents the page number. This starts from 0.
2. pageSize : This represents the number of size to fetch per page.
3. actor : This is the user that performed the action.
4. actionDescription : This is the description of the action performed.
5. actionType : This is the type of action performed. e,g USER_CREATION, PURCHASE.
6. fromDate : The start date to search from.
7. toDate : The end date to search with.
8. sortOrder : Order to sort with. e.g ASC, DESC.
9. sortField : Field to sort with. e.g actor, ipAddress
