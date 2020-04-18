package pl.coderslab.util;

import pl.coderslab.util.DbUtil;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;
@WebServlet("/dbTest")
public class DbTestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Connection connection = DbUtil.getConnection();
            PreparedStatement ps = connection.prepareStatement("INSERT INTO TEST VALUES (?)");
            ps.setInt(1, new Random().nextInt());
            ps.execute();
            resp.getWriter().append("Dodano wpis");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}