package web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(urlPatterns = {"/Players/PlayersSubmitFinalGuess"})
public class PlayersSubmitFinalGuessServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Integer storyId = (Integer) req.getSession().getAttribute("currentStoryId");
        if (storyId == null) {
            resp.sendRedirect(req.getContextPath() + "/storyLookup");
            return;
        }
        req.getRequestDispatcher("/jsp/Players/PlayersSubmitFinalGuess.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Integer storyId = (Integer) req.getSession().getAttribute("currentStoryId");
        if (storyId == null) {
            resp.sendRedirect(req.getContextPath() + "/storyLookup");
            return;
        }

        String guess = req.getParameter("guess");
        if (guess == null || guess.isBlank()) {
            req.setAttribute("error", "Please enter your final guess.");
            req.getRequestDispatcher("/jsp/Players/PlayersSubmitFinalGuess.jsp").forward(req, resp);
            return;
        }

        // TODO: persist the final guess (and associate with player/story)

        // After success, redirect back to dashboard
        resp.sendRedirect(req.getContextPath() + "/jsp/Players/playersDashboard");
    }
}
