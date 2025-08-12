package web;

import javax.servlet.annotation.WebServlet;

@WebServlet("/story")
public class StoryServlet extends BaseViewServlet {
    @Override
    protected String view() {return "/jsp/story.jsp"; }
}
