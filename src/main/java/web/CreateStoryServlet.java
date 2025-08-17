package web;

import javax.servlet.annotation.WebServlet;

@WebServlet("/createStory")
public class CreateStoryServlet extends BaseViewServlet {
    @Override
    protected String view() {
        return "/jsp/createStory.jsp";
    }
}
