package tools;

import board.Board;
import board.BoardManager;
import board.Terrain;
import gfx.Drawing;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import states.StateBuilder;
import tiles.Tileset;
import tools.modal.Modal;
import tools.modal.ModalBoardNew;
import tools.toolbar.Toolbar;
import tools.toolbar.ToolbarItem;

public class ToolBoard extends Tool
{
    // Toolbar
    private Toolbar toolbar;
    
    // Board File
    private boolean boardFileActive, boardFileSaved;
    private Board boardFileObject;
    
    // Board Display
    private int boardOffsetX, boardOffsetY;
    
    // Modal
    private boolean modalActive;
    private Modal modalObject;
    
    // Options
    private ToolBoardMode boardMode;
    private ToolBoardPaint optionPaintType;
    //private tile optionPaintTile;
    private boolean optionViewGrid, optionViewRuler;
    
    // TEMP
    private int tempTile;
    
    public ToolBoard(StateBuilder state)
    {
        super(state, "BRD", state.getToolNext(), "BOARD EDITOR", Drawing.getImage("icon/tool_board.png"));
        
        // Board File
        this.boardFileActive = false;
        this.boardFileObject = null;
        this.boardFileSaved = true;
        
        // Board Offset
        this.boardOffsetX = 0;
        this.boardOffsetY = 0;
        
        // Modal
        this.modalActive = false;
        this.modalObject = null;
        
        // Mode
        this.boardMode = ToolBoardMode.TERRAIN;
        this.optionPaintType = ToolBoardPaint.BRUSH;
        //this.optionPaintTile
        
        // Toolbar
        this.toolbarInit();
        
        // TEMP
        //Board testBoard = new Board("Mushroom", "TEST", 5, 5);
        //testBoard.setTerrainAll(new Terrain("TEST", 0, 0));
        //testBoard.setTerrainAt(1, 0, new Terrain("TEST", 1, 0));
        //this.boardFileObject = testBoard;
        tempTile = 0;
        
        // TEMP
        /*Board tempBoard = BoardManager.loadBoard("Mushroom", "TEST");
        System.out.println("TEST BOARD");
        System.out.println("Name: " + tempBoard.getName());
        System.out.println("Size: " + tempBoard.getSizeX() + " x " + tempBoard.getSizeY());
        System.out.println("");*/
    }
    
    private Rectangle getAreaBoard()
    {
        if(this.optionViewRuler)
        {
            return new Rectangle(this.getToolRectContent().x + 32, this.getToolRectContent().y + 32, this.getToolRectContent().width - 64, this.getToolRectContent().height - 64);
        }
        else {return new Rectangle(this.getToolRectContent().x, this.getToolRectContent().y, this.getToolRectContent().width - 32, this.getToolRectContent().height - 32);}
    }
    
    private Rectangle getAreaRuler(boolean horizontal)
    {
        if(horizontal) {return new Rectangle(this.getToolRectContent().x, getToolRectContent().y, getToolRectContent().width, 32);}
        return new Rectangle(this.getToolRectContent().x, getToolRectContent().y, 32, getToolRectContent().height);
    }
    
    private Rectangle getAreaScroll(boolean horizontal)
    {
        if(horizontal) {return new Rectangle(this.getToolRectContent().x, getToolRectContent().y + getToolRectContent().height - 32, getToolRectContent().width, 32);}
        return new Rectangle(this.getToolRectContent().x + this.getToolRectContent().width - 32, getToolRectContent().y, 32, getToolRectContent().height);
    }
    
    private Rectangle getAreaTools()
    {
        return new Rectangle();
    }
    
    public void inputClick(MouseEvent e)
    {
        // Modal Active
        if(this.modalActive)
        {
            this.modalObject.inputClick(e);
            return;
        }
        
        // Close Button ?? done in superclass?
        
        // Toolbar (should check if toolbar area contains e point)
        ToolbarItem click = this.toolbar.inputClick(e);
        if(click != null) {this.toolbarEvent(click);}
        
        // Scrollbar
        if(this.getAreaScroll(true).contains(e.getPoint()))
        {
            this.inputClickScrollH(e);
            return;
        }
        if(this.getAreaScroll(false).contains(e.getPoint()))
        {
            this.inputClickScrollV(e);
            return;
        }
        
        // Board
        if(this.getAreaBoard().contains(e.getPoint())) {this.inputClickBoard(e);}
    }
    
    private void inputClickBoard(MouseEvent e)
    {
        // Coordinates on Board Area
        int clickX = e.getX() - this.getAreaBoard().x;
        int clickY = e.getY() - this.getAreaBoard().y;
        
        // Calculate Tile X
        int tileX = 0;
        while(clickX > 32)
        {
            tileX ++;
            clickX -= 32;
        }
        
        // Calculate Tile Y
        int tileY = 0;
        while(clickY > 32)
        {
            tileY ++;
            clickY -= 32;
        }
        
        // Click on Board Tile
        this.inputClickBoardTile(tileX, tileY);
    }
    
    private void inputClickBoardTile(int tileX, int tileY)
    {
        // Terrain Mode
        if(this.boardMode == ToolBoardMode.TERRAIN)
        {
            // Paint
            if(this.optionPaintType == ToolBoardPaint.BRUSH)
            {
                // Set board tile at position to current terrain
                
                // TEMP
                this.tempTile = 1;
            }
        }
    }
    
    private void inputClickScrollH(MouseEvent e)
    {
        // NOTE: may wish to combine these functions and require an h/v argument
    }
    
    private void inputClickScrollV(MouseEvent e)
    {
        //
    }
    
    public void inputKey(String key)
    {
        //
    }
    
    public void render(Graphics g)
    {
        this.renderWindow(g);
        this.toolbar.render(g);
        this.renderContent(g);
        if(this.modalActive) {this.renderModal(g);}
    }
    
    private void renderContent(Graphics g)
    {
        // Background
        Drawing.fillRect(g, this.getAreaBoard(), "WHITE");
        
        // Ruler
        if(this.optionViewRuler) {this.renderContentRuler(g);}
        
        // Scrollbars
        this.renderContentScroll(g);
        
        // Board
        this.renderContentBoard(g);
    }
    
    private void renderContentBoard(Graphics g)
    {
        //this.boardFileObject.render(g, this.getAreaBoard(), this.boardOffsetX, this.boardOffsetY);
        
        // TEMP
        Tileset tileset = new Tileset("Mushroom", "TEST", "TEST", 32);
        Drawing.drawImage(g, tileset.getTileAt(0, 0), this.getAreaBoard().x, this.getAreaBoard().y);
        Drawing.drawImage(g, tileset.getTileAt(this.tempTile, 0), this.getAreaBoard().x + 32, this.getAreaBoard().y);
        
        // Border
        if(this.optionViewRuler) {Drawing.drawRect(g, this.getAreaBoard(), "BLACK");}
    }
    
    private void renderContentRuler(Graphics g)
    {
        Drawing.fillRect(g, this.getAreaRuler(true), "STANDARD_BACK");
        Drawing.fillRect(g, this.getAreaRuler(false), "STANDARD_BACK");
        Drawing.drawRect(g, this.getAreaRuler(true), "BLACK", false, true, true, false);
        Drawing.drawRect(g, this.getAreaRuler(false), "BLACK", false, true, true, false);
    }
    
    private void renderContentScroll(Graphics g)
    {
        Drawing.fillRect(g, this.getAreaScroll(true), "STANDARD_BACK");
        Drawing.fillRect(g, this.getAreaScroll(false), "STANDARD_BACK");
        Drawing.drawRect(g, this.getAreaScroll(true), "BLACK", true, true, true, false);
        Drawing.drawRect(g, this.getAreaScroll(false), "BLACK", false, true, true, true);
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