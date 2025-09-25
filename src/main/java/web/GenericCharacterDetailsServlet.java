package web;

import entity.Character; // adjust package if needed
import org.hibernate.Session;
import org.hibernate.Transaction;
import persistence.SessionFactoryProvider;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(urlPatterns = {"/Players/characters"})
public class GenericCharacterDetailsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Integer storyId = (Integer) req.getSession().getAttribute("currentStoryId");
        if (storyId == null) {
            resp.sendRedirect(req.getContextPath() + "/story-lookup");
            return;
        }

        String idParam = req.getParameter("id");
        if (idParam == null) {
            req.setAttribute("error", "Character id is required.");
            req.getRequestDispatcher("/jsp/Players/GenericCharacterDetails.jsp").forward(req, resp);
            return;
        }

        Integer charId = Integer.valueOf(idParam);

        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        try {
            Character ch = session.get(Character.class, charId);
            // (Optional) verify ch.getStory().getId().equals(storyId)
            req.setAttribute("character", ch);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw new ServletException(e);
        } finally {
            session.close();
        }

        req.getRequestDispatcher("/jsp/Players/GenericCharacterDetails.jsp").forward(req, resp);
    }
}
