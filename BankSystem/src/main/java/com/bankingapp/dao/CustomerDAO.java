package com.bankingapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bankingapp.model.Customer;
import com.bankingapp.util.DBConnection;

public class CustomerDAO {

    public boolean addCustomer(Customer customer) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO Customer (full_name, address, mobile_no, email_id, account_type, dob, id_proof) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, customer.getFullName());
            stmt.setString(2, customer.getAddress());
            stmt.setString(3, customer.getMobileNo());
            stmt.setString(4, customer.getEmailId());
            stmt.setString(5, customer.getAccountType());
            stmt.setDate(6, customer.getDob());
            stmt.setString(7, customer.getIdProof());

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    int customerId = rs.getInt(1);
                    customer.setCustomerId(customerId);
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Customer getCustomerById(int customerId) {
        Customer customer = null;
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM Customer WHERE customer_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                customer = new Customer();
                customer.setCustomerId(rs.getInt("customer_id"));
                customer.setFullName(rs.getString("full_name"));
                customer.setAddress(rs.getString("address"));
                customer.setMobileNo(rs.getString("mobile_no"));
                customer.setEmailId(rs.getString("email_id"));
                customer.setAccountType(rs.getString("account_type"));
                customer.setDob(rs.getDate("dob"));
                customer.setIdProof(rs.getString("id_proof"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }
    
    public boolean resetPassword(String accountNumber, String tempPassword, String newPassword) {
        try (Connection conn = DBConnection.getConnection()) {
            String query = "SELECT password FROM customers WHERE account_number = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, accountNumber);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                String storedPassword = rs.getString("password");

                if (storedPassword.equals(tempPassword)) {
                    String updateQuery = "UPDATE customers SET password = ? WHERE account_number = ?";
                    PreparedStatement updateStatement = conn.prepareStatement(updateQuery);
                    updateStatement.setString(1, newPassword);
                    updateStatement.setString(2, accountNumber);
                    updateStatement.executeUpdate();
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateCustomer(Customer customer) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "UPDATE Customer SET full_name = ?, address = ?, mobile_no = ?, email_id = ?, account_type = ?, dob = ?, id_proof = ? WHERE customer_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, customer.getFullName());
            stmt.setString(2, customer.getAddress());
            stmt.setString(3, customer.getMobileNo());
            stmt.setString(4, customer.getEmailId());
            stmt.setString(5, customer.getAccountType());
            stmt.setDate(6, customer.getDob());
            stmt.setString(7, customer.getIdProof());
            stmt.setInt(8, customer.getCustomerId());

            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            String query = "SELECT * FROM customers";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Customer customer = new Customer();
                customer.setCustomerId(rs.getInt("customer_id"));
                customer.setFullName(rs.getString("full_name"));
                customer.setAddress(rs.getString("address"));
                customer.setMobileNo(rs.getString("mobile_no"));
                customer.setEmailId(rs.getString("email_id"));
                customer.setAccountType(rs.getString("account_type"));
                customer.setDob(rs.getDate("dob"));
                customer.setIdProof(rs.getString("id_proof"));
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    public boolean deleteCustomer(int customerId) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "DELETE FROM Customer WHERE customer_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, customerId);
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
