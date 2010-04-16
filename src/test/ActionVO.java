package test;

import engine.Action;

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
