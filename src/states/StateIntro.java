package states;

import app.Engine;
import gfx.Colour;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import projects.ProjectManager;

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
        // TEMP
        Engine.setState(new StateBuilder());
    }
    
}