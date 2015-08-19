package tools;

import gfx.Drawing;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import states.StateBuilder;

public class ToolItem extends Tool
{
    
    public ToolItem(StateBuilder state)
    {
        super(state, "ITM", state.getToolNext(), "ITEM EDITOR", Drawing.getImage("icon/tool_items.png"));
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
        this.renderWindow(g);
    }
    
    public void tick()
    {
        //
    }
    
}