<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.bankingapp.dao.CustomerDAO, com.bankingapp.model.Customer" %>
<%
    int customerId = Integer.parseInt(request.getParameter("id"));
    CustomerDAO customerDAO = new CustomerDAO();
    Customer customer = customerDAO.getCustomerById(customerId);
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Customer</title>
</head>
<body>
    <h1>Edit Customer</h1>
    <form action="updateCustomer" method="post">
        <input type="hidden" name="customerId" value="<%= customer.getCustomerId() %>">
        
        <label for="fullName">Full Name:</label>
        <input type="text" id="fullName" name="fullName" value="<%= customer.getFullName() %>" required><br>

        <label for="address">Address:</label>
        <input type="text" id="address" name="address" value="<%= customer.getAddress() %>" required><br>

        <label for="mobileNo">Mobile No:</label>
        <input type="text" id="mobileNo" name="mobileNo" value="<%= customer.getMobileNo() %>" required><br>

        <label for="emailId">Email ID:</label>
        <input type="email" id="emailId" name="emailId" value="<%= customer.getEmailId() %>" required><br>

        <label for="accountType">Account Type:</label>
        <select id="accountType" name="accountType">
            <option value="Saving" <%= customer.getAccountType().equals("Saving") ? "selected" : "" %>>Saving Account</option>
            <option value="Current" <%= customer.getAccountType().equals("Current") ? "selected" : "" %>>Current Account</option>
        </select><br>

        <label for="dob">Date of Birth:</label>
        <input type="date" id="dob" name="dob" value="<%= customer.getDob() %>" required><br>

        <label for="idProof">ID Proof:</label>
        <input type="text" id="idProof" name="idProof" value="<%= customer.getIdProof() %>" required><br>

        <input type="submit" value="Update Customer">
    </form>
</body>
</html>
