package web;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * base view servlet, sets UTF-8 and forwards GET to return JSP by view()
 * instead of duplicating code across servlets.
 */
public abstract class BaseViewServlet extends HttpServlet {

    protected abstract String view();

    protected void beforeGet(HttpServletRequest req, HttpServletResponse resp) throws Exception {}

    protected void onPost(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        doGet(req, resp);
    }

    protected final void redirect(HttpServletRequest req, HttpServletResponse resp, String path) throws IOException {
        resp.sendRedirect(req.getContextPath() + path);
    }

    @Override protected final void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            req.setCharacterEncoding("UTF-8");
            resp.setContentType("text/html;charset=UTF-8");
            beforeGet(req, resp);
            String jsp = view();
            if (jsp == null) { resp.sendError(500, "No view() provided"); return; }
            req.getRequestDispatcher(jsp).forward(req, resp);
        } catch (Exception e) {
            throw (e instanceof ServletException) ? (ServletException)e : new ServletException(e);
        }
    }

    @Override protected final void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            onPost(req, resp);
        } catch (Exception e) {
            throw (e instanceof ServletException) ? (ServletException)e : new ServletException(e);
        }
    }
}
