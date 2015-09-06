package states;

import app.Engine;
import debug.Console;
import gfx.Colour;
import gfx.Drawing;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import projects.ProjectFile;

public class StateEngine extends State
{
    private ProjectFile project;
    
    public StateEngine(ProjectFile project)
    {
        this.project = project;
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
        Console.print("STATE ENGINE -> INPUT KEY TYPE (" + key + ")");
        // TEST
        if(key.equals("ESCAPE")) {Engine.setState(new StateBuilder());}
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
        // TEST
        Drawing.fillRect(g, 0, 0, 1366, 768, Colour.getColour("BLACK"));
    }
    
    public void tick()
    {
        //
    }
    
}