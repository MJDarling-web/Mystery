package web;

import dto.ScenesDraft;
import dto.StoryDraft;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.ServletException;
import java.io.IOException;

@WebServlet("/Host/hostCreateScenes")
public class HostCreateScenesServlet extends HttpServlet {
    @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        StoryDraft draft = (StoryDraft) req.getSession().getAttribute("draft");
        if (draft == null) { resp.sendRedirect(req.getContextPath()+"/Host/hostCreateNewStory"); return; }
        req.setAttribute("draft", draft);
        req.getRequestDispatcher("/jsp/Host/hostCreateScenes.jsp").forward(req, resp);
    }

    @Override protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        StoryDraft draft = (StoryDraft) req.getSession().getAttribute("draft");
        if (draft == null) { resp.sendRedirect(req.getContextPath()+"/Host/hostCreateNewStory"); return; }

        String action = req.getParameter("action");
        if ("add".equals(action)) {
            String title = t(req.getParameter("sceneTitle"));
            String desc  = t(req.getParameter("sceneDescription"));
            Integer order = p(req.getParameter("displayOrder"));
            if (title != null && !title.isEmpty()) {
                draft.addScene(new ScenesDraft(title, desc, order == null ? draft.getScenes().size()+1 : order));
            }
            resp.sendRedirect(req.getContextPath()+"/Host/hostCreateScenes");
        } else if ("delete".equals(action)) {
            Integer idx = p(req.getParameter("index"));
            if (idx != null) draft.removeScene(idx);
            resp.sendRedirect(req.getContextPath()+"/Host/hostCreateScenes");
        } else if ("next".equals(action)) {
            resp.sendRedirect(req.getContextPath()+"/Host/hostCreateClues");
        } else {
            resp.sendRedirect(req.getContextPath()+"/Host/hostCreateScenes");
        }
    }

    private String t(String v){ return v==null? null : v.trim(); }
    private Integer p(String v){ try { return Integer.valueOf(v); } catch (Exception e){ return null; } }
}
