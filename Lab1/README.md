# Java Technologies Laboratories
## Author: Crainic Diana-Isabela, 1st Year Master Software Engineering

[Java Servlet Technology Lab1](https://profs.info.uaic.ro/~acf/tj/labs/lab_01.html)

Tasks Done:
- Create a servlet that:
     - receives the following parameters key:String, value:int, mock:boolean and sync:boolean.
  - if mock is true than the servlet simply returns a confirmation message.
  - if mock is false, the servlet writes in a text file called repository a line containing the key, repeated value times, along with the timestamp of the request, and returns the content of the repository, as an HTML page containing all the lines that were created, ordered by key.
  - if sync is false, then the servlet will not use any synchronized method when writing in the file.

The servlet invocation will be done using a simple HTML form.

Write in the server log the following information about each request: the HTTP method used, the IP-address of the client, the user-agent, the client language(s) and the parameters of the request. (Take a look at HttpServletRequest API).

- Invoke the service from a desktop application (**Python**).
In this case, the servlet must respond with a simple text, instead of an HTML page.