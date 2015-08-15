package tools;

import gfx.Drawing;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import states.StateBuilder;

public class ToolBoard extends Tool
{
    
    public ToolBoard(StateBuilder state)
    {
        super(state, state.getToolNext(), "BOARD EDITOR", Drawing.getImage("icon/board.png"));
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