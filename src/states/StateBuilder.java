package states;

import app.Engine;
import config.Config;
import config.ConfigManager;
import gfx.Drawing;
import items.Item;
import items.ItemManager;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import projects.Project;
import projects.ProjectManager;
import tools.Tool;
import tools.ToolBoard;
import tools.ToolMain;
import tools.ToolProject;
import tools.ToolTileset;
import tools.desktop.Desktop;
import tools.modal.Modal;
import tools.modal.ModalAbout;
import tools.modal.ModalConfig;
import tools.modal.ModalMessage;
import tools.taskbar.Taskbar;
import tools.taskbar.TaskbarMenu;

public class StateBuilder extends State
{
    private Project project;
    private Desktop desktop;
    private Modal desktopModal;
    private Taskbar taskbar;
    private TaskbarMenu taskMenu;
    
    // Settings
    private Config configFile;
    private boolean configActive;
    private ModalConfig configModal;
    
    // About
    private boolean aboutActive;
    private ModalMessage aboutModal;
    
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
        //ProjectManager.createProject("Mushroom");
        
        // NOTE: we should load a text file to determine which project was last open
        this.project = null;
        this.desktop = new Desktop(this);
        this.desktopModal = null;
        this.taskbar = new Taskbar(this);
        this.taskMenu = new TaskbarMenu(this);
        
        // Settings
        this.configFile = ConfigManager.init();
        this.configActive = false;
        this.configModal = new ModalConfig(this);
        
        // About
        this.aboutActive = false;
        this.aboutModal = new ModalAbout(this);
        
        // TEMP
        this.toolMain = new ToolMain(this);
        
        // Tool Windows
        this.toolActive = false;
        this.toolArray = new ArrayList();
        this.toolFocus = 0;
        
        // TEST
        ItemManager itemManager = new ItemManager("Mushroom");
        /*Item testSword = itemManager.loadItem("iron_sword");
        System.out.println("LOAD ITEM");
        System.out.println(testSword);
        System.out.println("");*/
    }
    
    public void actionAbout(boolean active)
    {
        this.aboutActive = active;
    }
    
    public void actionConfig(boolean active)
    {
        this.configActive = active;
    }
    
    public void actionExit()
    {
        Engine.setState(new StateExit());
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
    
    public boolean getToolOpen(String ref)
    {
        for(int x = 0; x < this.toolArray.size(); x++)
        {
            if(this.toolArray.get(x).getToolRef().equals(ref)) {return true;}
        }
        return false;
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
        //this.toolInit(new ToolBoard(this));
        //this.toolInit(new ToolItem(this));
        //this.toolInit(new ToolCharacter(this));
        //this.toolInit(new ToolTileset(this));
        this.toolInit(new ToolProject(this));
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
            if(this.taskMenu.isActive())
            {
                if(this.taskMenu.getArea().contains(e.getPoint()))
                {
                    this.taskMenu.inputClickMenu(e);
                    return;
                }
                else {this.taskMenu.setActive(false);}
            }
            
            // Settings
            if(this.configActive)
            {
                if(this.configModal.getArea().contains(e.getPoint())) {this.configModal.inputClick(e);}
                return;
            }
            
            // About
            if(this.aboutActive)
            {
                if(this.aboutModal.getArea().contains(e.getPoint())) {this.aboutModal.inputClick(e);}
                return;
            }
            
            // Taskbar
            if(this.taskbar.getTaskbarRect().contains(e.getPoint()))
            {
                this.taskbar.inputClick(e);
                return;
            }
            
            // Tool Window
            if(this.toolActive) {getToolFocus().inputClick(e);}
            
            // Desktop
            else {if(this.desktop.getArea().contains(e.getPoint())) {this.desktop.inputClick(e);}}
        }
    }

    public void inputMouseClickR(MouseEvent e)
    {
        // Taskbar
        if(this.taskbar.getTaskbarRect().contains(e.getPoint())) {this.taskbar.inputContext(e);}
    }

    public void inputMouseMove(MouseEvent e)
    {
        //
    }
    
    public void projectUpdate()
    {
        // NOTE: need to call this EVERY TIME something is saved within the project
        this.getProject().setUpdated();
        this.getProject().save();
    }
    
    public void render(Graphics g)
    {
        // NOTE: do we need to render the desktop if there is a (fullscreen) tool above it?
        this.renderDesktop(g);
        
        this.taskbar.render(g);
        //this.toolMain.render(g);
        if(this.toolActive) {this.getToolFocus().render(g);}
        if(this.taskMenu.isActive()) {this.taskMenu.render(g);}
        if(this.desktopModal != null) {this.desktopModal.render(g);}
    }
    
    private void renderDesktop(Graphics g)
    {
        // Desktop
        this.desktop.render(g);
        
        // Settings
        if(this.configActive)
        {
            Drawing.fadeScreen(g, "FADE_GREEN");
            this.configModal.render(g);
        }
        
        // About
        if(this.aboutActive)
        {
            Drawing.fadeScreen(g, "FADE_GREEN");
            this.aboutModal.render(g);
        }
    }
    
    public void setModal()
    {
        this.desktopModal = null;
    }
    
    public void setModal(Modal modal)
    {
        this.desktopModal = modal;
    }
    
    public void setProject(Project project)
    {
        this.project = project;
        this.taskMenu.build();
    }

    public void tick()
    {
        //
    }
    
    public void toolActionBoard()
    {
        // Switch to Board Tool
        if(this.getToolOpen("BRD")) {this.toolFocus("BRD");}

        // Open Board Tool
        else {this.toolInit(new ToolBoard(this));}
    }
    
    public void toolActionProject()
    {
        // Switch to Project Tool
        if(this.getToolOpen("PRO")) {this.toolFocus("PRO");}

        // Open Project Tool
        else {this.toolInit(new ToolProject(this));}
    }
    
    public void toolClose(int id)
    {
        this.toolArray.remove(id);
        if(this.toolArray.isEmpty()) {this.toolActive = false;}
        else {this.toolFocus = this.getToolNext();}
    }
    
    public void toolFocus(int id)
    {
        this.toolFocus = id;
    }
    
    public void toolFocus(String ref)
    {
        for(int x = 0; x < this.toolArray.size(); x++)
        {
            if(this.toolArray.get(x).getToolRef().equals(ref)) {this.toolFocus(x);}
        }
    }
    
    public void toolInit(Tool tool)
    {
        this.toolActive = true;
        this.toolArray.add(tool);
        this.toolFocus = tool.getToolID();
        this.taskbar.addItem(tool.getTaskbarItem());
    }
    
}