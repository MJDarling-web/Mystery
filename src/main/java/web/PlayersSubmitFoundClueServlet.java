package web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(urlPatterns = {"/Players/playersSubmitFoundClue"})
public class PlayersSubmitFoundClueServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Integer storyId = (Integer) req.getSession().getAttribute("currentStoryId");
        if (storyId == null) {
            resp.sendRedirect(req.getContextPath() + "/story-lookup");
            return;
        }
        req.getRequestDispatcher("/jsp/Players/playersSubmitFoundClue.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Integer storyId = (Integer) req.getSession().getAttribute("currentStoryId");
        if (storyId == null) {
            resp.sendRedirect(req.getContextPath() + "/story-lookup");
            return;
        }

        String clueCode = req.getParameter("clueCode");
        if (clueCode == null || clueCode.isBlank()) {
            req.setAttribute("error", "Enter a clue code.");
            req.getRequestDispatcher("/jsp/Players/playersSubmitFoundClue.jsp").forward(req, resp);
            return;
        }

        // TODO: validate/persist the found clue for this story (and player if you track it)

        // After success, redirect back to dashboard
        resp.sendRedirect(req.getContextPath() + "jsp/Players/playersDashboard");
    }
}

