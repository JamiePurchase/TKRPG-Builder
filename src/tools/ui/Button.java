package tools.ui;

import app.Engine;
import gfx.Drawing;
import gfx.Text;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Button
{
    private String buttonRef, buttonText;
    private Rectangle buttonArea;
    
    public Button(String ref, String text, int posX, int posY)
    {
        this.buttonRef = ref;
        this.buttonText = text;
        this.buttonArea = new Rectangle(posX, posY, 200, 50);
    }
    
    public Rectangle getArea()
    {
        return this.buttonArea;
    }
    
    public void render(Graphics g)
    {
        // Background
        if(this.getArea().contains(Engine.getMousePoint())) {Drawing.fillRect(g, this.buttonArea, "SECONDARY_FORE");}
        else {Drawing.fillRect(g, this.buttonArea, "STANDARD_FORE");}
        
        // Caption
        Text.write(g, this.buttonText, this.buttonArea.x + (this.buttonArea.width / 2), this.buttonArea.y + 35, "CENTER", "BUTTON_STANDARD", "BLACK");
        
        // Border
        Drawing.drawRect(g, this.buttonArea, "BLACK");
    }
    
    public void tick()
    {
        
    }
    
}