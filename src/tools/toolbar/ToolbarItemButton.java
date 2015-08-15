package tools.toolbar;

import app.Engine;
import gfx.Drawing;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class ToolbarItemButton extends ToolbarItem
{
    private BufferedImage buttonImage;
    private boolean buttonEnabled;
    private boolean buttonDisable;
    
    public ToolbarItemButton(Toolbar parent, String ref, int pos, BufferedImage image, boolean disable)
    {
        super(parent, ref, 34, pos);
        this.buttonImage = image;
        this.buttonEnabled = true;
        this.buttonDisable = disable;
    }
    
    private Rectangle getButtonRect()
    {
        return new Rectangle(this.getRect().x, this.getRect().y + 4, 34, 34);
    }
    
    public void inputClick(MouseEvent e)
    {
        //
    }
    
    public void inputKey(String key)
    {
        //
    }
    
    public void render(Graphics g)
    {
        // Image
        if(this.buttonDisable) {Drawing.drawImageOpaque(g, this.buttonImage, this.getButtonRect().x, this.getButtonRect().y, 0.3f);}
        else {g.drawImage(this.buttonImage, this.getButtonRect().x, this.getButtonRect().y, null);}
        
        // Fade
        //if(this.buttonDisable) {Drawing.}
        
        // Border
        Drawing.drawRect(g, this.getButtonRect(), "BLACK");
    }
    
    public void setDisable(boolean value)
    {
        this.buttonDisable = value;
    }
    
    public void tick()
    {
        
    }
    
}