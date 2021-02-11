# Create a micro-services oriented application

## Services

The idea is to develop an application which is an e-commerce shop.
It keeps track of products, inventory, orders, payment.
Shows the products on a website. 

### Customer management (CRM)

This micro application is the backend for keeping track of customers.
It will support CRUD operations on customers as well as a search capability on any data of a customer.
C: Add a new customer
R: Give current data of a customer
U: Change/add an address, change name, email, etc.
D: Remove yourself from the system. Only allowed by a customer for itself, not for others.

### Inventory management

This micro application is the backend for keeping track of products and inventory.
It will support CRUD operations for Products as well as a search capability on any data of a product.
It will also support inventory management for the available Products.

C: New product
R: Give details about a product and its availability in inventory
U: Update the product details / update its availability in the inventory
D: Remove a product & update its corresponding availability in the inventory.

### Order

This micro application is the backend for keeping track of orders made by customers.
It will support CRUD operations on orders.
C: new order
R: what is the current status of my order
U: Change the address, quantity of a product
D: Cancel the order (only possible before payment process has started)


### Payment

A micro service giving the ability to process payment.
Within this application used to process the payment for an order.

It should supply a trigger to the requestee for completion of the payment.

Usage of both webhooks (url supplied during Create action) and messages should be possible and configurable.
Try usage of the Decorator pattern: Notifier base class, decorated with a WebHookNotifier and/or MessageNotifier.

CRUD abilities:
C: Start a new payment
R: Supply current status of a payment
U: NOT AVAILABLE
D: NOT AVAILABLE



## Code structure

Have a seperate module for the domain data objects.
All services can use this module to load the data objects.
Actual JPA data entities are to be created in the micro-service responsible for maintaining them, could be simply an extends of the domain data object?
