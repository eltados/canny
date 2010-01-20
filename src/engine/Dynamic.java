package engine;

import java.util.Locale;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;


public class Dynamic {
    public static Locale getLocale(User user){
       if(user instanceof Localizable)
          return ((Localizable)user).getLocale();
       return null;
    }
   
    public static Object invoke(Object object, String methodString) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        return invoke(object, methodString, null, null);
    }

    public static Object invoke(Object object, String methodString, Object[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        return invoke(object, methodString, args, autoDeterminClasses(args));
    }
    public static Object invoke(Object object, String methodString, Object[] args, Class[] classesArray) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Object output;
        if (methodString.endsWith("()"))
            methodString = methodString.substring(0, methodString.length() - 2);
        Method method = object.getClass().getDeclaredMethod(methodString, classesArray);
//        method.setAccessible(true);
        output = method.invoke(object, args);
//        method.setAccessible(false);
        return output;
    }
   //------------------------------------------
    private static Class[] autoDeterminClasses(Object[] args) throws NoSuchMethodException {
        Class[] classesArray;
        if (args == null) {
            classesArray = null;
        } else {
            classesArray = new Class[args.length];
            int i = 0;
            for (Object arg : args) {
                if (arg == null)
                    throw new NoSuchMethodException("Cannot autoDeterminClass for arg[" + i + "]=\"null\", please use specifing Class[]");
                classesArray[i] = arg.getClass();
            }
        }
        return classesArray;
    }


}
