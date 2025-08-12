package web;

import javax.servlet.annotation.WebServlet;

@WebServlet("/character")
public class CharacterServlet extends BaseViewServlet {
    @Override
    protected String view() {
        return "/jsp/character.jsp";
    }
}
