package tools;

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
import tools.modal.Modal;
import tools.toolbar.Toolbar;
import tools.toolbar.ToolbarItem;
import tools.ui.Textbox;

public class ToolItem extends Tool
{
    // Toolbar
    private Toolbar toolbar;
    
    // Item File
    private boolean itemFileActive, itemFileSaved;
    private ItemFile itemFileObject;
    
    // Inputs
    private Textbox inputName;
    
    // Modal
    private boolean modalActive;
    private FileBrowser modalObject;
    
    public ToolItem(StateBuilder state)
    {
        super(state, "ITM", state.getToolNext(), "ITEM EDITOR", Drawing.getImage("icon/tool_items.png"));
        
        // Toolbar
        this.toolbarInit();
        
        // Item File
        this.fileClose();
        
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
        this.itemFileActive = false;
        this.itemFileObject = null;
        this.itemFileSaved = true;
        this.updateTitle();
    }
    
    public void fileNew()
    {
        // NOTE: consider how this works
        this.itemFileActive = true;
        this.itemFileObject = null;
        this.itemFileSaved = false;
        this.updateTitle();
    }
    
    public void fileOpen(ItemFile file)
    {
        this.itemFileActive = true;
        this.itemFileObject = file;
        this.itemFileSaved = true;
        this.updateTitle();
        
        // Inputs
        this.inputName.setValue(this.itemFileObject.getName());
    }
    
    public void inputClick(MouseEvent e)
    {
        // Modal Active
        if(this.modalActive)
        {
            FileItem file = this.modalObject.inputClickFile(e);
            if(file != null)
            {
                this.modal();
                this.fileOpen(this.getState().managerItem.loadItem(file.getPath()));
            }
            return;
        }
        
        // Close Button ?? done in superclass?
        
        // Toolbar (should check if toolbar area contains e point)
        ToolbarItem click = this.toolbar.inputClick(e);
        if(click != null) {this.toolbarEvent(click);}
    }
    
    public void inputKey(String key)
    {
        System.out.println("TOOL ITEM -> INPUT KEY (" + key + ")");
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
        if(this.itemFileActive) {this.renderContent(g);}
        if(this.modalActive) {this.modalObject.render(g);}
    }
    
    private void renderContent(Graphics g)
    {
        // NOTE: we need to have a single form group that holds multiple ui elements and can render and tick them all
        
        // Item Name
        Text.write(g, "Name", 75, 125, "LEFT", "MESSAGE", "BLACK");
        this.inputName.render(g);
        
        // Project Info
        //Text.write(g, this.itemFileObject.getInfo(), 75, 175, "LEFT", "MESSAGE", "BLACK");
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
            // TEMP
            //this.fileOpen(this.getState().managerItem.loadItem("iron_sword"));
            
            // TEMP
            //ArrayList<FileItem> fileItems =
            this.modal(new FileBrowser("OPEN ITEM", this.getState().managerItem.getItemArray()));
        }
        if(item.getRef().equals("FILE_SAVE"))
        {
            this.itemFileObject.save();
            this.itemFileSaved = true;
        }
    }
    
    private void toolbarInit()
    {
        this.toolbar = new Toolbar(new Rectangle(0, 34, 1366, 42));
        this.toolbar.addLabel("ITEM");
        this.toolbar.addButton("FILE_NEW", "file_new");
        this.toolbar.addButton("FILE_OPEN", "file_open");
        this.toolbar.addButton("FILE_SAVE", "file_save");
        this.toolbar.addButton("FILE_SAVE_AS", "file_saveAs");
    }
    
    private void updateTitle()
    {
        String title = "ITEM EDITOR";
        if(this.itemFileActive) {title += " - " + this.itemFileObject.getName();}
        if(!this.itemFileSaved) {title += " *";}
        this.setTitle(title);
    }
    
}