package tools.taskbar;

import gfx.Text;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class TaskbarMenuItem
{
    private TaskbarMenu itemMenu;
    private String itemName;
    private BufferedImage itemIcon;
    private boolean itemSubmenu;
    
    public TaskbarMenuItem(TaskbarMenu menu, String name, BufferedImage icon)
    {
        this.itemMenu = menu;
        this.itemName = name;
        this.itemIcon = icon;
        this.itemSubmenu = false;
    }
    
    public String getName()
    {
        return this.itemName;
    }
    
    public void render(Graphics g, int pos)
    {
        this.renderIcon(g, pos);
        this.renderText(g, pos);
        this.renderSide(g, pos);
    }
    
    private void renderIcon(Graphics g, int pos)
    {
        if(this.itemIcon != null) {g.drawImage(this.itemIcon, 10, pos, null);}
    }
    
    private void renderSide(Graphics g, int pos)
    {
        if(this.itemSubmenu) {Text.write(g, ">", 225, pos, "RIGHT", "TASKBAR_MENU_ITEM", "BLACK");}
    }
    
    private void renderText(Graphics g, int pos)
    {
        Text.write(g, this.getName(), 60, pos, "LEFT", "TASKBAR_MENU_ITEM", "BLACK");
    }
    
    public void tick()
    {
        
    }
    
}