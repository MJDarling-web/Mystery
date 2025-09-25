package web;

import javax.servlet.annotation.WebServlet;
public class HostDeleteStorySerlvet {
    @WebServlet("/Host/hostDeleteStoryServlet ")
    public class HostDeleteStoryServlet extends BaseViewServlet {
        @Override
        protected String view() {
            return "/jsp/Host/hostDeleteStory.jsp";
        }
    }

}
