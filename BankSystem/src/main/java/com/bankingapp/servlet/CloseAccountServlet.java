package com.bankingapp.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bankingapp.util.DBConnection;

@WebServlet("/CloseAccountServlet")
public class CloseAccountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int accountNo = (Integer) request.getSession().getAttribute("accountNo");

        try (Connection conn = DBConnection.getConnection()) {
            String sql1 = "SELECT balance FROM Accounts WHERE account_no = ?";
            PreparedStatement stmt1 = conn.prepareStatement(sql1);
            stmt1.setInt(1, accountNo);
            ResultSet rs = stmt1.executeQuery();
            if (rs.next()) {
                double balance = rs.getDouble("balance");
                if (balance == 0) {
                    String sql2 = "DELETE FROM Transactions WHERE account_no = ?";
                    PreparedStatement stmt2 = conn.prepareStatement(sql2);
                    stmt2.setInt(1, accountNo);
                    stmt2.executeUpdate();

                    String sql3 = "DELETE FROM Accounts WHERE account_no = ?";
                    PreparedStatement stmt3 = conn.prepareStatement(sql3);
                    stmt3.setInt(1, accountNo);
                    stmt3.executeUpdate();

                    String sql4 = "DELETE FROM Login WHERE account_no = ?";
                    PreparedStatement stmt4 = conn.prepareStatement(sql4);
                    stmt4.setInt(1, accountNo);
                    stmt4.executeUpdate();

                    request.getSession().invalidate();
                    response.sendRedirect("accountClosed.jsp");
                } else {
                    response.sendRedirect("closeAccount.jsp?error=Withdraw all money before closing account");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
