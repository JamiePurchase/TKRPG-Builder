package tools.modal;

import board.BoardFile;
import gfx.Text;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import tools.ToolBoard;
import tools.ui.Button;
import tools.ui.Intbox;
import tools.ui.Textbox;

public class ModalBoardSettings extends Modal
{
    private ToolBoard tool;
    private BoardFile file;
    private Textbox textboxName;
    private Intbox textboxSizeX, textboxSizeY;
    private Button buttonAccept;
    
    public ModalBoardSettings(ToolBoard tool, BoardFile file)
    {
        super("Settings", new Rectangle(483, 259, 400, 250), true);
        this.tool = tool;
        this.file = file;
        this.textboxName = new Textbox("NAME", new Rectangle(), file.getName(), 12);
        this.textboxSizeX = new Intbox("SIZE_X", new Rectangle(), file.getSizeX());
        this.textboxSizeY = new Intbox("SIZE_Y", new Rectangle(), file.getSizeY());
        this.buttonAccept = new Button("OK", "OK", 583, 434);
        // NOTE: need to consider cleaning up the focus (when one gains, others must lose)
    }
    
    public void inputClick(MouseEvent e)
    {
        if(this.textboxName.getArea().contains(e.getPoint())) {this.textboxName.focus(true);}
        
        // NOTE: these boxes also have buttons on them, so we need to have a click method
        if(this.textboxSizeX.getArea().contains(e.getPoint())) {this.textboxSizeX.focus(true);}
        if(this.textboxSizeY.getArea().contains(e.getPoint())) {this.textboxSizeY.focus(true);}
        if(this.buttonAccept.getArea().contains(e.getPoint()))
        {
            // NOTE: update the boardFile
        }
    }
    
    public void inputKey(String key)
    {
        //
    }
    
    public void render(Graphics g)
    {
        this.renderModal(g);
        this.renderContent(g);
    }
    
    private void renderContent(Graphics g)
    {
        // Board Name
        Text.write(g, "Name", this.getArea().x + 25, this.getArea().y + 75, "LEFT", "MESSAGE_STANDARD", "BLACK");
        this.textboxName.render(g);
        
        // Board Size
        Text.write(g, "Size", this.getArea().x + 25, this.getArea().y + 125, "LEFT", "MESSAGE_STANDARD", "BLACK");
        this.textboxSizeX.render(g);
        this.textboxSizeY.render(g);
        
        // Accept Button
        this.buttonAccept.render(g);
    }
    
    public void tick()
    {
        //
    }
    
}