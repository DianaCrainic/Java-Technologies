<%@ page import="com.uaic.lab2.model.Device" %><%--
  Created by IntelliJ IDEA.
  User: diana
  Date: 07-Oct-21
  Time: 18:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.uaic.lab2.model.CategoryType" %>

<html>
<head>
    <title>Lab2</title>

    <style>
        <%@ include file="/style.css" %>
    </style>

</head>
<body>
<div>
    <h2>Add a record:</h2>
    <form action="controller">
        <label> Category: </label>
        <select name="category" id="category">
            <option value=""></option>
            <%
                for (CategoryType categoryType : CategoryType.values()) {
            %>
            <option value="<%=categoryType%>">
                <%=categoryType%>
            </option>
            <%}%>
        </select><br><br>

        <label>Key: </label>
        <input type="text" name="key"/><br/><br/>

        <label>Value:</label>
        <input type="text" name="value"/><br/><br/>

        <input type="submit" value="Submit"/>
    </form>
</div>

</body>
</html>
