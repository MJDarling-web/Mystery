package web;

import entity.Story;
import entity.Scene;      // adjust package names
import entity.Clue;
import entity.Character;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import persistence.SessionFactoryProvider;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/Players/playersDashboard"})
public class playersDashboardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Integer storyId = (Integer) req.getSession().getAttribute("currentStoryId");
        if (storyId == null) {
            resp.sendRedirect(req.getContextPath() + "/story-lookup");
            return;
        }

        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        try {
            Story story = session.get(Story.class, storyId);
            if (story == null) {
                tx.commit();
                resp.sendRedirect(req.getContextPath() + "/story-lookup");
                return;
            }

            // Ensure collections are usable after session closes
            Hibernate.initialize(story.getScenes());      // List<Scene>
            Hibernate.initialize(story.getCharacters());  // List<Character>

            //query clues
            List<Clue> foundClues = session.createQuery(
                            "from Clue c where c.story.id = :sid order by c.id asc", Clue.class)
                    .setParameter("sid", storyId)
                    .getResultList();

            req.setAttribute("story", story);
            req.setAttribute("unlockedScenes", story.getScenes());
            req.setAttribute("foundClues", foundClues);
            req.setAttribute("characters", story.getCharacters());

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw new ServletException(e);
        } finally {
            session.close();
        }

        req.getRequestDispatcher("/jsp/Players/playersDashboard.jsp").forward(req, resp);
    }
}
