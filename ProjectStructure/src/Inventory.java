import java.lang.module.FindException;
import java.util.Arrays;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Inventory {

    private GameObject[] list;
    private int size;
    private Gui gui;

    public Inventory(int size, Gui g) {
        this.gui = g;
        this.size = size;
        list = new GameObject[size];
    }

    public GameObject returnGameObject(String objectName) {
        for (GameObject g : list) {
            if (g.getName().equals(objectName)) {
                return g;
            }
        }
        return null;
    }

    public boolean addObject(GameObject go) {
        int index = getFirstEmptyIndex();
        if (index == -1) {
            System.out.println("Inventory är fullt");
            return false;
        }
        this.list[index] = go;
        return true;
    }

    public String toString() {
        return Arrays.toString(this.list);
    }

    public void removeObject(GameObject gameObject) {
        GameObject[] temp = Stream.of(this.list).filter(object -> {
            if (gameObject.equals(object)) {
                return false;
            }
            return true;
        }).toArray(GameObject[]::new);
        list = temp;
    }

    public boolean contains(String objectName) {
        for (GameObject g : this.list) {
            if (g != null) {

                if (g.getName().equals(objectName)) {
                    return true;
                }
            }

        }
        return false;
    }

    private int getFirstEmptyIndex() {

        for (int i = 0; i < this.list.length; i++) {
            if (this.list[i] == null) {
                return i;
            }
        }
        return -1;
    }
}




