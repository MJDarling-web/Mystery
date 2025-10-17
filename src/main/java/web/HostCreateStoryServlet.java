package web;

import dto.StoryDraft;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.ServletException;
import java.io.IOException;

@WebServlet("/Host/hostCreateNewStory")
public class HostCreateStoryServlet extends HttpServlet {
    @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession s = req.getSession();
        StoryDraft draft = (StoryDraft) s.getAttribute("draft");
        if (draft == null) { draft = new StoryDraft(); s.setAttribute("draft", draft); }
        req.setAttribute("draft", draft);
        req.getRequestDispatcher("/jsp/Host/hostCreateNewStory.jsp").forward(req, resp);
    }

    @Override protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession s = req.getSession();
        StoryDraft draft = (StoryDraft) s.getAttribute("draft");
        if (draft == null) { draft = new StoryDraft(); s.setAttribute("draft", draft); }
        draft.setTitle(t(req.getParameter("title")));
        draft.setDescription(t(req.getParameter("description")));
        draft.setSetting(t(req.getParameter("setting")));
        resp.sendRedirect(req.getContextPath()+"/Host/hostCreateScenes");
    }

    private String t(String v){ return v==null? null : v.trim(); }
}
