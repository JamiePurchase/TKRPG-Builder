package tools.modal;

import gfx.Text;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import states.StateBuilder;
import tools.ui.Button;

public class ModalConfig extends Modal
{
    private StateBuilder state;
    private Button buttonAccept;
    
    public ModalConfig(StateBuilder state)
    {
        super("Settings", new Rectangle(433, 259, 500, 250), true);
        this.state = state;
        this.buttonAccept = new Button("OK", "OK", 583, 434);
    }
    
    public void inputClick(MouseEvent e)
    {
        // Accept (NOTE: do we need to save when accept is clicked?)
        if(this.buttonAccept.getArea().contains(e.getPoint())) {state.actionConfig(false);}
    }
    
    public void inputKey(String key)
    {
        //
    }
    
    public void render(Graphics g)
    {
        this.renderModal(g);
        this.renderOptions(g);
        this.buttonAccept.render(g);
    }
    
    private void renderOptions(Graphics g)
    {
        Text.write(g, "Scheme", this.getArea().x + 25, this.getArea().y + 75, "LEFT", "MESSAGE_STANDARD", "BLACK");
    }
    
    public void tick()
    {
        
    }
    
}