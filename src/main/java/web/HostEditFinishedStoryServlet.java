package web;

import dto.StoryDraft;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.ServletException;
import java.io.IOException;

@WebServlet("/Host/hostEditFinishedStory")
public class HostEditFinishedStoryServlet extends HttpServlet {
    @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        StoryDraft draft = (StoryDraft) req.getSession().getAttribute("draft");
        if (draft == null) { resp.sendRedirect(req.getContextPath()+"/Host/hostCreateNewStory"); return; }
        req.setAttribute("draft", draft);
        req.getRequestDispatcher("/jsp/Host/hostEditFinishedStory.jsp").forward(req, resp);
    }

    @Override protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // TODO: persist Story + related entities here
        // After save, you can clear draft or redirect to dashboard
        req.getSession().removeAttribute("draft");
        resp.sendRedirect(req.getContextPath()+"/Host/adminDashboard");
    }
}
