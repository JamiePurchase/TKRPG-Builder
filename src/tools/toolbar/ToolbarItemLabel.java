package tools.toolbar;

import gfx.Text;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

public class ToolbarItemLabel extends ToolbarItem
{
    private String labelText;
    
    public ToolbarItemLabel(Toolbar parent, String label, int size, int pos)
    {
        super(parent, "", size + 6, pos);
        this.labelText = label;
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
        Text.write(g, this.labelText, this.getRect().x - 2, this.getRect().y + 28, "LEFT", "TOOLBAR", "BLACK");
        //Text.writeRotate(g, this.labelText, this.getRect().x - 2, this.getRect().y + 28, "TOOLBAR", "WHITE");
    }
    
    public void tick()
    {
        //
    }
    
}