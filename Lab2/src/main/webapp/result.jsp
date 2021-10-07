<%@ page import="java.util.List" %>
<%@ page import="com.uaic.lab2.model.Device" %><%--
  Created by IntelliJ IDEA.
  User: diana
  Date: 07-Oct-21
  Time: 18:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lab2</title>
</head>
<body>
<%--<h1> The parameters are: </h1>--%>
<%--<h2>Category: ${param['category']}</h2>--%>
<%--<h2>Key: ${param['key']}</h2>--%>
<%--<h2>Value: ${param['value']}</h2>--%>

<h1>The devices:</h1>

<% List<Device> devices = (List<Device>) request.getAttribute("devices");%>
<table border="1">
    <thead>
    <tr>
        <th>Category</th>
        <th>Key</th>
        <th>Value</th>
    </tr>
    </thead>
    <tbody>
    <% for (Device device : devices) {%>
    <tr>
        <td><%=device.getCategory()%>
        </td>
        <td><%=device.getKey()%>
        </td>
        <td><%=device.getValue()%>
        </td>
    </tr>
    <%} %>
    </tbody>

</table>
</body>
</html>
