package tools.desktop;

import gfx.Drawing;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import states.StateBuilder;
import styles.Scheme;

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
    
    public void addItem(DesktopItem item)
    {
        this.desktopItems.add(item);
    }
    
    public Rectangle getArea()
    {
        return this.desktopArea;
    }
    
    public String inputClick(MouseEvent e)
    {
        for(int x = 0; x < this.desktopItems.size(); x++)
        {
            if(this.desktopItems.get(x).getRect(x).contains(e.getPoint()))
            {
                return desktopItems.get(x).getRef();
            }
        }
        return null;
    }
    
    public void render(Graphics g)
    {
        this.renderBackground(g);
        this.renderItems(g);
    }
    
    private void renderBackground(Graphics g)
    {
        Drawing.fillRect(g, this.desktopArea, Scheme.Colour("DESKTOP_FILL"));
    }
    
    private void renderItems(Graphics g)
    {
        for(int x = 0; x < this.desktopItems.size(); x++)
        {
            this.desktopItems.get(x).render(g, x);
        }
    }
    
    public void tick()
    {
        
    }
    
}