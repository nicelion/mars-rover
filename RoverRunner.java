
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
        // Rover r1 = new Rover("Curiosity", 0, 0, 0);
        /*Rover r2 = new Rover("Spirit", 0, 0, 0);
        Rover r3 = new Rover("Sojourner", 0, 0, 0);
        Rover r4 = new Rover("Oppertunity", 0, 0, 0);
        
        System.out.println(r4);
        
        r4.setName("Hello, World!");
        printRover(r4);
        
        r4.move(3);
        printRover(r4);
        
        r4.kill(r1);
        r1.kill(r3);
        
        r2.kill(r2);
        
        r1.move(3);
        System.out.println(r1);
        r1.rotateLeft();
        System.out.println(r1);
        
        r1.teleport(40,12);
        System.out.println(r1);
        
        r1.goHome();
        System.out.println(r1);
        
        System.out.println();
        
        r1.takePicture();
        r1.takePicture();
        r1.takePicture();
        r1.takePicture();
        r1.takePicture();
        r1.takePicture();
        r1.takePicture();
        r1.takePicture();
        r1.takePicture();
        r1.takePicture();
        r1.takePicture();
        r1.takePicture("Black and White");
        
        
        r1.transmitPictures();
        
        r1.takePicture();*/
        
        /*r1.move(3);
        r1.rotateRight();
        r1.move(11);
        
        r1.teleport(10, -1);*/
        
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
            
            try {
                BufferedReader br = new BufferedReader(new FileReader("menu.txt"));
                String line = null;
                 while ((line = br.readLine()) != null) {
                   System.out.println(line);
                }
            } catch (IOException e){
                System.out.println("Could not load menu");
            }
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
                    System.out.println("For how many second would you like to charge?");
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
