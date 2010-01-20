package test;

import engine.Action;

/**
 * Created by IntelliJ IDEA.
 * User: Administrateur
 * Date: 17 déc. 2009
 * Time: 19:36:44
 * To change this template use File | Settings | File Templates.
 */
public class ActionVO implements Action{

    private String name;

    public ActionVO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public String toString(){
        return name;
    }


}
