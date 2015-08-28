package states;

import app.Engine;
import board.BoardManager;
import config.ConfigFile;
import config.ConfigManager;
import framework.files.FileItem;
import gfx.Drawing;
import items.ItemFile;
import items.ItemManager;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import lava.LavaManager;
import projects.ProjectFile;
import projects.ProjectManager;
import styles.Scheme;
import tiles.TilesetManager;
import tools.Tool;
import tools.ToolBoard;
import tools.ToolItem;
import tools.ToolLava;
import tools.ToolMain;
import tools.ToolProject;
import tools.ToolTileset;
import tools.desktop.Desktop;
import tools.desktop.DesktopItem;
import tools.files.FileBrowser;
import tools.focus.Focus;
import tools.modal.Modal;
import tools.modal.ModalAbout;
import tools.modal.ModalConfig;
import tools.modal.ModalMessage;
import tools.taskbar.Taskbar;
import tools.taskbar.TaskbarMenu;

public class StateBuilder extends State
{
    // Builder
    private ProjectFile project;
    private Desktop desktop;
    private Modal desktopModal;
    private Taskbar taskbar;
    private TaskbarMenu taskMenu;
    
    // Managers
    public BoardManager managerBoard;
    public ItemManager managerItem;
    public LavaManager managerLava;
    public ProjectManager managerProject;
    public TilesetManager managerTileset;
    
    // Project Browser
    public boolean projectBrowserActive;
    public FileBrowser projectBrowserModal;
    
    // Settings
    private ConfigFile configFile;
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
    
    // Keyboard
    private Focus focusElement;
    
    public StateBuilder()
    {
        // Application Variables
        Engine.setAppVariable("BUILDER_PATH", "C:/Users/Jamie/Documents/My Workshop/Java/TKRPG/Builder/");
        Engine.setAppVariable("BUILDER_SCHEME", "DEFAULT");
        //Engine.setAppVariable("BUILDER_SCHEME", "MYSTIC");
        
        // TEMP
        //ProjectManager.createProject("Mushroom");
        this.managerProject = new ProjectManager(this, "Mushroom");
        
        // NOTE: we should load a text file to determine which project was last open
        this.project = null;
        this.desktop = new Desktop(this);
        this.desktopModal = null;
        this.taskbar = new Taskbar(this);
        this.taskMenu = new TaskbarMenu(this);
        
        // Managers
        //this.buildManager();
        
        // Project Browser
        this.projectBrowserActive = false;
        this.projectBrowserModal = null;
        
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
        
        // Focus Element
        this.focusElement = null;
        
        // TEST
        /*ItemFile testSword = this.managerItem.loadItem("iron_sword");
            System.out.println("LOAD ITEM");
            System.out.println(testSword);
            System.out.println("");*/
        
        // TEMP
        this.desktop.addItem(new DesktopItem("TEST1", "TEST1", Drawing.getImage("icon/tool_board.png")));
        this.desktop.addItem(new DesktopItem("TEST2", "TEST2", Drawing.getImage("icon/tool_board.png")));
        this.desktop.addItem(new DesktopItem("TEST3", "TEST3", Drawing.getImage("icon/tool_board.png")));
        this.desktop.addItem(new DesktopItem("TEST4", "TEST4", Drawing.getImage("icon/tool_board.png")));
        this.desktop.addItem(new DesktopItem("TEST5", "TEST5", Drawing.getImage("icon/tool_board.png")));
        this.desktop.addItem(new DesktopItem("TEST6", "TEST6", Drawing.getImage("icon/tool_board.png")));
        this.desktop.addItem(new DesktopItem("TEST7", "TEST7", Drawing.getImage("icon/tool_board.png")));
        this.desktop.addItem(new DesktopItem("TEST8", "TEST8", Drawing.getImage("icon/tool_board.png")));
        this.desktop.addItem(new DesktopItem("TEST9", "TEST9", Drawing.getImage("icon/tool_board.png")));
        this.desktop.addItem(new DesktopItem("TEST10", "TEST10", Drawing.getImage("icon/tool_board.png")));
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
    
    private void buildManager()
    {
        this.managerBoard = new BoardManager(this, this.project.getFileName());
        this.managerItem = new ItemManager(this, this.project.getFileName());
        this.managerLava = new LavaManager(this, this.project.getFileName());
        this.managerTileset = new TilesetManager(this, this.project.getFileName());
    }
    
    public Focus getFocus()
    {
        return this.focusElement;
    }
    
    public ProjectFile getProject()
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
        System.out.println("STATE BUILDER -> INPUT KEY PRESS (" + key + ")");
    }

    public void inputKeyRelease(String key)
    {
        System.out.println("STATE BUILDER -> INPUT KEY RELEASE (" + key + ")");
    }

    public void inputKeyType(String key)
    {
        System.out.println("STATE BUILDER -> INPUT KEY TYPE (" + key + ")");
        // NOTE: shortcuts? other things? combintions of keys?
        
        // TEST
        if(this.focusElement != null) {this.focusElement.inputKey(key);}
        
        // Tool Window
        if(this.toolActive) {getToolFocus().inputKey(key);}
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
            
            // Project Browser
            if(this.projectBrowserActive)
            {
                if(this.projectBrowserModal.getArea().contains(e.getPoint()))
                {
                    FileItem file = this.projectBrowserModal.inputClickFile(e);
                    if(file != null)
                    {
                        this.setProject(this.managerProject.getProject(file));
                        this.setProjectBrowser(null);
                    }
                }
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
            else
            {
                if(this.desktop.getArea().contains(e.getPoint()))
                {
                    String ref = this.desktop.inputClick(e);
                    if(ref != null)
                    {
                        // NOTE: do something
                    }
                }
            }
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
        if(this.projectBrowserActive) {this.projectBrowserModal.render(g);}
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
            Drawing.fadeScreen(g, Scheme.Colour("STANDARD_FADE"));
            this.configModal.render(g);
        }
        
        // About
        if(this.aboutActive)
        {
            Drawing.fadeScreen(g, Scheme.Colour("STANDARD_FADE"));
            this.aboutModal.render(g);
        }
    }
    
    public void setFocus(Focus element)
    {
        this.focusElement = element;
        this.focusElement.focus(true);
    }
    
    public void setModal()
    {
        this.desktopModal = null;
    }
    
    public void setModal(Modal modal)
    {
        this.desktopModal = modal;
    }
    
    public void setProject(ProjectFile project)
    {
        this.project = project;
        this.buildManager();
        this.taskMenu.build();
    }
    
    public void setProjectBrowser(FileBrowser browser)
    {
        if(browser != null)
        {
            this.projectBrowserActive = true;
            this.projectBrowserModal = browser;
        }
        else
        {
            this.projectBrowserActive = false;
            this.projectBrowserModal = null;
        }
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
    
    public void toolActionItem()
    {
        // Switch to Item Tool
        if(this.getToolOpen("ITM")) {this.toolFocus("ITM");}

        // Open Item Tool
        else {this.toolInit(new ToolItem(this));}
    }
    
    public void toolActionLava()
    {
        // Switch to Lava Tool
        if(this.getToolOpen("LVA")) {this.toolFocus("LVA");}

        // Open Lava Tool
        else {this.toolInit(new ToolLava(this));}
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
    
    private void zTest()
    {
        //
    }
    
}