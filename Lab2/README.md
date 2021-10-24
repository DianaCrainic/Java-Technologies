# [Web Components Lab2](https://profs.info.uaic.ro/~acf/tj/labs/lab_02.html)

Tasks Done:
- Create a Web application containing the following components:
  - input.jsp: a page containing a form for introducing a record, i.e. a triple containing a category, a key and a value. (The key may be a word and the value may be its definition). The categories are not static, being read from a server-side component (an object);
  - result.jsp a page describing the response that will be delivered to the client, for example an HTML table containing the records stored on the server.
  - an object-oriented domain model;
  - a server-side component responsible with the business-logic of the application: writing the record to a server-side data structure, reading data from it, etc.
  - a server-side component responsible with controlling the web-flow.
The purpose of the application is to integrate various components, each having a specialized role.

- Create the following **web filters**:
  - A web filter that will log all requests received by input.jsp.
  - A web filter that will decorate the response by adding a specific prelude (at the beginning) and a specific coda (at the end) to the generated HTML page.
Important: we assume that the pages are already created and the functionalities described above cannot be implemented by modifying them directly.

- Create a **web listener** that reads a default category specified as a context init parameter at the application start-up. This default value should be stored in an attribute having application scope and it will be used whenever the request does not contain a category.

- Use a "hand-made" **cookie** to store the category selected by the client. When the user returns to the site (after the current session was invalidated) and presents this cookie, the category will be set automatically.