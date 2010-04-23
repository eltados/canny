package com.izera2.canny.jstl;

import com.izera2.canny.interfaces.Action;
import com.izera2.canny.Authorization;
import com.izera2.canny.interfaces.User;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.List;

public class WhyTag extends TagSupport {
   private User user;
   private Action action;
   private Object object=null;

   public int doStartTag() throws JspException{
      if (!Authorization.can(user, action, object))
         renderContentOfTag();

      return SKIP_BODY;
   }

   private void renderContentOfTag() throws JspException {
      JspWriter out = pageContext.getOut();
      List<String> reasons = Authorization.why(user, action, object);
      try {
         out.println("<ul class='reasons'>");
         for (String reason : reasons) {
            out.println("<li>"+reason+"</li>");
         }
         out.println("</ul>");
      } catch (IOException e) {
        throw new JspException(e);
      }
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


}
