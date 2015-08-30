package tools;

import gfx.Drawing;
import gfx.Text;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import states.StateBuilder;
import styles.Scheme;
import tools.modal.Modal;

public class ToolMain extends Tool
{
    private Modal modal;
    
    public ToolMain(StateBuilder state)
    {
        super(state, "MAIN", state.getToolNext(), "TKRPG Builder", new Rectangle(0, 0, 1366, 718), null);
        this.modal = null;
    }
    
    private Rectangle getInfoRect()
    {
        return new Rectangle(this.getToolRect().x, this.getToolRect().y + this.getToolRect().height - 30, this.getToolRect().width, 30);
    }
    
    public void inputClick(MouseEvent e)
    {
        if(this.modal != null)
        {
            if(this.modal.getArea().contains(e.getPoint()))
            {
                this.modal.inputClick(e);
            }
            //Engine.getAudio().playSound("ERROR");
        }
    }
    
    public void inputKey(String key)
    {
        //
    }
    
    public void render(Graphics g)
    {
        this.renderWindow(g);
        this.renderInfo(g);
        if(this.modal != null) {this.modal.render(g);}
        this.renderBorder(g);
    }
    
    private void renderInfo(Graphics g)
    {
        // Background
        Drawing.fillRect(g, this.getInfoRect(), Scheme.Colour("SECONDARY_FORE"));
        
        // Text
        Text.write(g, "HELLO", this.getInfoRect().x + 10, this.getInfoRect().y + 20, "LEFT", "INFOBAR", "BLACK");
        
        // Border
        Drawing.drawRect(g, this.getInfoRect(), 5, Scheme.Colour("STANDARD_FORE"));
    }
    
    public void tick()
    {
        
    }
    
}