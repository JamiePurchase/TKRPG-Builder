package tools;

import gfx.Drawing;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import states.StateBuilder;
import tools.modal.Modal;
import tools.modal.ModalBoardNew;
import tools.toolbar.Toolbar;
import tools.toolbar.ToolbarItem;
import tools.toolbar.ToolbarItemButton;

public class ToolBoard extends Tool
{
    private Toolbar toolbar;
    private boolean boardFileActive, boardFileSaved;
    //private Board boardFileObject;
    private boolean modalActive;
    private Modal modalObject;
    private ToolBoardMode boardMode;
    private ToolBoardPaint optionPaintType;
    //private tile optionPaintTile;
    private boolean optionViewGrid, optionViewRuler;
    
    public ToolBoard(StateBuilder state)
    {
        super(state, state.getToolNext(), "BOARD EDITOR", Drawing.getImage("icon/tool_board.png"));
        
        // Board File
        this.boardFileActive = false;
        //this.boardFileObject = null;
        this.boardFileSaved = true;
        
        // Modal
        this.modalActive = false;
        this.modalObject = null;
        
        // Mode
        this.boardMode = ToolBoardMode.TERRAIN;
        
        // Paint
        this.optionPaintType = ToolBoardPaint.BRUSH;
        //this.optionPaintTile
        
        // Toolbar
        this.toolbarInit();
    }
    
    public void inputClick(MouseEvent e)
    {
        // Modal
        if(this.modalActive) {this.modalObject.inputClick(e);}
        else
        {
            // Toolbar
            ToolbarItem click = this.toolbar.inputClick(e);
            if(click != null) {this.toolbarEvent(click);}
        }
    }
    
    public void inputKey(String key)
    {
        //
    }
    
    public void render(Graphics g)
    {
        this.renderWindow(g);
        this.toolbar.render(g);
        //if(this.modalActive) {this.modalObject.render(g);}
        
        // TEST
        if(this.modalActive) {this.renderModal(g);}
    }
    
    private void renderModal(Graphics g)
    {
        Drawing.fadeScreen(g, "FADE_GREEN");
        this.modalObject.render(g);
    }
    
    private void setModal()
    {
        this.modalActive = false;
        this.modalObject = null;
    }
    
    private void setModal(Modal modal)
    {
        this.modalActive = true;
        this.modalObject = modal;
    }
    
    public void tick()
    {
        //
    }
    
    private void toolbarEvent(ToolbarItem item)
    {
        if(item.getRef().equals("FILE_NEW"))
        {
            this.setModal(new ModalBoardNew());
        }
        if(item.getRef().equals("VIEW_GRID"))
        {
            if(this.optionViewGrid)
            {
                this.optionViewGrid = false;
                this.toolbar.getButton("VIEW_GRID").setDisable(true);
            }
            else
            {
                this.optionViewGrid = true;
                this.toolbar.getButton("VIEW_GRID").setDisable(false);
            }
        }
        if(item.getRef().equals("VIEW_RULER"))
        {
            if(this.optionViewRuler)
            {
                this.optionViewRuler = false;
                this.toolbar.getButton("VIEW_RULER").setDisable(true);
            }
            else
            {
                this.optionViewRuler = true;
                this.toolbar.getButton("VIEW_RULER").setDisable(false);
            }
        }
    }
    
    private void toolbarInit()
    {
        this.toolbar = new Toolbar(new Rectangle(0, 34, 1366, 42));
        this.toolbar.addLabel("BOARD");
        this.toolbar.addButton("FILE_NEW", "file_new");
        this.toolbar.addButton("FILE_OPEN", "file_open");
        this.toolbar.addButton("FILE_SAVE", "file_save");
        this.toolbar.addButton("FILE_SAVE_AS", "file_saveAs");
        this.toolbar.addDivider();
        this.toolbar.addLabel("CONFIG");
        this.toolbar.addButton("OPTIONS_STATS", "board_stats");
        this.toolbar.addButton("OPTIONS_ENEMY", "board_enemy");
        this.toolbar.addButton("OPTIONS_AUDIO", "board_audio");
        this.toolbar.addDivider();
        this.toolbar.addLabel("VIEW");
        this.toolbar.addButton("VIEW_GRID", "board_grid", true);
        this.toolbar.addButton("VIEW_RULER", "board_ruler", true);
        this.toolbar.addDivider();
        this.toolbar.addLabel("PAINT");
        this.toolbar.addButton("PAINT_BRUSH", "board_brush");
        this.toolbar.addButton("PAINT_FILL", "board_fill", true);
        this.toolbar.addButton("PAINT_DELETE", "board_delete", true);
        this.toolbar.addButton("PAINT_CLONE", "board_clone", true);
        this.toolbar.addDivider();
        this.toolbar.addLabel("TILE");
        //this.toolbar.addButton("TILE_SET", "board_tile", true);
    }
    
    public void setOptionViewGrid(boolean value)
    {
        this.optionViewGrid = value;
    }
    
    public void setOptionViewRuler(boolean value)
    {
        this.optionViewRuler = value;
    }
    
    public void setPaint(ToolBoardPaint paint)
    {
        this.optionPaintType = paint;
    }
    
}