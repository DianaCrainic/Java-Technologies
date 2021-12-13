# [Contexts and Dependency Injection (CDI) - Lab7](https://profs.info.uaic.ro/~acf/tj/labs/lab_07.html)

### Tasks Done:
1. JSF application for managing the submission of documents into a repository
- **Authentication** mechanism based on username and password;
- **Registration** mechanism for creating users and assign them a specific role (admin, author);
- Specify a **time frame**, in which registration is open for users and submissions;
- The possibility to **upload a document** - for authors;
- The possibility to **view all uploaded documents** - for admin;
- All submissions are **logged** in a text file.

2. **Contexts and Dependency Injection (CDI)**
- **@Inject** and **@Transactional** for the management of application's beans and transactions;
- **@Produces** for decoupling the components using dependency injection 
  - Used a producer method to generate registration numbers for uploaded documents;
- **@Interceptor** for decoupling orthogonal concerns 
  - Used for logging document submissions;
  - Used to validate the registrations and submissions in valid time frames;
- **@Decorator** for decoupling business concerns such as showing current time;
- **@Observes** for event-based communication 
  - Used for notifying when a document is uploaded and updates the list of documents;
- **@NotNull** and **@Size** for data validation for registration.

3. Created Unit Tests for entities.