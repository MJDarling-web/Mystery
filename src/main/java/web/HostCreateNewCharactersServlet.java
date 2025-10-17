package web;

import dto.CharacterDraft;
import dto.StoryDraft;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.ServletException;
import java.io.IOException;

@WebServlet("/Host/hostCreateCharacters")
public class HostCreateNewCharactersServlet extends HttpServlet {
    @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        StoryDraft draft = (StoryDraft) req.getSession().getAttribute("draft");
        if (draft == null) { resp.sendRedirect(req.getContextPath()+"/Host/hostCreateNewStory"); return; }
        req.setAttribute("draft", draft);
        req.getRequestDispatcher("/jsp/Host/hostCreateCharacters.jsp").forward(req, resp);
    }

    @Override protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        StoryDraft draft = (StoryDraft) req.getSession().getAttribute("draft");
        if (draft == null) { resp.sendRedirect(req.getContextPath()+"/Host/hostCreateNewStory"); return; }

        String action = req.getParameter("action");
        if ("add".equals(action)) {
            String name = t(req.getParameter("name"));
            String role = t(req.getParameter("role"));
            String bio  = t(req.getParameter("bio"));
            boolean guilty   = "true".equalsIgnoreCase(req.getParameter("guilty"));
            boolean murdered = "true".equalsIgnoreCase(req.getParameter("murdered"));
            String imageUrl  = t(req.getParameter("imageUrl"));
            if (name != null && !name.isEmpty()) {
                draft.addCharacter(new CharacterDraft(name, role, bio, guilty, murdered, imageUrl));
            }
            resp.sendRedirect(req.getContextPath()+"/Host/hostCreateCharacters");
        } else if ("remove".equals(action)) {
            try { draft.removeCharacter(Integer.parseInt(req.getParameter("index"))); } catch (Exception ignored) {}
            resp.sendRedirect(req.getContextPath()+"/Host/hostCreateCharacters");
        } else if ("next".equals(action)) {
            resp.sendRedirect(req.getContextPath()+"/Host/hostEditFinishedStory");
        } else {
            resp.sendRedirect(req.getContextPath()+"/Host/hostCreateCharacters");
        }
    }

    private String t(String v){ return v==null? null : v.trim(); }
}
