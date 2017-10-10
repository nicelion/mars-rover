
/**
 * 
 * Mars Rover object
 * 
 * @author Ian Thompson 
 * @version 9/26/17
 */

import java.util.concurrent.TimeUnit;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
public class Rover
{
    // fields
    private String name;
    private int x;
    private int y;
    private int dir; // 0=North, 1=East, 2=South, 3=West
    private boolean isAlive;
    private double battery;
    private int numPics;
    private int maxPics;

    
    // constructor(s)
    /**
     * Initialization of Rover object
     * @param name name of the rover
     * @param x value of the rovers initial "x" position
     * @param y value of the rover's inital "y" position
     * @param dir value of the rover's initial direction.
     */
    public Rover(String name, int x, int y, int dir) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.isAlive = true;
        this.battery = 50;
        this.numPics = 0;
        this.maxPics = 10;

    }

    /**
     *  Change the name of the rover
     *  @param name name of rover
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * Set the direction of the rover (0-3)
     * @param dir value of direction (0 - north, 1 - east, 2 - south, 3 - west)
     */
    public void setDir(int dir){
        this.dir = dir;
    }

    /*******************************************ENERGY*****************************************************/
    private void adjustBattery(double b){
        if (battery > 0 && battery <= 100) {
            double battLeft = 100 - battery;
            if (b > battLeft){
                System.out.println("Charged to 100");
                this.battery = 100;
            } else {
                if (this.battery + b <= 0) {
                    this.battery = 0;
                    this.isAlive = false;
                    System.out.println("Battery at zero! Please charge!"); 
                } else {
                    this.battery += b;
                    System.out.println("Battery changed by " + b + ". Battery now at " + this.battery + "!");
                }
            }
        }     

    }

    /**
     * Charge the rover's value for a specific amount of time. Uses 10 * time = battery percent.
     * @param time time you want rover to charge for
     */
    public void chargeBattery(int time) {

        System.out.println("Charging battery for " + time + " seconds!");
        
        Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
               System.out.println(".");
            }
        }, 0, time * 1000);
        
        try {
            Thread.sleep(time * 1000);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        adjustBattery(10 * time);
    }

    // methods - stuff the Rover can do

    /**
     * Move the rover in by "v" in the current direction the rover is facing
     * @param v how far you want the rover to move.
     */
    public void move(int v)
    {
        if (isAlive) {
            if (dir == 0)
            {
                y += v;
            }
            else if (dir == 1)
            {
                x += v;
            }
            else if (dir == 2)
            {
                y -= v;
            }
            else 
            {
                x -= v;
            }

            System.out.println(name + " moved " + getDirectionName(dir) + " by " + v);
            adjustBattery(v * -4);
        } else {
            printDead("move");
        }
    }

    public void rotateLeft() 
    {
        if (isAlive) {
            dir -= 1;

            if (dir < 0)
            {
                dir = 3;
            }

            System.out.println(name + " turned to the left");
            adjustBattery(-5);
        } else {
            printDead("rotate left");
        }
    }

    public void rotateRight()
    {
        if (isAlive) {
            dir += 1;

            if (dir == 4)
            {
                dir = 0;
            }

            System.out.println(name + " turned to the right");     
            adjustBattery(-5);
        } else {
            printDead("rotate right");;
        }
    }

    /**
     * Teleports to rover to given x and y coordinant.
     * @param x x position
     * @param y y position
     */
    public void teleport(int x, int y) {
        if (isAlive){
            this.x = x;
            this.y = y;
            System.out.println(this.name + " has teleported! It's coordinance is now (" + x + ", " + y + ")!");
            adjustBattery(-10);
        } else {
            printDead("teleport");
        }
    }

    /**
     * Sends the rover home, or to (0,0)
     */
    public void goHome() {
        if (isAlive) {
            this.x = 0;
            this.y = 0;
            System.out.println(this.name + " has made it home!");
            adjustBattery(-10);
        } else {
            printDead("go home");
        }
    }

    
    /*******************************************PICTUERES*************************************************/    

    /**
     * Takes a picture at the rover's current coordinace
     */
    public void takePicture() {
        if (this.isAlive) {
            if (numPics < maxPics){
                System.out.println(this.name + " has taken a picture at (" + this.x + ", " + this.y + ") facing " + getDirectionName(this.dir) + "!");
                numPics += 1;
                adjustBattery(-2);
            } else {
                storageFull();
            }
        } else {
            printDead("take picture");
        }
    }

   

    /**
     * Takes a picture with filter
     * @param filter name of filter you want to use
     */
    public void takePicture(String filter) {
        if (this.isAlive) {
            System.out.println(this.name + " has taken a picture at (" + this.x + ", " + this.y + ") using the \"" + filter + "\" filter!");
            numPics += 1;
            adjustBattery(-2);
        } else {
            printDead("take picture");
        }

    }

    /**
     * Called when storage is full. Prints and error message.
     */
    private void storageFull() {
        System.out.println("Storage full! Please transmit pictures back to earth!");

    }

    /**
     * Transmit pictures back to earth. Set numPics back to zero.
     */
    public void transmitPictures() {

        if (this.isAlive) {
            System.out.println("Transmitting.....");
            this.numPics = 0; 
            try        
            {
                Thread.sleep(5000);
            } 
            catch(InterruptedException ex) 
            {
                Thread.currentThread().interrupt();
            }

            System.out.println("Transmitted!");
            adjustBattery(-5);
        } else {
            printDead("transmit pictures");
        }
    }

    
    /**
     * Kill another rover
     * @param other Rover object you want to kill
     */
    public void kill(Rover other){
        if (other.isAlive != true){
            System.out.println(other.name + " is already dead!");
        } else if (isAlive) {
            if (other.name == this.name) {
                System.out.println("Please contact the Suicide Hotline at 1-800-273-8255 beacue " + this.name + " cannot kill itself!");
            } else {
                System.out.println(this.name + " has killed " + other.name + "!");
                other.isAlive = false;
            }
        } else {
            printDead("kill " + other.name);
        }
    }

    public void attack(Rover other, int power) {
        
       int otherPower = randomWithRange(1, 100);
       
       if (otherPower == power) {
           System.out.println("It's a tie! Try again!");        
        } else if (otherPower > power) {
            System.out.println("You lost");
        } else {
            System.out.println("You won!");
        }
        
    }
    
    /**
     * Returns int dir value as string
     * @param num takes num value of direction
     * @return string of dir
     */
    private String getDirectionName(int num){
        if (num == 0){
            return "North";
        } else if (num == 1) {
            return "East";
        } else if (num == 2) {
            return "South";
        } else {
            return "West";
        }
    }

    private void printDead(String action) {
        System.out.println(this.name + " could not complete action \"" + action + "\" because it is dead");
    }

    private int randomWithRange(int min, int max) {
        int range = (max - min) + 1;
        return (int)(Math.random() * range) + min;
    }
    
    /**
     * Returns name of the rover
     * @return name of rover
     */
    
    public String getName() {
        return this.name;
    }
    
    public String toString() 
    {
        return "Rover[name=" + name + ", x=" + x + ", y=" + y + ", dir=" + getDirectionName(this.dir) + "]";
    }
}
