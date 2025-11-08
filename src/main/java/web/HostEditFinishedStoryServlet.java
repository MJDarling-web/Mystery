package web;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import persistence.SessionFactoryProvider;

import dto.DraftSaver;
import dto.StoryDraft;
import dto.CharacterDraft;
import dto.ScenesDraft;
import dto.ClueDraft;

import entity.Story;
import entity.User;
import entity.Scene;
import entity.Clue;
import entity.Character;

import persistence.GenericDao;
import service.HibernateDraftSaver;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/Host/hostEditFinishedStory")
public class HostEditFinishedStoryServlet extends HttpServlet {

    private DraftSaver draftSaver;

    @Override
    public void init() {
        if (draftSaver == null) {
            draftSaver = new HibernateDraftSaver(new GenericDao<>(Story.class));
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

        HttpSession session = req.getSession();

        // Build a fresh draft from the posted form fields (so edits on this page are used)
        StoryDraft draft = buildDraftFromRequest(req);
        if (draft == null) {
            resp.sendRedirect(req.getContextPath() + "/Host/hostCreateNewStory");
            return;
        }

        Integer editingId = (Integer) session.getAttribute("editingStoryId");

        if (editingId != null) {
            // ===== UPDATE EXISTING STORY inside an open session (avoids LazyInitializationException) =====
            updateExistingStory(editingId, draft);

            // clear edit state & redirect
            session.removeAttribute("draft");
            session.removeAttribute("editingStoryId");
            resp.sendRedirect(req.getContextPath() + "/Host/adminDashboard?updatedId=" + editingId);
            return;
        }

        // ===== CREATE NEW STORY (existing behavior) =====
        User creator = ensureTempCreator();
        int newId = draftSaver.save(draft, creator);
        session.removeAttribute("draft");
        resp.sendRedirect(req.getContextPath() + "/Host/adminDashboard?createdId=" + newId);
    }

    /** Rebuilds a StoryDraft from the posted form fields on hostEditFinishedStory.jsp */
    private StoryDraft buildDraftFromRequest(HttpServletRequest req) {
        StoryDraft draft = new StoryDraft();
        draft.setTitle(req.getParameter("title"));
        draft.setDescription(req.getParameter("description"));
        draft.setSetting(req.getParameter("setting"));

        // --- Characters ---
        String[] names = req.getParameterValues("characterName");
        String[] bios  = req.getParameterValues("characterBio");
        String[] roles = req.getParameterValues("characterRole");
        String[] imgs  = req.getParameterValues("characterImageUrl");

        java.util.Set<Integer> guiltyIdx = new java.util.HashSet<>();
        String[] guiltyParams = req.getParameterValues("characterGuilty"); // indices for checked boxes
        if (guiltyParams != null) {
            for (String v : guiltyParams) {
                try { guiltyIdx.add(Integer.parseInt(v)); } catch (NumberFormatException ignored) {}
            }
        }

        if (names != null) {
            for (int i = 0; i < names.length; i++) {
                CharacterDraft cd = new CharacterDraft();
                cd.setName(names[i]);
                cd.setBio(bios != null && i < bios.length ? bios[i] : null);
                cd.setRole(roles != null && i < roles.length ? roles[i] : null);
                cd.setImageUrl(imgs != null && i < imgs.length ? imgs[i] : null);
                cd.setGuilty(guiltyIdx.contains(i));
                cd.setMurdered(false); // keep false unless you add this to entity
                draft.addCharacter(cd);
            }
        }

        // --- Scenes ---
        String[] sTitle = req.getParameterValues("sceneTitle");
        String[] sDesc  = req.getParameterValues("sceneDescription");
        String[] sOrder = req.getParameterValues("sceneOrder");
        if (sTitle != null) {
            for (int i = 0; i < sTitle.length; i++) {
                ScenesDraft sd = new ScenesDraft();
                sd.setTitle(sTitle[i]);
                sd.setDescription(sDesc != null && i < sDesc.length ? sDesc[i] : null);
                try {
                    sd.setDisplayOrder(sOrder != null && i < sOrder.length ? Integer.parseInt(sOrder[i]) : 0);
                } catch (NumberFormatException e) {
                    sd.setDisplayOrder(0);
                }
                draft.addScene(sd);
            }
        }

        // --- Clues ---
        String[] cTitle = req.getParameterValues("clueTitle");
        String[] cDesc  = req.getParameterValues("clueDescription");
        String[] cImg   = req.getParameterValues("clueImageUrl");
        if (cTitle != null) {
            for (int i = 0; i < cTitle.length; i++) {
                ClueDraft cd = new ClueDraft();
                cd.setTitle(cTitle[i]);
                cd.setDescription(cDesc != null && i < cDesc.length ? cDesc[i] : null);
                cd.setImageUrl(cImg != null && i < cImg.length ? cImg[i] : null);
                draft.addClue(cd);
            }
        }

        return draft;
    }

    /** Perform the edit/update while a Hibernate Session is open (fixes LAZY init issues). */
    private void updateExistingStory(int storyId, StoryDraft draft) {
        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            try {
                Story entity = session.get(Story.class, storyId);
                if (entity == null) {
                    tx.rollback();
                    return;
                }

                // Ensure collections are initialized while session is open
                Hibernate.initialize(entity.getCharacters());
                Hibernate.initialize(entity.getScenes());
                Hibernate.initialize(entity.getClues());

                // Scalars
                entity.setTitle(draft.getTitle());
                entity.setDescription(draft.getDescription());
                entity.setSetting(draft.getSetting());

                // ----- Characters -----
                entity.getCharacters().clear();
                if (draft.getCharacters() != null) {
                    for (CharacterDraft dc : draft.getCharacters()) {
                        Character ch = new Character();
                        ch.setName(dc.getName());
                        ch.setRole(dc.getRole());
                        ch.setBio(dc.getBio());
                        ch.setGuilty(Boolean.TRUE.equals(dc.getGuilty()));
                        ch.setPictureUrl(dc.getImageUrl());
                        ch.setStory(entity);
                        entity.getCharacters().add(ch);
                    }
                }

                // ----- Scenes -----
                entity.getScenes().clear();
                if (draft.getScenes() != null) {
                    for (ScenesDraft ds : draft.getScenes()) {
                        Scene sc = new Scene();
                        sc.setTitle(ds.getTitle());
                        sc.setDescription(ds.getDescription());
                        sc.setDisplayOrder(ds.getDisplayOrder());
                        sc.setStory(entity);
                        entity.getScenes().add(sc);
                    }
                }

                // ----- Clues -----
                entity.getClues().clear();
                if (draft.getClues() != null) {
                    for (ClueDraft dcl : draft.getClues()) {
                        Clue cl = new Clue();
                        cl.setTitle(dcl.getTitle());
                        cl.setDescription(dcl.getDescription());
                        cl.setImageUrl(dcl.getImageUrl());
                        cl.setStory(entity);
                        entity.getClues().add(cl);
                    }
                }

                // Persist changes
                session.merge(entity); // merge handles detached entities safely
                tx.commit();
            } catch (Exception ex) {
                if (tx != null) tx.rollback();
                throw ex;
            }
        }
    }

    /** TEMP helper until real auth: creates a throwaway user so DraftSaver has a creator. */
    private User ensureTempCreator() {
        GenericDao<User> userDao = new GenericDao<>(User.class);
        String uname = "temp_host_" + System.currentTimeMillis();
        User u = new User(uname, "placeholder", uname + "@example.com", true);
        int id = userDao.insert(u);
        return userDao.getById(id);
    }
}
