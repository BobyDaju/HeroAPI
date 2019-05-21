package Model;

public class Hero {
    private String ID;
    private String name;
    private String desc;


    public Hero (String name, String desc) {
        this.name = name;
        this.desc = desc;
    }


    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
