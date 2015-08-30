package tools;

import gfx.Drawing;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import states.StateBuilder;
import styles.Scheme;
import tiles.TilesetFile;
import tools.modal.Modal;
import tools.toolbar.Toolbar;
import tools.toolbar.ToolbarItem;

public class ToolTileset extends Tool
{
    // File
    private boolean tilesetActive, tilesetSaved;
    private TilesetFile tilesetObject;
    
    // Toolbar
    private Toolbar toolbar;
    
    // Modal
    private boolean modalActive;
    private Modal modalObject;
    
    public ToolTileset(StateBuilder state)
    {
        super(state, "TST", state.getToolNext(), "TILESET EDITOR", Drawing.getImage("icon/tool_board.png"));
        
        // File
        this.tilesetSaved = true;
        this.fileClose();
        
        // Toolbar
        toolbarInit();
        
        // Modal
        this.modalActive = false;
        this.modalObject = null;
    }
    
    public ToolTileset(StateBuilder state, TilesetFile tileset)
    {
        super(state, "TST", state.getToolNext(), "TILESET EDITOR", Drawing.getImage("icon/tool_board.png"));
        
        // File
        this.tilesetSaved = true;
        this.fileOpen(tileset);
        
        // Toolbar
        toolbarInit();
        
        // Modal
        this.modalActive = false;
        this.modalObject = null;
    }
    
    private void fileClose()
    {
        this.tilesetActive = false;
        this.tilesetObject = null;
    }
    
    private void fileOpen(TilesetFile tileset)
    {
        this.tilesetActive = true;
        this.tilesetObject = tileset;
    }
    
    private void fileSave()
    {
        // NOTE: consider when a new tileset has been made but not saved yet
        this.tilesetObject.save();
        this.tilesetSaved = true;
    }
    
    public void inputClick(MouseEvent e)
    {
        // Modal Active
        if(this.modalActive)
        {
            this.modalObject.inputClick(e);
            return;
        }
        
        // Close Button
        if(this.getCloseRect().contains(e.getPoint())) {this.inputClickClose();}
        
        // Toolbar (should check if toolbar area contains e point)
        ToolbarItem click = this.toolbar.inputClick(e);
        if(click != null) {this.toolbarEvent(click);}
    }
    
    public void inputKey(String key)
    {
        //
    }
    
    public void render(Graphics g)
    {
        this.renderWindow(g);
        this.toolbar.render(g);
        if(this.modalActive) {this.renderModal(g);}
    }
    
    private void renderModal(Graphics g)
    {
        Drawing.fadeScreen(g, Scheme.Colour("STANDARD_FADE"));
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
            //
        }
    }
    
    private void toolbarInit()
    {
        this.toolbar = new Toolbar(new Rectangle(0, 34, 1366, 42));
        this.toolbar.addLabel("TILESET");
        this.toolbar.addButton("FILE_NEW", "file_new");
        this.toolbar.addButton("FILE_OPEN", "file_open");
        this.toolbar.addButton("FILE_SAVE", "file_save");
        this.toolbar.addButton("FILE_SAVE_AS", "file_saveAs");
        this.toolbar.addDivider();
    }
    
}