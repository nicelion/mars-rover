
/**
 * Write a description of class RoverRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class RoverRunner
{
    public static void printRover(Rover r) {
        System.out.println(r);
        
    }
    
    public static void main(String[] arrrggggggs)
    {
        try {
                // Prints the menu.txt file to the console
                BufferedReader br = new BufferedReader(new FileReader("menu.txt"));
                String line = null;
                 while ((line = br.readLine()) != null) {
                   System.out.println(line);

                }
            } catch (IOException e){
                System.out.println("Could not load menu");
            }
        System.out.println();
         // Make a SimpleScanner
        SimpleScanner input = new SimpleScanner();
        
        // Make some Rovers
        Rover r1 = new Rover("Curiosity", 0, 0, 0);
        Rover r2 = new Rover("Spirit", 0, 0, 0);
        Rover r3 = new Rover("Harambe", 0, 0, 0);
        
        // Make a RoverGroup and add Rovers
        RoverGroup group = new RoverGroup();
        group.add(r1);
        group.add(r2);
        group.add(r3);
        
        // Now let's do stuff
        boolean running = true;
        String exitCommand = "quit";
        
        while (running) {
            // Input name
            System.out.print("Enter the name of the Rover to act: ");
            String name = input.readString();
            
            // Select Rover with matching name
            Rover actor = group.find(name);
            
            if (actor != null) {
                // If the rover is found
                System.out.print("Enter a command: ");
                String command = input.readString();
                
                if (command.equals("0")) {
                    System.out.print("Enter distance to move: ");
                    int distance = input.readInt();
                    actor.move(distance);
                }
                else if (command.equals("1")) {
                    actor.rotateRight();
                }
                else if (command.equals("2")) {
                    actor.rotateLeft();
                }
                else if (command.equals("3")) {
                    System.out.print("Enter the name the target rover: ");
                    String targetName = input.readString();
                    
                    Rover target = group.find(targetName);
                    
                    if (target != null) {
                        actor.kill(target);
                        System.out.println(target);
                    }
                    else {
                        System.out.println("Error: No such target.");
                    }
                } else if (command.equals("4")){
                    System.out.println("For how many seconds would you like to charge?");
                    int batt = input.readInt();
                    actor.chargeBattery(batt);
                } else if (command.equals("5")) {
                    System.out.println("Please type x position: ");
                    int x = input.readInt();
                    System.out.println("Please type y position: ");
                    int y = input.readInt();
                    
                    actor.teleport(x,y);
                } else if (command.equals("6")) {
                    actor.goHome();
                } else if (command.equals("7")) {
                    actor.takePicture();
                } else if (command.equals("8")) {
                    System.out.println("What filter do you want to add?");
                    String filter = input.readString();
                    actor.takePicture(filter);
                } else if (command.equals("9")){
                    actor.transmitPictures();
                } else if (command.equals("10")) {
                    System.out.print("Enter the name the target rover: ");
                    String targetName = input.readString();

                    Rover target = group.find(targetName);
                    
                    System.out.println("Enter how much damage you woul like to instill: ");
                    int attackPower = input.readInt();
                    
                    if (target != null) {
                        actor.attack(target, attackPower);
                        System.out.println(target);
                    }
                    else {
                        System.out.println("Error: No such target.");
                    }
                }
                else {
                    System.out.println("Error: Invalid command.");
                }
                
                System.out.println(actor);
            }
            else if (name.equals(exitCommand)) {
                running = false; // set flag to exit loop
            }
            else {
                System.out.println("Error: " + name + " doesn't exist.");
            }
            
            // just a blank line
            System.out.println();
        }
        
        System.out.println("Goodbye.");
    }
 
    
}
