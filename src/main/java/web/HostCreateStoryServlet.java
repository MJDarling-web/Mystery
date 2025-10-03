package web;

import dto.StoryDraft;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import javax.servlet.ServletException;

@WebServlet("/Host/hostCreateNewStory")
public class HostCreateStoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // show the form
        req.getRequestDispatcher("/jsp/Host/hostCreateNewStory.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        String title = req.getParameter("title");
        String description = req.getParameter("description");
        String setting = req.getParameter("setting");

        // Validate
        if (title == null || title.isBlank()) {
            req.setAttribute("error", "Title is required.");
            // forwarding to JSP path
            req.getRequestDispatcher("/jsp/Host/hostCreateNewStory.jsp").forward(req, resp);
            return;
        }

        StoryDraft draft = new StoryDraft(title, description, setting);

        HttpSession session = req.getSession(true);
        session.setAttribute("storyDraft", draft);

        // direct to create new scenes
        resp.sendRedirect(req.getContextPath() + "/Host/hostCreateScenes");
    }

}