package states;

import app.Engine;
import blocks.Window;
import gfx.Colour;
import gfx.Drawing;
import gfx.Fonts;
import gfx.Text;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import projects.Project;
import projects.ProjectManager;
import tools.Tool;
import tools.ToolBoard;
import tools.ToolMain;
import tools.desktop.Desktop;
import tools.modal.Modal;
import tools.modal.ModalProjectOpen;
import tools.taskbar.Taskbar;
import tools.taskbar.TaskbarMenu;

public class StateBuilder extends State
{
    private Project project;
    private Desktop desktop;
    private Modal desktopModal;
    private Taskbar taskbar;
    private TaskbarMenu taskMenu;
    
    // TEMP
    private ToolMain toolMain;
    
    // Tool Windows
    private boolean toolActive;
    private ArrayList<Tool> toolArray;
    private int toolFocus;
    
    public StateBuilder()
    {
        // Application Variables
        Engine.setAppVariable("BUILDER_PATH", "C:/Users/Jamie/Documents/My Workshop/Java/TKRPG/Builder/");
        
        // TEMP
        ProjectManager.createProject("Mushroom");
        
        // NOTE: we should load a text file to determine which project was last open
        this.project = null;
        this.desktop = new Desktop(this);
        this.desktopModal = null;
        this.taskbar = new Taskbar(this);
        this.taskMenu = new TaskbarMenu(this);
        
        // TEMP
        this.toolMain = new ToolMain(this);
        
        // Tool Windows
        this.toolActive = false;
        this.toolArray = new ArrayList();
        this.toolFocus = 0;
    }
    
    public void audioSound(String sound)
    {
        
    }
    
    public Project getProject()
    {
        return this.project;
    }
    
    public TaskbarMenu getTaskMenu()
    {
        return this.taskMenu;
    }
    
    private Tool getToolFocus()
    {
        if(this.toolActive) {return this.toolArray.get(this.toolFocus);}
        return null;
    }
    
    public int getToolNext()
    {
        if(this.toolArray == null) {return 0;}
        return this.toolArray.size();
    }

    public void inputKeyPress(String key)
    {
        //
    }

    public void inputKeyRelease(String key)
    {
        //
    }

    public void inputKeyType(String key)
    {
        this.toolMain.inputKey(key);
        
        // TEMP
        //this.setModal(new ModalProjectOpen(this));
        
        // TEMP
        this.toolInit(new ToolBoard(this));
    }

    public void inputMouseClickL(MouseEvent e)
    {
        if(this.desktopModal != null)
        {
            this.desktopModal.inputClick(e);
        }
        else
        {
            // NOTE: check taskmenu first
            if(this.desktop.getArea().contains(e.getPoint())) {this.desktop.inputClick(e);}
            if(this.taskbar.getTaskbarRect().contains(e.getPoint())) {this.taskbar.inputClick(e);}
            this.toolMain.inputClick(e);
        }
    }

    public void inputMouseClickR(MouseEvent e)
    {
        //
    }

    public void inputMouseMove(MouseEvent e)
    {
        //
    }
    
    public void render(Graphics g)
    {
        this.desktop.render(g);
        this.taskbar.render(g);
        //this.toolMain.render(g);
        if(this.toolActive) {this.getToolFocus().render(g);}
        if(this.taskMenu.isActive()) {this.taskMenu.render(g);}
        if(this.desktopModal != null) {this.desktopModal.render(g);}
    }
    
    public void setModal()
    {
        this.desktopModal = null;
    }
    
    public void setModal(Modal modal)
    {
        this.desktopModal = modal;
    }
    
    public void setProjectOpen(Project project)
    {
        this.project = project;
    }

    public void tick()
    {
        //
    }
    
    private void toolInit(Tool tool)
    {
        this.toolActive = true;
        this.toolArray.add(tool);
        this.toolFocus = tool.getToolID();
        this.taskbar.addItem(tool.getTaskbarItem());
    }
    
}