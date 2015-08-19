package tools.taskbar;

import app.Engine;
import gfx.Drawing;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import tools.Tool;
import tools.ui.Tooltip;

public class TaskbarItem
{
    private String itemName;
    private Tool itemTool;
    private BufferedImage itemIcon;
    
    public TaskbarItem(String name, Tool tool, BufferedImage icon)
    {
        this.itemName = name;
        this.itemTool = tool;
        this.itemIcon = icon;
    }
    
    public Rectangle getArea(int position)
    {
        return new Rectangle((70 * position) + 150, 718, 70, 50);
    }
    
    public void render(Graphics g, int position)
    {        
        // Fill
        String fill = "STANDARD_FORE";
        if(this.getArea(position).contains(Engine.getMousePoint()))
        {
            fill = "SECONDARY_FORE";
            
            // NOTE: tooltips need a bit of work
            // NOTE: may wish to make this part of the Application.jar and just pass in styles from this app
            //if(Engine.getMouseIdle() || Engine.getTooltipActive() == false) {this.renderTooltip(g);}
        }
        Drawing.fillRect(g, this.getArea(position), fill);
        
        // Icon
        g.drawImage(this.itemIcon, this.getArea(position).x + 10, 718, null);
    }
    
    private void renderTooltip(Graphics g)
    {
        new Tooltip(this.itemName, Engine.getMousePoint()).render(g);
        Engine.setTooltipActive(true);
    }
    
    public void tick()
    {
        
    }
    
}