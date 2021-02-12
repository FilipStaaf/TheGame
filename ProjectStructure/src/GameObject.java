
/*
• GameObjects Ska också vara abstract eller interface hantera alla
"icke-levandeöbjekt i spelet (möbler, nycklar etc). GameObject ska innehålla en boolean som avgör om objektet går att ta med sig eller är
fastmonterat"i rummet.

 */


public class GameObject {
    private String name;
    boolean moveable;

    public GameObject(String itemName ,boolean moveable){
        this.name = itemName;
        this.moveable = moveable;
    }

    public boolean isMovable(){
        return this.moveable;
    }
    public String getName(){
        return this.name;
    }
    public String toString(){
        return this.name;
    }
}
