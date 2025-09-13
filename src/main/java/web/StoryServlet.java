/**
 * 
 * PlayersServlet will be the GET
 * reads currentStoryId from session
 * opens a hibernate session using session.get(Story.class, id)
 * puts the story on the request and forwards to PlayerDashboard.jsp
 *
 */

package web;

import javax.servlet.annotation.WebServlet;

@WebServlet("/story")
public class StoryServlet extends BaseViewServlet {
    @Override
    protected String view() {return "/jsp/story.jsp"; }
}
