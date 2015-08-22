package tools;

import gfx.Colour;
import gfx.Drawing;
import gfx.Text;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import lava.LavaFile;
import states.StateBuilder;
import styles.Scheme;
import static styles.Scheme.Colour;
import tools.files.FileBrowser;
import framework.files.FileItem;
import tools.toolbar.Toolbar;
import tools.toolbar.ToolbarItem;

public class ToolLava extends Tool
{
    // Toolbar
    private Toolbar toolbar;
    
    // Item File
    private boolean lavaFileActive, lavaFileSaved;
    private LavaFile lavaFileObject;
    
    // Modal
    private boolean modalActive;
    private FileBrowser modalObject;
    
    public ToolLava(StateBuilder state)
    {
        super(state, "LVA", state.getToolNext(), "LAVA EDITOR", Drawing.getImage("icon/tool_lava.png"));
        
        // Toolbar
        this.toolbarInit();
        
        // Item File
        this.fileClose();
        
        // Modal
        this.modalActive = false;
        this.modalObject = null;
    }
    
    public void fileClose()
    {
        this.lavaFileActive = false;
        this.lavaFileObject = null;
        this.lavaFileSaved = true;
        this.updateTitle();
    }
    
    public void fileNew()
    {
        // NOTE: consider how this works
        this.lavaFileActive = true;
        this.lavaFileObject = null;
        this.lavaFileSaved = false;
        this.updateTitle();
    }
    
    public void fileOpen(LavaFile file)
    {
        this.lavaFileActive = true;
        this.lavaFileObject = file;
        this.lavaFileSaved = true;
        this.updateTitle();
    }
    
    private Rectangle getAreaContent()
    {
        return new Rectangle(this.getToolRectContent().x, this.getToolRectContent().y, this.getToolRectContent().width - 32, this.getToolRectContent().height - 64);
    }
    
    /*private Rectangle getAreaScroll()
    {
        //return new Rectangle(this.getToolRectContent().x, this.getToolRectContent().y, this.getToolRectContent().width - 32, this.getToolRectContent().height - 32);
    }*/
    
    private Rectangle getAreaStatus()
    {
        return new Rectangle(this.getToolRectContent().x, this.getToolRectContent().y + this.getToolRectContent().height - 32, this.getToolRectContent().width, 32);
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
                this.fileOpen(this.getState().managerLava.loadLava(file.getPath()));
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
        //
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
        this.renderStatus(g);
        if(this.lavaFileActive) {this.renderContent(g);}
        if(this.modalActive) {this.modalObject.render(g);}
    }
    
    private void renderContent(Graphics g)
    {
        // TEST
        Drawing.fillRect(g, this.getAreaContent(), Scheme.Colour("SECONDARY_FORE"));
    }
    
    private void renderStatus(Graphics g)
    {
        // Fill
        Drawing.fillRect(g, this.getAreaStatus(), Scheme.Colour("SECONDARY_FORE"));
        
        // Content
        if(this.lavaFileActive) {this.renderStatusFile(g);}
        else
        {
            Text.write(g, "LAVA EDITOR", this.getAreaStatus().x + 14, this.getAreaStatus().y + 22, "LEFT", "TOOLBAR", "BLACK");
        }
        
        // Border
        Drawing.drawLine(g, this.getAreaStatus().x, this.getAreaStatus().y, this.getAreaStatus().x + this.getAreaStatus().width, this.getAreaStatus().y, "BLACK");
    }
    
    private void renderStatusFile(Graphics g)
    {
        // File
        Text.write(g, "FILE", this.getAreaStatus().x + 14, this.getAreaStatus().y + 22, "LEFT", "TOOLBAR", "BLACK");
        Text.write(g, this.lavaFileObject.getName(), this.getAreaStatus().x + 70, this.getAreaStatus().y + 22, "LEFT", "TOOLBAR_BOLD", "BLACK");
        
        // Line
        Text.write(g, "LINE", this.getAreaStatus().x + 468, this.getAreaStatus().y + 22, "LEFT", "TOOLBAR", "BLACK");
        Text.write(g, "1", this.getAreaStatus().x + 568, this.getAreaStatus().y + 22, "RIGHT", "TOOLBAR_BOLD", "BLACK");
        //this.inputScript.getCursorPosX()
        Drawing.drawLine(g, this.getAreaStatus().x + 454, this.getAreaStatus().y, this.getAreaStatus().x + 444, this.getAreaStatus().y + this.getAreaStatus().height, null);
        
        // Column
        Text.write(g, "COLUMN", this.getAreaStatus().x + 624, this.getAreaStatus().y + 22, "LEFT", "TOOLBAR", "BLACK");
        //Text.write(g, "1", this.getAreaStatus().x + 700, this.getAreaStatus().y + 22, "RIGHT", "TOOLBAR_BOLD", "BLACK");
        Text.write(g, "1", this.getAreaStatus().x + 764, this.getAreaStatus().y + 22, "RIGHT", "TOOLBAR_BOLD", "BLACK");
        //this.inputScript.getCursorPosY()
        Drawing.drawLine(g, this.getAreaStatus().x + 610, this.getAreaStatus().y, this.getAreaStatus().x + 600, this.getAreaStatus().y + this.getAreaStatus().height, null);
        
        // Errors
        Text.write(g, "ERROR COUNT", this.getAreaStatus().x + 820, this.getAreaStatus().y + 22, "LEFT", "TOOLBAR", "BLACK");
        Text.write(g, "0", this.getAreaStatus().x + 1044, this.getAreaStatus().y + 22, "RIGHT", "TOOLBAR_BOLD", "BLACK");
        Drawing.drawLine(g, this.getAreaStatus().x + 806, this.getAreaStatus().y, this.getAreaStatus().x + 796, this.getAreaStatus().y + this.getAreaStatus().height, null);
        
        // Updated
        Text.write(g, "LAST UPDATED", this.getAreaStatus().x + 1100, this.getAreaStatus().y + 22, "LEFT", "TOOLBAR", "BLACK");
        Text.write(g, "20/08/2015", this.getAreaStatus().x + this.getAreaStatus().width - 15, this.getAreaStatus().y + 22, "RIGHT", "TOOLBAR_BOLD", "BLACK");
        Drawing.drawLine(g, this.getAreaStatus().x + 1086, this.getAreaStatus().y, this.getAreaStatus().x + 1076, this.getAreaStatus().y + this.getAreaStatus().height, null);
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
            this.modal(new FileBrowser("OPEN LAVA SCRIPT", this.getState().managerLava.getLavaArray()));
        }
        if(item.getRef().equals("FILE_SAVE"))
        {
            this.lavaFileObject.save();
            this.lavaFileSaved = true;
        }
    }
    
    private void toolbarInit()
    {
        this.toolbar = new Toolbar(new Rectangle(0, 34, 1366, 42));
        this.toolbar.addLabel("LAVA");
        this.toolbar.addButton("FILE_NEW", "file_new");
        this.toolbar.addButton("FILE_OPEN", "file_open");
        this.toolbar.addButton("FILE_SAVE", "file_save");
        this.toolbar.addButton("FILE_SAVE_AS", "file_saveAs");
    }
    
    private void updateTitle()
    {
        String title = "LAVA EDITOR";
        if(this.lavaFileActive) {title += " - " + this.lavaFileObject.getName();}
        if(!this.lavaFileSaved) {title += " *";}
        this.setTitle(title);
    }
    
}