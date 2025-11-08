package web;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import persistence.SessionFactoryProvider;

import dto.StoryDraft;
import dto.CharacterDraft;
import dto.ScenesDraft;
import dto.ClueDraft;

import entity.Story;
import entity.Character;
import entity.Scene;
import entity.Clue;

import persistence.GenericDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/Host/startEdit")
public class HostStartEditServlet extends HttpServlet {

    private GenericDao<Story> storyDao;

    @Override
    public void init() throws ServletException {
        storyDao = new GenericDao<>(Story.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String idParam = req.getParameter("storyId");
        if (idParam == null) {
            resp.sendRedirect(req.getContextPath() + "/Host/adminDashboard");
            return;
        }

        int storyId;
        try {
            storyId = Integer.parseInt(idParam);
        } catch (NumberFormatException e) {
            resp.sendRedirect(req.getContextPath() + "/Host/adminDashboard");
            return;
        }

        // Load Story (make sure children are available; if your GenericDao leaves them LAZY,
        // either fetch-join in DAO or mark relationships EAGER temporarily).
        Story s = loadStoryWithChildren(storyId);

        if (s == null) {
            resp.sendRedirect(req.getContextPath() + "/Host/adminDashboard");
            return;
        }

        StoryDraft draft = mapStoryToDraft(s);

        HttpSession session = req.getSession();
        session.setAttribute("draft", draft);
        session.setAttribute("editingStoryId", storyId); // flag edit-mode

        resp.sendRedirect(req.getContextPath() + "/Host/hostEditFinishedStory");
    }

    private StoryDraft mapStoryToDraft(Story s) {
        StoryDraft d = new StoryDraft();
        d.setTitle(s.getTitle());
        d.setDescription(s.getDescription());
        d.setSetting(s.getSetting());

        // Characters
        for (Character c : s.getCharacters()) {
            CharacterDraft cd = new CharacterDraft();
            cd.setName(c.getName());
            cd.setRole(c.getRole());
            cd.setBio(c.getBio());
            cd.setGuilty(c.isGuilty());
            // your JSP shows "murdered" but entity doesn’t have it; default to false
            cd.setMurdered(false);
            cd.setImageUrl(c.getPictureUrl());
            d.addCharacter(cd);
        }

        // Scenes
        for (Scene sc : s.getScenes()) {
            ScenesDraft sd = new ScenesDraft();
            sd.setTitle(sc.getTitle());
            sd.setDescription(sc.getDescription());
            sd.setDisplayOrder(sc.getDisplayOrder());
            d.addScene(sd);
        }

        // Clues
        for (Clue cl : s.getClues()) {
            ClueDraft cd = new ClueDraft();
            cd.setTitle(cl.getTitle());
            cd.setDescription(cl.getDescription());
            cd.setImageUrl(cl.getImageUrl());
            // locationFound / isFound exist in entity but your review JSP doesn’t show them
            d.addClue(cd);
        }

        return d;
    }
    private Story loadStoryWithChildren(int id) {
        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            Story s = session.get(Story.class, id);
            if (s != null) {
                // Initialize collections while the session is open
                Hibernate.initialize(s.getCharacters());
                Hibernate.initialize(s.getScenes());
                Hibernate.initialize(s.getClues());
            }
            return s;
        }
    }
}
