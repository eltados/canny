package test;

import engine.Action;

/**
 * Created by IntelliJ IDEA.
 * User: Administrateur
 * Date: 17 déc. 2009
 * Time: 19:45:04
 * To change this template use File | Settings | File Templates.
 */
public class CustomAction {
    public final static Action EDIT = new ActionVO("edit");
    public final static Action EDIT_LEARNING_OBJECT = new ActionVO("edit learning object");
    public final static Action SUPER_SECURE = new ActionVO("super secure");
    public final static Action READ = new ActionVO("read");
    public final static Action DELETE = new ActionVO("delete");
    public final static Action UPDATE = new ActionVO("update");
    public static final Action IDENTICAL = new ActionVO("identical");
   public static final Action GUEST = new ActionVO("guest");
}
