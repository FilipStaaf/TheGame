

/*
Npc Ska vara antingen ett Interface eller en Abstrakt klass och ge
ramarna för våra Npc:er.
 */



public abstract class Npc {
    String name;
    Inventory inventory;
    Gui gui;
    public Npc(String name,Gui g){
        this.gui = g;
        this.name = name;
        this.inventory = new Inventory(1,gui);
    }

    public Inventory getInventory(){
        return this.inventory;
    }
    public String toString (){
        return this.name + " is carrying " +this.inventory;
    }
}
