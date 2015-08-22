package tools.modal;

import gfx.Colour;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

public class ModalBoardNew extends Modal
{
    //private String input
    
    public ModalBoardNew()
    {
        super("New Board", new Rectangle(433, 209, 500, 350), true);
        
        // TEMP
        /*BlockForm form = new BlockForm("NEW_");
        form.addBlock("LABEL_NAME", new Block());
        this.addBlock(form);*/
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
        this.renderOptions(g);
    }
    
    private void renderOptions(Graphics g)
    {
        /*g.setColor(Scheme.Colour("SECONDARY_FORE"));
        g.drawRect(600, 250, 200, 50);*/
    }
    
    public void tick()
    {
        //
    }
    
}