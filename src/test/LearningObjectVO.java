package test;

import engine.Ownable;
import engine.User;

public class LearningObjectVO implements Ownable{
   User user ;
   boolean locked= false;

   public LearningObjectVO(User user) {
      this.user = user;
   }

   public User getOwner() {
      return user;
   }

   public boolean isLocked() {
      return locked;
   }

   public void setLocked(boolean locked) {
      this.locked = locked;
   }
}