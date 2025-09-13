package web;

import javax.servlet.annotation.WebServlet;

@WebServlet("/storyLookup")
public class StoryLookupServlet extends BaseViewServlet {
    @Override
    protected String view() {return "/jsp/storyLookup.jsp"; }
}