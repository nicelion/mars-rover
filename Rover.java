
/**
 * Write a description of class Rover here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Rover
{
    // fields
    private String name;
    private int x;
    private int y;
    private int dir; // 0=North, 1=East, 2=South, 3=West
    
    
    // constructor(s)

    
    public Rover(String name, int x, int y, int dir) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.dir = dir;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setDir(int dir){
        this.dir = dir;
    }
    
    // methods - stuff the Rover can do
    private String dirToStr(int num){
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
    
    public void move(int v)
    {
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
        
        System.out.println(name + " moved " + dirToStr(dir) + " by " + v);
    }
    
    public void rotateLeft() 
    {
        dir -= 1;
        
        if (dir < 0)
        {
            dir = 3;
        }
        
        System.out.println(name + " turned to the left");        
    }
    
    public void rotateRight()
    {
        dir += 1;
        
        if (dir == 4)
        {
            dir = 0;
        }
        
        System.out.println(name + " turned to the right");        
    }

    public String toString() 
    {
        return "Rover[name=" + name + ", x=" + x + ", y=" + y + ", dir=" + dir + "]";
    }
}
