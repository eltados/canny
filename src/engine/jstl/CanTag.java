package engine.jstl;

import engine.Action;
import engine.Authorization;
import engine.User;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 * Created by IntelliJ IDEA.
 * User: mathieu
 * Date: 16-Apr-2010
 * Time: 12:09:07
 * To change this template use File | Settings | File Templates.
 */
public class CanTag extends BodyTagSupport {
   public int doAfterBody() throws JspException {
      if (Authorization.can(user, action, object))
         return EVAL_BODY_INCLUDE;
      else
         return SKIP_BODY;
   }

   public void setUser(User user) {
      this.user = user;
   }

   public void setAction(Action action) {
      this.action = action;
   }

   public void setObject(Object object) {
      this.object = object;
   }

   private User user;
   private Action action;
   private Object object;
}
