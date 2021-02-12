
/* • Update Ska implementera Runnable och starta en tråd som Regelbundet updaterar Guit utifrån vad som händer i spelet.

 */


import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Update implements Runnable {
    private Gui gui;

    public Update(Gui gui) {
        this.gui = gui;
    }

    @Override
    public void run() {
        /*
        gui.setShowRoom();
        gui.setShowInventory();
        gui.setShowPersons();
        */

        //TODO
    }
}
