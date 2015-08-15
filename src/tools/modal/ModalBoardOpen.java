package tools.modal;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import tools.files.FileBrowser;

public class ModalBoardOpen extends FileBrowser
{
    
    public ModalBoardOpen()
    {
        super("OPEN BOARD");
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
        // NOTE: which of the render methods should be getting called?
    }
    
    public void tick()
    {
        //
    }
    
}