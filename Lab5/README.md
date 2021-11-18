# [Java Persistence API (JPA) - Lab5](https://profs.info.uaic.ro/~acf/tj/labs/lab_05.html)

Tasks Done:
1. Rewrited the persistence layer of the application created for the previous laboratory using a technology that implements the **JPA specifications**.
    - Defined the persistence unit using a data source configured as a JDBC Resource.
    - Created the EntityManager objects using dependency injection.
    - Defined the mappings using JPA-only annotations.
    - Implemented the repository classes using JPA-QL.
    - Created unit tests for Student entity.

2. Defined 2 types of classes representing exams: **Project Presentation** and **Written Tests**. Used **Inheritance Mapping** in order to define this new model. Adapted the user interface accordingly.

3. Added an "Exam search page". Exams can be **filtered** by start date and/or end date. Each filter will have a checkox - if it is checked then the filter will be taken into consideration. The dynamic queries are implemented using **JPA Criteria API**.

4. Intuitive design and interactive app.