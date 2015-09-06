package tools;

import debug.Console;
import gfx.Drawing;
import gfx.Text;
import items.ItemFile;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import states.StateBuilder;
import styles.Scheme;
import tools.files.FileBrowser;
import framework.files.FileItem;
import tiles.TileFile;
import tools.modal.Modal;
import tools.toolbar.Toolbar;
import tools.toolbar.ToolbarItem;
import tools.ui.Textbox;

public class ToolTilePaint extends Tool
{
    // Toolbar
    private Toolbar toolbar;
    
    // Item File
    private boolean tileFileActive, tileFileSaved;
    private TileFile tileFileObject;
    
    // Inputs
    private Textbox inputName;
    
    // Modal
    private boolean modalActive;
    private FileBrowser modalObject;
    
    public ToolTilePaint(StateBuilder state)
    {
        super(state, "T32", state.getToolNext(), "TILE EDITOR", Drawing.getImage("icon/tool_items.png"));
        
        // Toolbar
        this.toolbarInit();
        
        // Item File
        this.fileClose();
        
        // TEMP
        this.tileFileActive = true;
        this.tileFileObject = new TileFile();
        
        // Inputs
        this.inputName = new Textbox("NAME", new Rectangle(175, 100, 400, 35), "", 20);
        
        // Modal
        this.modalActive = false;
        this.modalObject = null;
        
        // TEST
        this.getState().setFocus(this.inputName);
    }
    
    public void fileClose()
    {
        this.tileFileActive = false;
        this.tileFileObject = null;
        this.tileFileSaved = true;
        this.updateTitle();
    }
    
    public void fileNew()
    {
        // NOTE: consider how this works
        this.tileFileActive = true;
        this.tileFileObject = null;
        this.tileFileSaved = false;
        this.updateTitle();
    }
    
    public void fileOpen(TileFile file)
    {
        this.tileFileActive = true;
        this.tileFileObject = file;
        this.tileFileSaved = true;
        this.updateTitle();
        
        // Inputs
        //this.inputName.setValue(this.tileFileObject.getName());
    }
    
    public void inputClick(MouseEvent e)
    {
        // Modal Active
        /*if(this.modalActive)
        {
            FileItem file = this.modalObject.inputClickFile(e);
            if(file != null)
            {
                this.modal();
                this.fileOpen(this.getState().getManager().Item().loadItem(file.getPath()));
            }
            return;
        }*/
        
        // Close Button
        if(this.getCloseRect().contains(e.getPoint())) {this.inputClickClose();}
        
        // Toolbar (should check if toolbar area contains e point)
        ToolbarItem click = this.toolbar.inputClick(e);
        if(click != null) {this.toolbarEvent(click);}
    }
    
    public void inputKey(String key)
    {
        Console.print("TOOL ITEM -> INPUT KEY (" + key + ")");
        if(this.inputName.hasFocus()) {this.inputName.inputKey(key);}
    }
    
    public void modal()
    {
        this.modalActive = false;
        this.modalObject = null;
    }
    
    public void modal(FileBrowser modal)
    {
        this.modalActive = true;
        this.modalObject = modal;
    }
    
    public void render(Graphics g)
    {
        this.renderWindow(g, Scheme.Colour("STANDARD_BACK"));
        this.toolbar.render(g);
        if(this.tileFileActive) {this.renderContent(g);}
        if(this.modalActive) {this.modalObject.render(g);}
    }
    
    private void renderContent(Graphics g)
    {
        if(this.tileFileActive) {this.renderContentTile(g);}
    }
    
    private void renderContentTile(Graphics g)
    {
        for(int x = 0; x < 32; x++)
        {
            for(int y = 0; y < 32; y++)
            {
                Drawing.fillRect(g, new Rectangle((32 * x) + 50, (32 * y) + 50, 32, 32), this.tileFileObject.getPixel(x, y).getColor());
            }
        }
    }
    
    public void tick()
    {
        //
    }
    
    private void toolbarEvent(ToolbarItem item)
    {
        if(item.getRef().equals("FILE_NEW"))
        {
            // NOTE: open a modal that allows the user to enter a new name?
            this.fileNew();
        }
        if(item.getRef().equals("FILE_OPEN"))
        {
            //
        }
        if(item.getRef().equals("FILE_SAVE"))
        {
            //this.tileFileObject.save();
            //this.tileFileSaved = true;
        }
    }
    
    private void toolbarInit()
    {
        this.toolbar = new Toolbar(new Rectangle(0, 34, 1366, 42));
        this.toolbar.addLabel("TILE");
        this.toolbar.addButton("FILE_NEW", "file_new");
        this.toolbar.addButton("FILE_OPEN", "file_open");
        this.toolbar.addButton("FILE_SAVE", "file_save");
        this.toolbar.addButton("FILE_SAVE_AS", "file_saveAs");
    }
    
    private void updateTitle()
    {
        String title = "TILE EDITOR";
        //if(this.tileFileActive) {title += " - " + this.tileFileObject.getName();}
        if(!this.tileFileSaved) {title += " *";}
        this.setTitle(title);
    }
    
}