package web;

import javax.servlet.annotation.WebServlet;
@WebServlet("/adminDashboard")
public class AdminDashboardServlet extends BaseViewServlet {
    @Override
    protected String view() {
        return "/jsp/adminDashboard.jsp";
    }
}