<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, com.bankingapp.dao.*, com.bankingapp.model.*" %>
<%
    

    // Fetch customer list
    CustomerDAO customerDAO = new CustomerDAO();
    List<Customer> customers = customerDAO.getAllCustomers();
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
    <h1>Admin Dashboard</h1>
    <a href="registerCustomer.jsp">Add New Customer</a> | <a href="logout.jsp">Logout</a>
    <h2>Customer List</h2>
    <table border="1">
        <tr>
            <th>Customer ID</th>
            <th>Full Name</th>
            <th>Address</th>
            <th>Mobile No</th>
            <th>Email ID</th>
            <th>Account Type</th>
            <th>Date of Birth</th>
            <th>ID Proof</th>
            <th>Actions</th>
        </tr>
        <%
            for (Customer customer : customers) {
        %>
        <tr>
            <td><%= customer.getCustomerId() %></td>
            <td><%= customer.getFullName() %></td>
            <td><%= customer.getAddress() %></td>
            <td><%= customer.getMobileNo() %></td>
            <td><%= customer.getEmailId() %></td>
            <td><%= customer.getAccountType() %></td>
            <td><%= customer.getDob() %></td>
            <td><%= customer.getIdProof() %></td>
            <td>
                <a href="viewCustomer.jsp?id=<%= customer.getCustomerId() %>">View</a> | 
                <a href="resetPassword.jsp?id=<%= customer.getCustomerId() %>">Reset Password</a> |
                <a href="editCustomer.jsp?id=<%= customer.getCustomerId() %>">Edit</a> | 
                <a href="closeAccount?id=<%= customer.getCustomerId() %>" onclick="return confirm('Are you sure you want to close this customer account?');">Delete</a>
            </td>
        </tr>
        <%
            }
        %>
    </table>
</body>
</html>
