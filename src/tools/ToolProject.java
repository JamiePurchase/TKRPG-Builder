package tools;

import gfx.Drawing;
import gfx.Text;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import projects.Project;
import states.StateBuilder;
import tiles.Tileset;
import tools.modal.Modal;
import tools.toolbar.Toolbar;
import tools.toolbar.ToolbarItem;

public class ToolProject extends Tool
{
    // Toolbar
    private Toolbar toolbar;
    
    // Modal
    private boolean modalActive;
    private Modal modalObject;
    
    public ToolProject(StateBuilder state)
    {
        super(state, "PRO", state.getToolNext(), "PROJECT SETTINGS", Drawing.getImage("icon/tool_board.png"));
        
        // Toolbar
        toolbarInit();
        
        // Modal
        this.modalActive = false;
        this.modalObject = null;
    }
    
    private void fileSave()
    {
        this.getProject().save();
    }
    
    private Project getProject()
    {
        return this.getState().getProject();
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
    }
    
    public void inputKey(String key)
    {
        //
    }
    
    public void render(Graphics g)
    {
        this.renderWindow(g, "STANDARD_BACK");
        this.toolbar.render(g);
        this.renderContent(g);
    }
    
    private void renderContent(Graphics g)
    {
        // Project Name
        Text.write(g, this.getProject().getName(), 75, 125, "LEFT", "MESSAGE", "BLACK");
        
        // Project Info
        Text.write(g, this.getProject().getInfo(), 75, 175, "LEFT", "MESSAGE", "BLACK");
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
        this.toolbar.addLabel("PROJECT");
        this.toolbar.addButton("FILE_NEW", "file_new");
        this.toolbar.addButton("FILE_OPEN", "file_open");
        this.toolbar.addButton("FILE_SAVE", "file_save");
        this.toolbar.addButton("FILE_SAVE_AS", "file_saveAs");
        this.toolbar.addDivider();
    }
    
}