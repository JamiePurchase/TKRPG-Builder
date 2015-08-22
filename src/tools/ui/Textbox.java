package tools.ui;

import app.Engine;
import gfx.Drawing;
import gfx.Text;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import styles.Scheme;
import tools.focus.Focus;

public class Textbox extends Focus
{
    private String textboxRef, textboxValue;
    private Rectangle textboxArea;
    private int textboxLimit;
    private boolean textboxEnabled;
    
    public Textbox(String ref, Rectangle area, String value, int limit)
    {
        super(false);
        this.textboxRef = ref;
        this.textboxArea = area;
        this.textboxValue = value;
        this.textboxLimit = limit;
        this.textboxEnabled = true;
        
        // TEMP
        this.focus(true);
    }
    
    public Rectangle getArea()
    {
        return this.textboxArea;
    }
    
    public void inputKey(String key)
    {
        if(key.equals("DELETE")) {this.inputKeyDelete();}
        
        // NOTE: need to check if key is a valid character to type
        this.inputKeyInsert(key);
    }
    
    private void inputKeyDelete()
    {
        if(this.textboxValue.length() > 0) {this.textboxValue = this.textboxValue.substring(0, this.textboxValue.length() - 1);}
    }
    
    private void inputKeyInsert(String character)
    {
        if(this.textboxValue.length() < this.textboxLimit) {this.textboxValue += character;}
    }
    
    public void render(Graphics g)
    {
        // Background
        if(this.textboxArea.contains(Engine.getMousePoint()) || this.hasFocus()) {Drawing.fillRect(g, this.textboxArea, Scheme.Colour("SECONDARY_FORE"));}
        else {Drawing.fillRect(g, this.textboxArea, Scheme.Colour("STANDARD_BACK"));}
        
        // Value
        Text.write(g, this.textboxValue, this.textboxArea.x + 15, this.textboxArea.y + 22, "LEFT", "INPUT_STANDARD", "BLACK");
        
        // Border
        Drawing.drawRect(g, this.textboxArea, "BLACK");
    }
    
    public void setValue(String value)
    {
        this.textboxValue = value;
    }
    
}