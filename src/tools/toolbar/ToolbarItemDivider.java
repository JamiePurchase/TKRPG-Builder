package tools.toolbar;

import gfx.Text;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

public class ToolbarItemDivider extends ToolbarItem
{
    
    public ToolbarItemDivider(Toolbar parent, int pos)
    {
        super(parent, "", 10, pos);
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
        g.drawLine(this.getRect().x, this.getRect().y, this.getRect().x, this.getRect().y + this.getRect().height);
    }
    
    public void tick()
    {
        //
    }
    
}