package states;

import app.Engine;
import gfx.Colour;
import input.gui.KeyboardGui;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.io.File;

public class StateLoad extends State
{
    // TEMP
    private KeyboardGui keyboard;
    
    public StateLoad()
    {
        // TEMP
        this.keyboard = new KeyboardGui();
    }

    public void inputKeyPress(String key)
    {
        //
    }

    public void inputKeyRelease(String key)
    {
        //
    }

    public void inputKeyType(String key)
    {
        //
    }

    public void inputMouseClickL(MouseEvent e)
    {
        //
    }

    public void inputMouseClickR(MouseEvent e)
    {
        //
    }

    public void inputMouseMove(MouseEvent e)
    {
        //
    }
    
    public void render(Graphics g)
    {
        // Background
        g.setColor(Colour.getColour("BLACK"));
        g.fillRect(0, 0, 1366, 768);
        
        // TEMP
        this.keyboard.render(g);
    }

    public void tick()
    {
        // TEMP
        //Engine.setState(new StateBuilder());
    }
    
}