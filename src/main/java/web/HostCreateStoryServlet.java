package web;

import javax.servlet.annotation.WebServlet;

@WebServlet("/Host/hostCreateNewStory ")
public class HostCreateStoryServlet extends BaseViewServlet {
    @Override
    protected String view() {
        return "/jsp/Host/hostCreateNewStory.jsp";
    }
}
