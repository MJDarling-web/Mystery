/*
    StoryLookupServlet will be the POST
    uses existing genericDao<Story> to check the story exists,
    validate storyID,
    store only the ID in the session,
    forwards to /players
*/

package web;

import entity.Story;
import persistence.GenericDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/storyLookup")
public class StoryLookupServlet extends HttpServlet {

    private GenericDao<Story> storyDao;

    @Override
    public void init() throws ServletException {
        storyDao = new GenericDao<>(Story.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/storyLookup.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        int id;
        try {
            id = Integer.parseInt(req.getParameter("storyId"));
            if (id <= 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            req.setAttribute("error", "Please enter a valid story id");
            req.getRequestDispatcher("/jsp/storyLookup.jsp").forward(req, resp);
            return;
        }

        // storing the id
        req.getSession().setAttribute("currentStoryId", id);

        //forward to player dashboard servlet
        resp.sendRedirect(req.getContextPath()  + "/players");
    }
}