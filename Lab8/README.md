# [Web Services - Lab8](https://profs.info.uaic.ro/~acf/tj/labs/lab_08.html)

### Tasks Done:
1. Created **RESTful Web Services** using **JAX-RS**
- **GET - ViewDocumentService** that returns a "list" of the documents there were uploaded. The parameter of the web method is the identifier of the user. If the parameter is null, then all documents are returned.
- **POST - AddDocumentService** that allows adding a new document;
- **PUT - UpdateDocumentService** that allows replacing an existing document;
- **DELETE - DeleteDocumentService** that allows deleting an existing document from the database;
- JSON was used for representing consumed or produced data;
- ViewDocumentService was documented using **OpenAPI** (_openapi.yaml_);
- Unit tests were created for ViewDocumentService.

2. Created a **filter** that will act as a cache for the ViewDocumentService.