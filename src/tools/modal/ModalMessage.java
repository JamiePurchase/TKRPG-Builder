package tools.modal;

import gfx.Text;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import tools.ui.Button;

public class ModalMessage extends Modal
{
    private String message1, message2;
    private Button buttonAccept;
    
    public ModalMessage(String title, boolean close, String message1, String message2)
    {
        super(title, new Rectangle(333, 259, 700, 250), close);
        this.setMessage(message1, message2);
        this.buttonAccept = new Button("OK", "OK", 583, 434);
    }
    
    public ModalMessage(String title, Rectangle area, boolean close, String message1, String message2)
    {
        super(title, area, close);
        this.setMessage(message1, message2);
        this.buttonAccept = new Button("OK", "OK", 583, 434);
    }
    
    public Button getButtonAccept()
    {
        return this.buttonAccept;
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
        this.renderModal(g);
        this.renderMessage(g);
    }
    
    public void renderMessage(Graphics g)
    {
        // Message Text
        Text.write(g, this.message1, this.getArea().x + 25, this.getArea().y + 75, "LEFT", "MESSAGE_STANDARD", "BLACK");
        Text.write(g, this.message2, this.getArea().x + 25, this.getArea().y + 125, "LEFT", "MESSAGE_STANDARD", "BLACK");
        
        // Accept Button
        this.buttonAccept.render(g);
    }
    
    public void setMessage(String message1, String message2)
    {
        this.message1 = message1;
        this.message2 = message2;
    }
    
    public void tick()
    {
        //
    }
    
}