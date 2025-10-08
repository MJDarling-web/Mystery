package web;

import dto.DraftSaver;
import dto.StoryDraft;
import entity.Story;
import entity.User;
import persistence.GenericDao;
import dto.DraftSaver;
import service.HibernateDraftSaver;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Host/hostEditFinishedStory")
public class HostEditFinishedStoryServlet extends HttpServlet {

    DraftSaver draftSaver;

    @Override
    public void init() {
        if (draftSaver == null) {
            // use the DAO-injecting so tests can mirror it
            draftSaver = new HibernateDraftSaver(new GenericDao<>(Story.class));
            // draftSaver = new HibernateDraftSaver();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        StoryDraft draft = (StoryDraft) req.getSession().getAttribute("draft");
        if (draft == null) {
            resp.sendRedirect(req.getContextPath() + "/Host/hostCreateNewStory");
            return;
        }
        req.setAttribute("draft", draft);
        req.getRequestDispatcher("/jsp/Host/hostEditFinishedStory.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Pull the draft from session
        StoryDraft draft = (StoryDraft) req.getSession().getAttribute("draft");
        if (draft == null) {
            resp.sendRedirect(req.getContextPath() + "/Host/hostCreateNewStory");
            return;
        }

        // temporary creator no auth atm
        User creator = ensureTempCreator();

        int newId = draftSaver.save(draft, creator);
        req.getSession().removeAttribute("draft");
        resp.sendRedirect(req.getContextPath() + "/Host/adminDashboard?createdId=" + newId);
    }
        private User ensureTempCreator() {
            GenericDao<User> userDao = new GenericDao<>(User.class);

            // simplest approach: insert a new temp user each time (fine for now).
            // If you already have a findByPropertyEqual(), you could re-use the same one.
            String uname = "temp_host_" + System.currentTimeMillis();
            User u = new User(uname, "placeholder", uname + "@example.com", true);
            int id = userDao.insert(u);
            return userDao.getById(id);
        }

        /* Pulls current user (creator) from session
        User creator = (User) req.getSession().getAttribute("user");
        if (creator == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        // Persist and get new Story id
        int newId = draftSaver.save(draft, creator);

        // Clear the draft and redirect with id (PRG)
        req.getSession().removeAttribute("draft");
        resp.sendRedirect(req.getContextPath() + "/Host/adminDashboard?createdId=" + newId);
    }

         */
}
