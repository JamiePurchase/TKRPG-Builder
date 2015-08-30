package states;

import app.Engine;
import config.ConfigFile;
import config.ConfigManager;
import debug.Console;
import framework.files.FileItem;
import framework.files.FileManager;
import gfx.Drawing;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import projects.ProjectFile;
import projects.ProjectManager;
import styles.Scheme;
import tools.Tool;
import tools.ToolBoard;
import tools.ToolItem;
import tools.ToolLava;
import tools.ToolMain;
import tools.ToolProject;
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
        
        // NOTE: we should load a text file to determine which project was last open
        this.project = null;
        this.desktop = new Desktop(this);
        this.desktopModal = null;
        this.taskbar = new Taskbar(this);
        this.taskMenu = new TaskbarMenu(this);
        
        // Managers
        this.initManager();
        
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
        
        // TEST
        this.zTest();
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
    
    public Focus getFocus()
    {
        return this.focusElement;
    }
    
    public FileManager getManager()
    {
        return (FileManager) Engine.getAppVariable("ENGINE_MANAGER");
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
        //return this.toolArray.size();
        
        int tool = this.toolFocus + 1;
        if(tool >= this.toolArray.size()) {tool = 0;}
        return tool;
    }
    
    public boolean getToolOpen(String ref)
    {
        for(int x = 0; x < this.toolArray.size(); x++)
        {
            if(this.toolArray.get(x).getToolRef().equals(ref)) {return true;}
        }
        return false;
    }
    
    private void initManager()
    {
        if(this.project != null) {Engine.setAppVariable("ENGINE_MANAGER", new FileManager(this.project.getFileName()));}
        else {Engine.setAppVariable("ENGINE_MANAGER", null);}
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
                        this.setProject(ProjectManager.getProject(file));
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
        this.getProject().update();
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
        this.initManager();
        this.taskMenu.build();
        
        // TEST
        this.desktop.setFileTree(project);
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
    
    public void toolClose()
    {
        this.toolClose(this.toolFocus);
    }
    
    public void toolClose(int id)
    {
        // Remove Taskbar Icon
        this.taskbar.removeItem(this.toolArray.get(id).getTaskbarRef());
        
        // Remove Tool Object
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
        // Create Taskbar Icon
        this.taskbar.addItem(tool.getTaskbarItem());
        
        // Create Tool Object
        this.toolActive = true;
        this.toolArray.add(tool);
        this.toolFocus = tool.getToolID();
    }
    
    @SuppressWarnings("ThrowableResultIgnored")
    private void zTest()
    {
        // Manager
        this.setProject(ProjectManager.getProject("Mushroom"));
        Engine.setAppVariable("ENGINE_MANAGER", new FileManager(this.project.getFileName()));
        
        // Folder Search
        Console.br();
        Console.tableLine();
        Console.tableBr();
        Console.tableWrite("Project: " + this.project.getFileName());
        Console.tableBr();
        
        // Board Files
        Console.tableLine();
        this.zTestPrint("Board Files", this.getManager().Board().getBoardArray());
        
        // Character Files
        Console.tableLine();
        this.zTestPrint("Character Files", this.getManager().Character().getCharacterArray());
        
        // Item Files
        Console.tableLine();
        this.zTestPrint("Item Files", this.getManager().Item().getItemArray());
        
        // Tileset Files
        Console.tableLine();
        this.zTestPrint("Tileset Files", this.getManager().Tileset().getTilesetArray());
        
        // Done
        Console.tableLine();
        Console.br();
    }
    
    private void zTestPrint(String title, ArrayList<FileItem> files)
    {
        Console.tableWrite(title, "(" + files.size() + ")");
        for(int x = 0; x < files.size(); x++)
        {
            Console.tableWrite(" • " + files.get(x).getName(), files.get(x).getDate().getDisplay());
        }
    }
    
}