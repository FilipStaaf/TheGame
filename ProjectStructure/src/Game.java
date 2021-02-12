
import java.util.Arrays;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Game {

    private Player spelaren;
    private Gui gui = new Gui();
    private Room room1, room2, room3, room4;
    private Room[] map;
    private Person[] persons;

    public Game() {


        //Skapa rum!
        room1 = new Room("Vardagsrum", "Stort och mörkt med en soffa", gui);
        room2 = new Room("Badrum", "Litet, trång med en taklampa", gui);
        room3 = new Room("Sovrum", "liten, kvavt med en säng", gui);
        room4 = new Room("Allrum", "Stort, ljust och luktar äckligt", gui);


        map = new Room[4];
        map[0] = room1;
        map[1] = room2;
        map[2] = room3;
        map[3] = room4;

        //Skapar Spelaren
        spelaren = new Player("spelaren", 0, gui);

        //Skapar Gameobjects
        GameObject soffa = new GameObject("soffa", false);
        GameObject taklampa = new GameObject("taklampa", false);
        GameObject säng = new GameObject("säng", false);
        GameObject banan = new GameObject("banan", true);
        GameObject ficklampa = new GameObject("ficklampa", true);
        GameObject morot = new GameObject("morot", true);
        room1.addObject(soffa);
        room2.addObject(taklampa);
        room3.addObject(säng);
        room4.addObject(banan);

        //Skapar Låsta GameObjects
        Container box = new Container("En blå låda", false, true, gui);
        Container dörr = new Container("Dörren ut", false, true, gui);
        room4.addObject(box);
        room4.addObject(dörr);

        //Skapar NPC
        Person jason = new Person("Jason", 4, gui);
        room1.addNpc(jason);
        jason.getInventory().addObject(morot);

        Person freddy = new Person("Freddy", 1, gui);
        room2.addNpc(freddy);
        freddy.getInventory().addObject(banan);

        Person ture = new Person("Ture", 2, gui);
        room3.addNpc(ture);
        ture.getInventory().addObject(ficklampa);

        persons = new Person[3];
        persons[0] = jason;
        persons[1] = freddy;
        persons[2] = ture;

        // spelarens inventory
        spelaren.getInventory().addObject(morot);
        System.out.println(spelaren.getInventory());

        Inventory inventory = new Inventory(7, gui);
        System.out.println(inventory);

        inventory.addObject(ficklampa);

        System.out.println(inventory);

        int currentRoomView = 0;

        ScheduledThreadPoolExecutor pool = new ScheduledThreadPoolExecutor(10);
        pool.scheduleAtFixedRate(jason, 20, 30, TimeUnit.SECONDS);
        pool.scheduleAtFixedRate(freddy, 30, 30, TimeUnit.SECONDS);
        pool.scheduleAtFixedRate(ture, 10, 30, TimeUnit.SECONDS);

        while (true) {
            String command = gui.getCommand();
            if (!command.equals("-1")) {

                if (command.equals("1")) {
                    currentRoomView = 0;
                }
                if (command.equals("2")) {
                    currentRoomView = 1;
                }
                if (command.equals("3")) {
                    currentRoomView = 2;
                }
                if (command.equals("4")) {
                    currentRoomView = 3;
                }

                gui.setShowRoom(map[currentRoomView].toString());
                gui.setShowInventory(spelaren.getInventory());
                Person[] whoInRoom = getPersonsInRoom(currentRoomView);
                map[currentRoomView].setPerson(whoInRoom);
                if (map[currentRoomView].getPersons() != null) {
                    gui.setShowPersons(map[currentRoomView].getPersons());
                }

                //tar argumententet ifrån commands, ifall argumentet stämmer skapar vi en sträng
                // med värdet av texten som kommer efter det sanna argumentet ("ta")
                    if (command.startsWith("ta")) {
                        String objectName = command.substring(3);
                        System.out.println(objectName);

                        if (map[currentRoomView].getInventory().contains(objectName)) {
                            GameObject gameObject = map[currentRoomView].getInventory().returnGameObject(objectName);
                            if (gameObject.isMovable()) {
                                boolean succeses = spelaren.getInventory().addObject(gameObject);
                                if (succeses) {
                                    map[currentRoomView].getInventory().removeObject(gameObject);
                                }
                            }
                        }
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    public Person[] getPersonsInRoom ( int index){
        Person[] inRoom = Arrays.stream(persons).filter(person -> {
            if (person.getPosition() == index) {
                return true;
            }
            return false;
        }).toArray(Person[]::new);
        return inRoom;
    }

}