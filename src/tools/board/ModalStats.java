package tools.board;

import board.BoardFile;
import gfx.Fonts;
import gfx.Text;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import tools.modal.Modal;
import tools.ToolBoard;
import tools.ui.Button;
import tools.ui.Intbox;
import tools.ui.Textbox;

public class ModalStats extends Modal
{
    private ToolBoard modalTool;
    private Textbox modalInputName;
    private Intbox modalInputSizeX, modalInputSizeY;
    private Button modalInputAccept;
    
    public ModalStats(ToolBoard tool)
    {
        // Modal
        super("Settings", new Rectangle(433, 259, 500, 250), true);
        this.modalTool = tool;
        
        // Form
        this.modalInputName = new Textbox("STATS_NAME", new Rectangle(this.getArea().x + 125, this.getArea().y + 50, 355, 25), "", 12);
        this.modalInputSizeX = new Intbox("STATS_SIZE_X", new Rectangle(this.getArea().x + 125, this.getArea().y + 90, 170, 25), 0, 0, 999);
        this.modalInputSizeY = new Intbox("STATS_SIZE_Y", new Rectangle(this.getArea().x + 310, this.getArea().y + 90, 170, 25), 0, 0, 999);
        this.modalInputAccept = new Button("OK", "OK", 583, 434);
    }
    
    private BoardFile getFile()
    {
        return this.modalTool.getFile();
    }
    
    public void inputClick(MouseEvent e)
    {
        // NOTE: should be doing something if the name textbox is clicked on
        
        // Intbar Arrows
        if(this.modalInputSizeX.getAreaArrow1().contains(e.getPoint())) {this.modalInputSizeX.arrow1();}
        if(this.modalInputSizeX.getAreaArrow2().contains(e.getPoint())) {this.modalInputSizeX.arrow2();}
        if(this.modalInputSizeY.getAreaArrow1().contains(e.getPoint())) {this.modalInputSizeY.arrow1();}
        if(this.modalInputSizeY.getAreaArrow2().contains(e.getPoint())) {this.modalInputSizeY.arrow2();}
        
        // Accept Button
        if(this.modalInputAccept.getArea().contains(e.getPoint()))
        {
            this.modalTool.getFile().setBoardName(this.modalInputName.getValue());
            this.modalTool.getFile().setBoardSize(this.modalInputSizeX.getValueInt(), this.modalInputSizeY.getValueInt());
            this.modalTool.setModalSettings(false);
        }
    }
    
    public void inputKey(String key)
    {
        //
    }
    
    public void loadValues()
    {
        this.modalInputName.setValue(this.getFile().getName());
        this.modalInputSizeX.setValue("" + this.getFile().getSizeX());
        this.modalInputSizeY.setValue("" + this.getFile().getSizeY());
    }
    
    public void render(Graphics g)
    {
        super.renderModal(g);
        this.renderForm(g);
    }
    
    private void renderForm(Graphics g)
    {
        // Name
        Text.write(g, "NAME", this.getArea().x + 25, this.getArea().y + 70, "LEFT", Fonts.getFont("STANDARD"), Color.BLACK);
        this.modalInputName.render(g);
        
        // Size
        Text.write(g, "SIZE", this.getArea().x + 25, this.getArea().y + 110, "LEFT", Fonts.getFont("STANDARD"), Color.BLACK);
        this.modalInputSizeX.render(g);
        this.modalInputSizeY.render(g);
        
        // Accept
        this.modalInputAccept.render(g);
        
    }
    
    public void tick()
    {
        //
    }
    
}