/*
* Servlet to pull all Host stories for viewing details and
* forward to Final Story page with edit/delete options on Final Story page.
*
*/
package web;

import entity.Story;
import persistence.GenericDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/adminDashboard")
public class AdminDashboardServlet extends HttpServlet {

    private GenericDao<Story> storyDao;

    @Override
    public void init() throws ServletException {
        storyDao = new GenericDao<>(Story.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/adminDashboard.jsp").forward(req, resp);
    }

}