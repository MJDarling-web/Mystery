package web;

import dto.ClueDraft;
import dto.StoryDraft;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.ServletException;
import java.io.IOException;

@WebServlet("/Host/hostCreateClues")
public class HostCreateCluesServlet extends HttpServlet {
    @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        StoryDraft draft = (StoryDraft) req.getSession().getAttribute("draft");
        if (draft == null) { resp.sendRedirect(req.getContextPath()+"/Host/hostCreateNewStory"); return; }
        req.setAttribute("draft", draft);
        req.getRequestDispatcher("/jsp/Host/hostCreateClues.jsp").forward(req, resp);
    }

    @Override protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        StoryDraft draft = (StoryDraft) req.getSession().getAttribute("draft");
        if (draft == null) { resp.sendRedirect(req.getContextPath()+"/Host/hostCreateNewStory"); return; }

        String action = req.getParameter("action");
        if ("add".equals(action)) {
            String title = t(req.getParameter("title"));
            String desc  = t(req.getParameter("description"));
            String url   = t(req.getParameter("imageUrl"));
            if (title != null && !title.isEmpty()) draft.addClue(new ClueDraft(title, desc, url));
            resp.sendRedirect(req.getContextPath()+"/Host/hostCreateClues");
        } else if ("remove".equals(action)) {
            try { draft.removeClue(Integer.parseInt(req.getParameter("index"))); } catch (Exception ignored) {}
            resp.sendRedirect(req.getContextPath()+"/Host/hostCreateClues");
        } else if ("next".equals(action)) {
            resp.sendRedirect(req.getContextPath()+"/Host/hostCreateCharacters");
        } else {
            resp.sendRedirect(req.getContextPath()+"/Host/hostCreateClues");
        }
    }

    private String t(String v){ return v==null? null : v.trim(); }
}
