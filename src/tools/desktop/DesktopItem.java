package tools.desktop;

import app.Engine;
import gfx.Drawing;
import gfx.Fonts;
import gfx.Text;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import styles.Scheme;

public class DesktopItem
{
    private String itemRef, itemName;
    private BufferedImage itemIcon;
    
    public DesktopItem(String ref, String name, BufferedImage icon)
    {
        this.itemRef = ref;
        this.itemName = name;
        this.itemIcon = icon;
    }
    
    public Rectangle getRect(int position)
    {
        int posX = 0;
        while(position > 5)
        {
            position -= 6;
            posX += 1;
        }
        int posY = position;
        return new Rectangle((100 * posX) + 25, (100 * posY) + 25, 80, 80);
    }
    
    public String getRef()
    {
        return this.itemRef;
    }
    
    public void render(Graphics g, int position)
    {
        if(this.getRect(position).contains(Engine.getMousePoint())) {this.renderHighlight(g, position);}
        this.renderIcon(g, position);
        this.renderName(g, position);
    }
    
    private void renderHighlight(Graphics g, int position)
    {
        Drawing.fillRect(g, this.getRect(position), Scheme.Colour("SECONDARY_FORE"));
    }
    
    private void renderIcon(Graphics g, int position)
    {
        g.drawImage(this.itemIcon, this.getRect(position).x + 15, this.getRect(position).y + 5, null);
    }
    
    private void renderName(Graphics g, int position)
    {
        Text.write(g, this.itemName, this.getRect(position).x + (this.getRect(position).width / 2), this.getRect(position).y + 70, "CENTER", Fonts.getFont("STANDARD"), Color.BLACK);
    }
    
    public void tick()
    {
        
    }
    
}