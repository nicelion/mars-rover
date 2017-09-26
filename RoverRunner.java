
/**
 * Write a description of class RoverRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RoverRunner
{
    public static void printRover(Rover r) {
        System.out.println(r);
        
    }
    
    public static void main(String[] arrrggggggs)
    {
        Rover r1 = new Rover("Curiosity", 0, 0, 0);
        Rover r2 = new Rover("Spirit", 0, 0, 0);
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
        
 
    }
}
