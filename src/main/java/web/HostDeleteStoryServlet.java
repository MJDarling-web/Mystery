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


@WebServlet("/Host/deleteStory")
public class HostDeleteStoryServlet extends HttpServlet {

    private GenericDao<Story> storyDao;

    @Override
    public void init() throws ServletException {
        storyDao = new GenericDao<>(Story.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int storyId = Integer.parseInt(req.getParameter("storyId"));
        storyDao.deleteEntity(storyDao.getById(storyId));
        resp.sendRedirect(req.getContextPath() + "/Host/adminDashboard");
    }

}
