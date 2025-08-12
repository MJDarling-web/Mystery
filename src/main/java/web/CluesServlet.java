package web;

import javax.servlet.annotation.WebServlet;
@WebServlet("/clues")
public class CluesServlet extends BaseViewServlet {
    @Override
    protected String view() {
        return "/jsp/clues.jsp";
    }
}
