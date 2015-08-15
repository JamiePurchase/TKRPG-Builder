package tools.desktop;

import gfx.Drawing;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import states.StateBuilder;

public class Desktop
{
    private StateBuilder state;
    private final Rectangle desktopArea = new Rectangle(0, 0, 1366, 718);
    private ArrayList<DesktopItem> desktopItems;
    
    public Desktop(StateBuilder state)
    {
        this.state = state;
        this.desktopItems = new ArrayList();
    }
    
    public Rectangle getArea()
    {
        return this.desktopArea;
    }
    
    public void inputClick(MouseEvent e)
    {
        //
    }
    
    public void render(Graphics g)
    {
        this.renderBackground(g);
        this.renderItems(g);
    }
    
    private void renderBackground(Graphics g)
    {
        Drawing.fillRect(g, this.desktopArea, "DESKTOP_FILL");
    }
    
    private void renderItems(Graphics g)
    {
        
    }
    
    public void tick()
    {
        
    }
    
}