package web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(urlPatterns = {"/Players/playersSubmitFoundClue"})
public class PlayersSubmitFoundClueServlet extends HttpServlet {

    //Get request to show form to enter clue pin
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    // checks that storyId is still in session otherwise
        Integer storyId = (Integer) req.getSession().getAttribute("currentStoryId");
        if (storyId == null) {
            resp.sendRedirect(req.getContextPath() + "/storyLookup");
            return;
        }
        req.getRequestDispatcher("/jsp/Players/playersSubmitFoundClue.jsp").forward(req, resp);
    }

    //Post submission of clue pin
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Integer storyId = (Integer) req.getSession().getAttribute("currentStoryId");
        //TODO should this be refactored if its in here more than once?
        if (storyId == null) {
            resp.sendRedirect(req.getContextPath() + "storyLookup");
            return;
        }

        //checks if clue exists
        String clueCode = req.getParameter("clueCode");
        if (clueCode == null || clueCode.isBlank()) {
            req.setAttribute("error", "Sorry that clue can't be found");
            req.getRequestDispatcher("/jsp/Players/playersSubmitFoundClue.jsp").forward(req, resp);
            return;
        }

        // TODO: validate/persist the found clue for this story (and player if track)
        int clueId = Integer.parseInt(req.getParameter("clueCode"));
        var clueDao = new persistence.GenericDao<>(entity.Clue.class);
        entity.Clue clue = clueDao.getById(clueId);

        if (clue != null && clue.getStory().getId() == storyId) {
            clue.setFound(true);
            clueDao.update(clue);
        }
        // After success, redirects back to dashboard
        resp.sendRedirect(req.getContextPath() + "/Players/playersDashboard");
    }
}

