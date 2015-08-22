package tools.toolbar;

import app.Engine;
import gfx.Drawing;
import gfx.Text;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import styles.Scheme;

public class Toolbar 
{
    private Rectangle toolbarArea;
    private ArrayList<ToolbarItem> toolbarItem;
    
    public Toolbar(Rectangle area)
    {
        this.toolbarArea = area;
        this.toolbarItem = new ArrayList();
    }
    
    public void addButton(String ref, String icon)
    {
        this.addButton(ref, icon, false);
    }
    
    public void addButton(String ref, String icon, boolean disable)
    {
        ToolbarItemButton button = new ToolbarItemButton(this, ref, this.getPositionNext(), Drawing.getImage("icon/" + icon + ".png"), disable);
        this.toolbarItem.add(button);
    }
    
    public void addDivider()
    {
        ToolbarItemDivider divider = new ToolbarItemDivider(this, this.getPositionNext());
        this.toolbarItem.add(divider);
    }
    
    public void addLabel(String caption)
    {
        ToolbarItemLabel label = new ToolbarItemLabel(this, caption, Text.getTextWidth(caption, "TOOLBAR"), this.getPositionNext());
        this.toolbarItem.add(label);
    }
    
    public Rectangle getArea()
    {
        return this.toolbarArea;
    }
    
    private Rectangle getAreaDraw()
    {
        return new Rectangle(this.toolbarArea.x + 5, this.toolbarArea.y, this.toolbarArea.width - 10, this.toolbarArea.height);
    }
    
    public ToolbarItemButton getButton(String search)
    {
        for(int x = 0; x < this.toolbarItem.size(); x++)
        {
            if(this.toolbarItem.get(x).getRef().equals(search) && this.toolbarItem.get(x) instanceof ToolbarItemButton)
            {
                return (ToolbarItemButton) this.toolbarItem.get(x);
            }
        }
        return null;
    }
    
    private ToolbarItem getItemLast()
    {
        return this.toolbarItem.get(this.toolbarItem.size() - 1);
    }
    
    private ToolbarItem getItemAt(int pos)
    {
        return this.toolbarItem.get(pos);
    }
    
    private int getPositionNext()
    {
        if(this.toolbarItem.size() > 0) {return this.getItemLast().getPositionEnd();}
        return 15;
    }
    
    public ToolbarItem inputClick(MouseEvent e)
    {
        for(int x = 0; x < this.toolbarItem.size(); x++)
        {
            if(this.toolbarItem.get(x).getRect().contains(e.getPoint())) {return this.toolbarItem.get(x);}
        }
        return null;
    }
    
    public void render(Graphics g)
    {
        this.renderBar(g);
        this.renderItems(g);
        this.renderBorder(g);
    }
    
    private void renderBar(Graphics g)
    {
        // Background
        Drawing.fillRect(g, this.getAreaDraw(), Scheme.Colour("SECONDARY_FORE"));
        
        // Border
        Drawing.drawRect(g, this.getAreaDraw(), "BLACK");
    }
    
    private void renderBorder(Graphics g)
    {
        // NOTE: is this necessary?
    }
    
    private void renderItems(Graphics g)
    {
        for(int x = 0; x < this.toolbarItem.size(); x++)
        {
            this.toolbarItem.get(x).render(g);
        }
    }
    
    public void tick()
    {
        //
    }
    
}