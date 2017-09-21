
/**
 * Write a description of class RoverRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RoverRunner
{
    public static void main(String[] arrrggggggs)
    {
        Rover r1 = new Rover("Curiosity");
        Rover r2 = new Rover("Spirit");
        Rover r3 = new Rover("Sojourner");
        
        System.out.println(r1);
        
        r1.move();
        System.out.println(r1);

        r1.rotateRight();
        System.out.println(r1);
        
        r2.rotateLeft();
        System.out.println(r2);
        
        r2.move();
        System.out.println(r2);
        
        r3.rotateLeft();
        System.out.println(r3);
        
        r3.move();
        System.out.println(r3);
        
        r1.move();
        System.out.println(r1);
    }
}
