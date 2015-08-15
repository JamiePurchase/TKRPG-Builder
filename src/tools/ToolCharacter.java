package tools;

import gfx.Drawing;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import states.StateBuilder;

public class ToolCharacter extends Tool
{
    
    public ToolCharacter(StateBuilder state)
    {
        super(state, state.getToolNext(), "CHARACTER EDITOR", Drawing.getImage("icon/tool_characters.png"));
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