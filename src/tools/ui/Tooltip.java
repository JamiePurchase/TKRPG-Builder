package tools.ui;

import gfx.Drawing;
import gfx.Text;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import styles.Scheme;

public class Tooltip
{
    private String tooltipValue;
    private Rectangle tooltipRect;
    
    public Tooltip(String value, Point cursor)
    {
        this.tooltipValue = value;
        this.tooltipRect = new Rectangle(cursor.x, cursor.y, 200, 25);
    }
    
    public void render(Graphics g)
    {
        // Background
        Drawing.fillRect(g, this.tooltipRect, Scheme.Colour("STANDARD_FORE"));
        
        // Text
        Text.write(g, this.tooltipValue, this.tooltipRect.x + (this.tooltipRect.width / 2), this.tooltipRect.y + 20, "CENTER", "TOOLTIP", "BLACK");
        
        // Border
        Drawing.drawRect(g, this.tooltipRect, "BLACK");
    }
    
}