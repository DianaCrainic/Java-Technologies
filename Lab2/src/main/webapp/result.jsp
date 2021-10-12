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
    <style>
        <%@ include file="/style.css" %>
    </style>
</head>
<body>

<div>
    <h2>Records:</h2>
    <% List<Device> devices = (List<Device>) request.getAttribute("devices");%>
    <table id="devices">
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
</div>
</body>
</html>
