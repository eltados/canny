package test;

import engine.User;
import engine.Localizable;

import java.util.Locale;

public class UserVO implements User, Localizable {

   private String name;
   private String role;
   private Locale locale = null;

   public UserVO(String name, String role) {
      this.name = name;
      this.role = role;
   }
   public UserVO(String name, String role, Locale locale) {
      this.name = name;
      this.role = role;
      this.locale = locale;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getRole() {
      return role;
   }

   public void setRole(String role) {
      this.role = role;
   }

   public String toString() {
      return name + "[" + role + "]";
   }

   public Locale getLocale() {
      return locale;
   }

   public void setLocale(Locale locale) {
      this.locale = locale;
   }
}
