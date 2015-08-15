package tools.desktop;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class DesktopItem
{
    private String itemName;
    private BufferedImage itemIcon;
    
    public DesktopItem(String name, BufferedImage icon)
    {
        this.itemName = name;
        this.itemIcon = icon;
    }
    
    public void render(Graphics g)
    {
        
    }
    
    public void tick()
    {
        
    }
    
}