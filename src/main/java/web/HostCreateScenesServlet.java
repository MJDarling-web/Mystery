package web;

import dto.StoryDraft;
import dto.ScenesDraft;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/Host/hostCreateScenes")
public class HostCreateScenesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        StoryDraft draft = (StoryDraft) req.getSession().getAttribute("storyDraft");
        if (draft == null) {
            resp.sendRedirect(req.getContextPath() + "/Host/hostCreateNewStory");
            return;
        }
        req.setAttribute("storyDraft", draft);
        req.getRequestDispatcher("/jsp/Host/hostCreateScenes.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession(false);
        if (session == null) {
            resp.sendRedirect(req.getContextPath() + "/Host/hostCreateNewStory");
            return;
        }

        StoryDraft draft = (StoryDraft) session.getAttribute("storyDraft");
        if (draft == null) {
            resp.sendRedirect(req.getContextPath() + "/Host/hostCreateNewStory");
            return;
        }

        String action = req.getParameter("action");
        if ("add".equals(action)) {
            String title = req.getParameter("sceneTitle");
            String description = req.getParameter("sceneDescription");
            String displayOrderStr = req.getParameter("displayOrder");

            if (title == null || title.isBlank()) {
                req.setAttribute("error", "Scene title is required.");
                req.setAttribute("storyDraft", draft);
                req.getRequestDispatcher("/jsp/Host/hostCreateScenes.jsp").forward(req, resp);
                return;
            }

            int displayOrder = draft.getScenes().size() + 1;
            try {
                if (displayOrderStr != null && !displayOrderStr.isBlank()) {
                    displayOrder = Integer.parseInt(displayOrderStr);
                }
            } catch (NumberFormatException ignored) {}

            draft.getScenes().add(new ScenesDraft(
                    title.trim(),
                    description == null ? "" : description.trim(),
                    displayOrder
            ));

            resp.sendRedirect(req.getContextPath() + "/Host/hostCreateScenes");
            return;

        } else if ("delete".equals(action)) {
            String indexStr = req.getParameter("index");
            try {
                int idx = Integer.parseInt(indexStr);
                List<ScenesDraft> scenes = draft.getScenes();
                if (idx >= 0 && idx < scenes.size()) {
                    scenes.remove(idx);
                }
            } catch (Exception ignored) {}
            resp.sendRedirect(req.getContextPath() + "/Host/hostCreateScenes");
            return;

        } else if ("next".equals(action)) {
            resp.sendRedirect(req.getContextPath() + "/Host/hostCreateCharacters");
            return;
        }

        // redirect back to main page if needed
        resp.sendRedirect(req.getContextPath() + "/Host/hostCreateScenes");
    }
}
