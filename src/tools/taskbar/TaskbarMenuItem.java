package tools.taskbar;

import app.Engine;
import gfx.Colour;
import gfx.Drawing;
import gfx.Text;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import styles.Scheme;

public class TaskbarMenuItem
{
    private TaskbarMenu itemMenu;
    private String itemRef, itemName;
    private BufferedImage itemIcon;
    private boolean itemSubmenu;
    
    public TaskbarMenuItem(TaskbarMenu menu, String ref, String name, String icon)
    {
        this.itemMenu = menu;
        this.itemRef = ref;
        this.itemName = name;
        if(icon == null) {this.itemIcon = null;}
        else {this.itemIcon = Drawing.getImage("icon/" + icon + ".png");}
        this.itemSubmenu = false;
    }
    
    public Rectangle getArea(int pos)
    {
        return new Rectangle(this.itemMenu.getArea().x, this.itemMenu.getArea().y + (pos * 50), this.itemMenu.getArea().x + this.itemMenu.getArea().width, 50);
    }
    
    private Rectangle getAreaIcon(int pos)
    {
        return new Rectangle(this.itemMenu.getArea().x + 8, this.itemMenu.getArea().y + (pos * 50) + 8, 34, 34);
    }
    
    public String getName()
    {
        return this.itemName;
    }
    
    public String getRef()
    {
        return this.itemRef;
    }
    
    public void render(Graphics g, int pos)
    {
        if(this.getArea(pos).contains(Engine.getMousePoint())) {this.renderHighlight(g, pos);}
        if(this.itemIcon != null) {this.renderIcon(g, pos);}
        this.renderText(g, pos);
        this.renderSide(g, pos);
    }
    
    private void renderHighlight(Graphics g, int pos)
    {
        Drawing.fillRect(g, this.getArea(pos), Scheme.Colour("SECONDARY_FORE"));
    }
    
    private void renderIcon(Graphics g, int pos)
    {
        g.drawImage(this.itemIcon, this.getAreaIcon(pos).x, this.getAreaIcon(pos).y, null);
        Drawing.drawRect(g, this.getAreaIcon(pos), "BLACK");
    }
    
    private void renderSide(Graphics g, int pos)
    {
        if(this.itemSubmenu) {Text.write(g, ">", 225, pos + 25, "RIGHT", "TASKBAR_MENU_ITEM", "BLACK");}
    }
    
    private void renderText(Graphics g, int pos)
    {
        Text.write(g, this.getName(), this.getArea(pos).x + 60, this.getArea(pos).y + 35, "LEFT", "TASKBAR_MENU_ITEM", "BLACK");
    }
    
    public void tick()
    {
        
    }
    
}