package states;

import app.Engine;
import gfx.Colour;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import tools.modal.ModalMessage;
import java.lang.System;

public class StateExit extends State
{
    private ModalMessage exitMessage;
    
    public StateExit()
    {
        this.exitMessage = new ModalMessage("TKRPG Builder", false, "Thanks for using the TKRPG builder.", "");
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
        if(this.exitMessage.getArea().contains(e.getPoint())) {System.exit(0);}
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
        
        // Message Box
        exitMessage.render(g);
        
        // NOTE: May wish to save the canvas as an image and just draw that
    }

    public void tick()
    {
        //
    }
    
}