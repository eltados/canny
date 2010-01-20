package engine;

import java.util.Locale;

public interface Translator {
   public String translate(String text, Locale locale, Object... params);

   public Translator DEFAULT = new Translator() {
      public String translate(String text, Locale locale, Object... params) {
         return text + "[" + locale + "]";
      }
   };
   public Translator NO = new Translator() {
      public String translate(String text, Locale locale, Object... params) {
            return text;
      }
   };
}
