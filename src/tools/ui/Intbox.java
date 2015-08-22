package tools.ui;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Intbox extends Textbox
{
    
    public Intbox(String ref, Rectangle area, int value)
    {
        super(ref, area, "" + value, 0);
    }
    
    public void inputKey(String key)
    {
        // NOTE: check if numeric
    }
    
    public void render(Graphics g)
    {
        super.render(g);
        this.renderArrows(g);
    }
    
    private void renderArrows(Graphics g)
    {
        // NOTE: arrows at the edge of the textbox
    }
    
}