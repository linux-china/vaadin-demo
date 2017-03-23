Vaadin Spring Boot management UI Demo
=====================================

Vaadin Spring Boot Demo to display Vaadin UI on management port

### Vaadin Admin Uri list

Display Vaadin UI with @SpringManagementOnly annotation through management port. http://localhost:8081/admin/admin2

### How display Vaadin UI to management port

* Create Servlet Wrapping  MVC Endpoint
* Forward to Vaadin UI
* Create interceptor to block visit admin vaadin UI list from server.port

### Glossary

* UI(view切换) -> View(包含业务逻辑) -> XxxDesign(手动或者designer生成，负责UI)


### References

* Vaadin Charts Demo:  https://demo.vaadin.com/charts/