package tools.desktop;

import gfx.Drawing;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import projects.ProjectFile;
import states.StateBuilder;
import styles.Scheme;
import tools.files.tree.FileTree;

public class Desktop
{
    private StateBuilder state;
    private final Rectangle desktopArea = new Rectangle(0, 0, 1366, 718);
    private ArrayList<DesktopItem> desktopItems;
    private boolean desktopTreeActive;
    private FileTree desktopTreeModal;
    
    public Desktop(StateBuilder state)
    {
        // Desktop
        this.state = state;
        this.desktopItems = new ArrayList();
        
        // File Tree
        this.desktopTreeActive = false;
        this.desktopTreeModal = null;
    }
    
    public void addItem(DesktopItem item)
    {
        this.desktopItems.add(item);
    }
    
    public Rectangle getArea()
    {
        return this.desktopArea;
    }
    
    public String inputClick(MouseEvent e)
    {
        if(this.desktopTreeActive)
        {
            if(this.desktopTreeModal.getArea().contains(e.getPoint()))
            {
                this.desktopTreeModal.inputClick(e);
                return null;
            }
        }
        return this.inputClickItems(e);
    }
    
    private String inputClickItems(MouseEvent e)
    {
        for(int x = 0; x < this.desktopItems.size(); x++)
        {
            if(this.desktopItems.get(x).getRect(x).contains(e.getPoint()))
            {
                return desktopItems.get(x).getRef();
            }
        }
        return null;
    }
    
    public void render(Graphics g)
    {
        this.renderBackground(g);
        this.renderItems(g);
        if(this.desktopTreeActive) {this.desktopTreeModal.render(g);}
    }
    
    private void renderBackground(Graphics g)
    {
        Drawing.fillRect(g, this.desktopArea, Scheme.Colour("DESKTOP_FILL"));
    }
    
    private void renderItems(Graphics g)
    {
        for(int x = 0; x < this.desktopItems.size(); x++)
        {
            this.desktopItems.get(x).render(g, x);
        }
    }
    
    public void setFileTree(ProjectFile project)
    {
        this.desktopTreeActive = true;
        this.desktopTreeModal = new FileTree();
        
        // TEMP
        this.desktopTreeModal.addNode("BRD", "BOARDS");
        //this.desktopTreeModal.getNode("BRD").addNodeArray(this.state.managerBoard.getBoardArray());
        this.desktopTreeModal.addNode("ITM", "ITEMS");
        this.desktopTreeModal.addNode("???", "PLAYLISTS");
        // NOTE: change the reference of the playlist node (file extension?)
    }
    
    public void tick()
    {
        
    }
    
}