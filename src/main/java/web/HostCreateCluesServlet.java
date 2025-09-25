package web;

import javax.servlet.annotation.WebServlet;
@WebServlet("/Host/hostCreateClues")
public class HostCreateCluesServlet extends BaseViewServlet {
    @Override
    protected String view() {
        return "/jsp/Host/hostCreateClues.jsp";
    }
}
