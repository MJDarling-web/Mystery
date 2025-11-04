package web;

import entity.Character;
import entity.Story;
import org.hibernate.Session;
import org.hibernate.Transaction;
import persistence.SessionFactoryProvider;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

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

        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();

            List<Character> characters = session.createQuery(
                            "select c from Character c where c.story.id = :sid order by c.name",
                            Character.class)
                    .setParameter("sid", storyId)
                    .getResultList();

            tx.commit();
            req.setAttribute("characters", characters);
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

        String suspectId = req.getParameter("suspect");
        if (suspectId == null || suspectId.isBlank()) {
            req.setAttribute("error", "Please select a suspect.");
            doGet(req, resp);
            return;
        }

        // (Optional) Persist guess + reason here

        // Redirect to results page with suspect id
        resp.sendRedirect(req.getContextPath()
                + "/Players/playersFinalGuessEntered?suspect=" + suspectId);
    }
}
