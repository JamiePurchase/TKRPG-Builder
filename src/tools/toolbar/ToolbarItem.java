package tools.toolbar;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

public abstract class ToolbarItem
{
    private Toolbar itemParent;
    private String itemRef;
    private int itemSize;
    private int itemPos;
    
    public ToolbarItem(Toolbar parent, String ref, int size, int pos)
    {
        this.itemParent = parent;
        this.itemRef = ref;
        this.itemSize = size;
        this.itemPos = pos + 4;
    }
    
    public int getPositionEnd()
    {
        return this.itemPos + this.itemSize + 4;
    }
    
    public Rectangle getRect()
    {
        return new Rectangle(this.itemPos, this.itemParent.getArea().y, this.itemSize, this.itemParent.getArea().height);
    }
    
    public String getRef()
    {
        return this.itemRef;
    }
    
    public abstract void inputClick(MouseEvent e);
    
    public abstract void inputKey(String key);
    
    public abstract void render(Graphics g);
    
    public abstract void tick();
    
}