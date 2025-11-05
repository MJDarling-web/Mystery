package web;

import entity.Character;
import entity.Story;
import org.hibernate.NonUniqueResultException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import persistence.SessionFactoryProvider;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import java.io.IOException;

@WebServlet(urlPatterns = {"/Players/playersFinalGuessEntered"})
public class playersFinalGuessEnteredServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Integer storyId = (Integer) req.getSession().getAttribute("currentStoryId");
        if (storyId == null) {
            resp.sendRedirect(req.getContextPath() + "/storyLookup");
            return;
        }

        String suspectParam = req.getParameter("suspect");
        if (suspectParam == null || suspectParam.isBlank()) {
            resp.sendRedirect(req.getContextPath() + "/Players/PlayersSubmitFinalGuess");
            return;
        }

        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();

            Story story = session.get(Story.class, storyId);
            if (story == null) {
                tx.rollback();
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Story not found.");
                return;
            }

            Character murderer;
            try {
                murderer = session.createQuery(
                                "from Character c where c.story.id = :sid and c.isGuilty = true",
                                Character.class)
                        .setParameter("sid", storyId)
                        .uniqueResult();
            } catch (NonUniqueResultException e) {
                tx.rollback();
                resp.sendError(HttpServletResponse.SC_CONFLICT,
                        "Multiple guilty characters found for this story.");
                return;
            }

            if (murderer == null) {
                tx.rollback();
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST,
                        "No murderer configured for this story.");
                return;
            }

            Character guessed = null;
            try {
                Integer suspectId = Integer.valueOf(suspectParam);
                Character byId = session.get(Character.class, suspectId);
                if (byId != null && byId.getStory() != null && byId.getStory().getId() == storyId) {
                    guessed = byId;
                }
            } catch (NumberFormatException ignored) { /* radio should provide an int id */ }

            String guessedName = (guessed != null) ? guessed.getName() : "(unknown suspect)";
            boolean isCorrect = (guessed != null) && (guessed.getId() == murderer.getId());

            tx.commit();

            req.setAttribute("guessedName", guessedName);
            req.setAttribute("actualMurdererName", murderer.getName());
            req.setAttribute("isCorrect", isCorrect);
        }

        RequestDispatcher rd = req.getRequestDispatcher("/jsp/Players/playersFinalGuessEntered.jsp");
        rd.forward(req, resp);
    }
}
