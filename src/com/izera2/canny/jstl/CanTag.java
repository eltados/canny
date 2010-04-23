package com.izera2.canny.jstl;

import com.izera2.canny.interfaces.Action;
import com.izera2.canny.interfaces.User;
import com.izera2.canny.Authorization;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;

public class CanTag extends BodyTagSupport {
   protected User user;
   protected Action action;
   protected Object object;
   
   public int doAfterBody() throws JspException {
        if (shouldContentBeRended())
           renderContentOfTag();

        return SKIP_BODY;
     }

     protected boolean shouldContentBeRended() {
        return Authorization.can(user, action, object);
     }

   private void renderContentOfTag() throws JspException {
      BodyContent body = getBodyContent();
      JspWriter writer = body.getEnclosingWriter();
      try {
         String bodyString = body.getString();
         if (bodyString != null)
            writer.print(bodyString);
      }
      catch (IOException ioe) {
         throw new JspException("Error: IO Exception with JspWriter");
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
