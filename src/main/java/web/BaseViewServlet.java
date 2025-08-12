// BaseViewServlet.java
package web;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public abstract class BaseViewServlet extends HttpServlet {
    protected abstract String view();
    @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        req.getRequestDispatcher(view()).forward(req, resp);
    }
}
