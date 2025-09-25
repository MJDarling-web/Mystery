package web;

import javax.servlet.annotation.WebServlet;
@WebServlet("Host/hostEditFinishedStory")
public class HostEditFinishedStoryServlet extends BaseViewServlet {
    @Override
    protected String view() {
        return "/jsp/Host/hostEditFinishedStory.jsp";
    }
}
