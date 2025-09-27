package web;

import entity.Story;
import persistence.GenericDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@WebServlet(urlPatterns = {"/Players/storyLookup"})
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

        String gameCode = req.getParameter("gameCode");
        if (gameCode == null || gameCode.isBlank()) {
            req.setAttribute("error", "Please enter your party code.");
            req.getRequestDispatcher("/jsp/Players/storyLookup.jsp").forward(req, resp);
            return;
        }

        // Example: lookup by gameCode field
        List<Story> matches = storyDao.getByPropertyEqual("gameCode", gameCode.trim());
        if (matches.isEmpty()) {
            req.setAttribute("error", "No game found for that code.");
            req.getRequestDispatcher("/jsp/Players/storyLookup.jsp").forward(req, resp);
            return;
        }

        Story story = matches.get(0);

        // Session attributes
        HttpSession session = req.getSession(true);
        session.setAttribute("currentStoryId", story.getId());      // adjust getter
        session.setAttribute("gameSessionId", UUID.randomUUID().toString());

        // Optional: if you create a player record here, store playerId too.
        // session.setAttribute("playerId", player.getId());

        resp.sendRedirect(req.getContextPath() + "jsp/Players/playersDashboard");
    }
}
