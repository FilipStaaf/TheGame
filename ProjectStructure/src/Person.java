
import java.util.*;


public class Person extends Npc implements Runnable{

    private int position;
    public Person(String name, int startRoom, Gui g) {
        super(name,g);
        this.position = startRoom;
    }
    public synchronized void move(){
        int rand = new Random().nextInt(4);
        this.position = rand;
        System.out.println("noving " + this.name + " to room " + (this.position+1) );
    }
    public int getPosition() {
        return position;
    }

    @Override
   public void run(){
        move();
   }
}

/*


 */

