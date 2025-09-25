package web;

import javax.servlet.annotation.WebServlet;

@WebServlet("/Host/hostCreateCharacters")
public class HostCreateNewCharactersServlet extends BaseViewServlet {
    @Override
    protected String view() {
        return "/jsp/Host/hostCreateCharacters.jsp";
    }
}
