package web;

import entity.Story;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import persistence.SessionFactoryProvider;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/players")
public class PlayersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // ✅ same key used by StoryLookupServlet
        Integer storyId = (Integer) req.getSession().getAttribute("currentStoryId");
        if (storyId == null) {
            resp.sendRedirect(req.getContextPath() + "/storyLookup");
            return;
        }

        /*
        * loads the story
        */
        Story story;
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        try {
            story = session.get(Story.class, storyId);
            tx.commit();
        } finally {
            session.close();
        }

        /*
         * if it vanished, clear the same key and bounce back
         */
        if (story == null) {
            req.getSession().removeAttribute("currentStoryId");
            resp.sendRedirect(req.getContextPath() + "/storyLookup");
            return;
        }
        req.setAttribute("currentStoryID", storyId);
        req.setAttribute("story", story);
        req.getRequestDispatcher("/jsp/players.jsp").forward(req, resp);
    }
}
