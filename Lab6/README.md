# [Enterprise Java Beans (EJB) - Lab6](https://profs.info.uaic.ro/~acf/tj/labs/lab_06.html)

### Tasks Done:
- Persistence layer using **EJB**
    - Transactions were implemented using EJB technology
- Reservation page with resources
    - **DataResourcesViewBackingBean** - A stateless session bean that offers methods for checking the availability of a resource.;
    - **ResourceAssignmentBackingBean** - A stateful session bean responsible with the assignment of one or more resource to a specific exam. The assignment should be atomic, either all resources are successfully assigned, or the transaction will be rolled back.;
    - **CurrentAssignmentsBackingBean** - A singleton session bean that keeps an in-memory map of the current assignments. The map will be instantiated at application startup and updated whenever the assignments change.
- Created more unit tests for all entities.