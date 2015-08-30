package states;

import app.Engine;
import gfx.Colour;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.io.File;

public class StateIntro extends State
{
    private int loadState;
    
    public StateIntro()
    {
        this.loadState = 0;
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
    }

    public void tick()
    {
        // NOTE: need to look for the main config file for the app (this should be in the same dir as this jar)
        // if we found the config file, launch the StateLoad state and begin to setup the builder
        // else create the config file, launch the StateNew state and begin to setup the builder
        //Engine.setState(new StateLoad());
        
        Engine.setState(new StateBuilder());
    }
    
}