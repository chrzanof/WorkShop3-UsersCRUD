package pl.coderslab.users;

import classes.User;
import classes.UserDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/user/edit")
public class UserEdit extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao userDao = new UserDao();
        String parameter = request.getParameter("id");
        int intParameter = Integer.parseInt(parameter);
        User user = userDao.read(intParameter);
        request.setAttribute("user", user);
        getServletContext().getRequestDispatcher("/users/edit.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao dao = new UserDao();
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User user = new User();
        user.setId(id);
        user.setUserName(name);
        user.setEmail(email);
        user.setPassword(password);
        dao.update(user);
        response.sendRedirect(request.getContextPath() + "/user/list");


    }
}
