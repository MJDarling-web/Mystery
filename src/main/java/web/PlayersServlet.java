package web;

import javax.servlet.annotation.WebServlet;

@WebServlet("/players")
public class PlayersServlet extends BaseViewServlet {
    @Override
    protected String view() {return "/jsp/players.jsp"; }
}