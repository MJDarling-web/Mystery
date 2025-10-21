package web;

import javax.servlet.annotation.WebServlet;
public class HostDeleteStoryServlet {
    @WebServlet("/Host/hostDeleteStoryServlet ")
    public static class hostDeleteStoryServlet extends BaseViewServlet {
        @Override
        protected String view() {
            return "/jsp/Host/hostDeleteStory.jsp";
        }
    }

}
