package engine;

public class StringAction implements Action{

    private String name;

    public StringAction(String name) {
        this.name = name;
    }
    
    public String toString(){
        return name;
    }


}