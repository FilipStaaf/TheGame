import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

/*Extremt enkelt Gui för att kunna komma igång.
Snygga gärna till/gör ett eget. Men tänk på att gör GUI:s INTE är ett kursmoment - så fastna inte här!
 */


    public class Gui extends JFrame {

        private JPanel panel;
        private JTextArea showRoom;
        private JTextArea showPersons;
        private JTextField input;
        private JTextArea inventory;
        private String command;
        private boolean gotCommand;
        private JButton button;

        public Gui(){
            this.gotCommand = false;
            this.command = "";
            this.setTitle("Game");
            this.setSize(800, 600);
            this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            setUpElements();
            setUpPanel();
            this.add(panel);
            this.setVisible(true);
            this.setResizable(false);
        }
        //Returnera det senaste commitade kommandot
        public String getCommand(){
            if (this.gotCommand){
                //System.out.println(this.command);
                return this.command;
            }
            return "-1";
        }
        //Här kan man updatera respektive fält:
        public void setShowRoom(String roomDescription){
            this.showRoom.setText(roomDescription);
         }
        public void setShowPersons(Person[] persons){
            this.showPersons.setText(Arrays.stream(persons).filter(Objects::nonNull).map(Npc::toString).collect(Collectors.joining("\n")));
        }
        public void setShowInventory(Inventory i){
            this.inventory.setText(i.toString());
        }
//Nedantåenda spaghetti är inte vacker...
        private void setUpPanel(){
            this.panel.add(showPersons);
            this.panel.add(showRoom);
            this.panel.add(input);
            this.panel.add(inventory);
            this.panel.add(button);
        }
        private void setUpElements(){
            this.panel = new JPanel(new GridLayout(3,3,2,2));
            this.showRoom = new JTextArea("Room: ");
            this.showPersons = new JTextArea("Persons: ");
            this.inventory = new JTextArea("Inventory: ");
            this.input = new JTextField("Give command: ");
            this.showPersons.setEditable(false);
            this.showRoom.setEditable(false);
            this.inventory.setEditable(false);

            ActionListener inputListener = e -> {
                this.command = input.getText();
                System.out.println("click");
                this.gotCommand = true;
            };

            input.addActionListener(inputListener);

            this.button = new JButton("commit");
            this.button.addActionListener(inputListener);
        }
    }









