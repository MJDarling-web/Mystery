package web;

import javax.servlet.annotation.WebServlet;
@WebServlet("/characters")
public class CharactersServlet extends BaseViewServlet {
    @Override
    protected String view() {
        return "/jsp/characters.jsp";
    }
}
