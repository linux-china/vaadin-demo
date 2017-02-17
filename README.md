Vaadin Spring Boot management UI Demo
=====================================

Vaadin Spring Boot Demo to display Vaadin UI on management port

### Vaadin Admin Uri list

Display Vaadin UI through management port. http://localhost:8081/admin/admin2

### How display Vaadin UI to management port

* Create Servlet Wrapping  MVC Endpoint
* Forward to Vaadin UI
* Create interceptor to block visit admin uri list from server.port