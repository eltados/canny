package com.izera2.canny.jstl;

import com.izera2.canny.Authorization;

public class CannotTag extends CanTag {
   protected boolean shouldContentBeRended() {
      return !Authorization.can(user, action, object);
   }
}