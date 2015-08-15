package tools.taskbar;

import gfx.Drawing;
import gfx.Text;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import states.StateBuilder;

public class TaskbarMenu
{
    private StateBuilder state;
    private Rectangle menuArea;
    private ArrayList<TaskbarMenuItem> menuItems;
    private boolean menuActive;
    
    public TaskbarMenu(StateBuilder state)
    {
        this.state = state;
        this.menuArea = new Rectangle(0, 200, 250, 518);
        this.menuItems = new ArrayList();
        this.menuActive = false;
        
        // TEMP
        this.menuItems.add(new TaskbarMenuItem(this, "QUIT", null));
    }
    
    public void inputClick()
    {
        if(this.menuActive) {this.menuActive = false;}
        else {this.menuActive = true;}
    }
    
    public boolean isActive()
    {
        return this.menuActive;
    }
    
    public void render(Graphics g)
    {
        // Background
        Drawing.fillRect(g, this.menuArea, Color.WHITE);
        
        // Items
        for(int x = 0; x < this.menuItems.size(); x++)
        {
            this.menuItems.get(x).render(g, 500);
        }
        
        // Border
        Drawing.drawRect(g, this.menuArea, Color.BLACK);
    }
    
    public void tick()
    {
        
    }
    
}