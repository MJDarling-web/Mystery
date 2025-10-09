package web;

import entity.Story;
import persistence.GenericDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.UUID;

@WebServlet("/Players/storyLookup")
public class StoryLookupServlet extends HttpServlet {

    private GenericDao<Story> storyDao;

    @Override
    public void init() throws ServletException {
        storyDao = new GenericDao<>(Story.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/Players/storyLookup.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String storyIdStr = req.getParameter("storyId");
        if (storyIdStr == null || storyIdStr.isBlank()) {
            req.setAttribute("error", "Please enter a story ID.");
            req.getRequestDispatcher("/jsp/Players/storyLookup.jsp").forward(req, resp);
            return;
        }

        int storyId;
        try {
            storyId = Integer.parseInt(storyIdStr.trim());
        } catch (NumberFormatException e) {
            req.setAttribute("error", "Story ID must be a number.");
            req.getRequestDispatcher("/jsp/Players/storyLookup.jsp").forward(req, resp);
            return;
        }

        Story story = storyDao.getById(storyId);
        if (story == null) {
            req.setAttribute("error", "No story found for that ID.");
            req.getRequestDispatcher("/jsp/Players/storyLookup.jsp").forward(req, resp);
            return;
        }

        HttpSession session = req.getSession(true);
        session.setAttribute("currentStoryId", story.getId());
        session.setAttribute("gameSessionId", UUID.randomUUID().toString());

        // Redirect to a servlet (recommended), not directly to a JSP:
        resp.sendRedirect(req.getContextPath() + "/Players/playersDashboard");
    }
}
