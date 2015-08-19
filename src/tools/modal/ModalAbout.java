package tools.modal;

import gfx.Text;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import states.StateBuilder;
import tools.ui.Button;

public class ModalAbout extends ModalMessage
{
    private StateBuilder state;
    
    public ModalAbout(StateBuilder state)
    {
        super("About", new Rectangle(433, 259, 500, 250), true, "TKRPG Builder (version 0.1)", "Built with NetBeans");
        this.state = state;
    }
    
    public void inputClick(MouseEvent e)
    {
        // Accept
        if(this.getButtonAccept().getArea().contains(e.getPoint())) {state.actionAbout(false);}
    }
    
    public void inputKey(String key)
    {
        //
    }
    
    public void render(Graphics g)
    {
        this.renderModal(g);
        this.renderMessage(g);
    }
    
    public void tick()
    {
        
    }
    
}