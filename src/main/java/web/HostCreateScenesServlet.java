package web;

import javax.servlet.annotation.WebServlet;
@WebServlet("/Host/hostCreateScenes")
public class HostCreateScenesServlet extends BaseViewServlet {
    @Override
    protected String view() {
        return "/jsp/Host/hostCreateScenes.jsp";
    }
}
