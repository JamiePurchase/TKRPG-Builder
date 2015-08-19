package tools.modal;

import blocks.BlockGroup;
import blocks.form.BlockForm;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class ModalProjectNew extends Modal
{
    //private String input
    
    public ModalProjectNew()
    {
        super("New Project", new Rectangle(433, 209, 500, 350), true);
        
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
    }
    
    public void tick()
    {
        
    }
    
}