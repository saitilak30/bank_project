<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View Account</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <div class="container">
        <h1>Account Details</h1>
        <table>
            <tr><th>Full Name</th><td>${customer.fullName}</td></tr>
            <tr><th>Address</th><td>${customer.address}</td></tr>
            <tr><th>Mobile Number</th><td>${customer.mobileNumber}</td></tr>
            <tr><th>Email</th><td>${customer.email}</td></tr>
            <tr><th>Account Type</th><td>${customer.accountType}</td></tr>
            <tr><th>Balance</th><td>${customer.initialBalance}</td></tr>
            <tr><th>Date of Birth</th><td>${customer.dob}</td></tr>
            <tr><th>ID Proof</th><td>${customer.idProof}</td></tr>
        </table>
        <a href="customerDashboard.jsp">Back to Dashboard</a>
    </div>
</body>
</html>
