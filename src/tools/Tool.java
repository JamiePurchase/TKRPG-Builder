package tools;

import app.Engine;
import gfx.Colour;
import gfx.Drawing;
import gfx.Text;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import states.StateBuilder;
import tools.taskbar.TaskbarItem;

public abstract class Tool
{
    private StateBuilder state;
    private String toolRef;
    private int toolID;
    private String toolTitle;
    private Rectangle toolArea;
    private boolean toolFocus;
    private boolean toolSaved;
    private BufferedImage toolTaskIcon;
    
    public Tool(StateBuilder state, String ref, int id, String title, BufferedImage icon)
    {
        this.state = state;
        this.toolRef = ref;
        this.toolID = id;
        this.toolTitle = title;
        this.toolArea = new Rectangle(0, 0, 1366, 718);
        this.toolFocus = true;
        this.toolSaved = true;
        this.toolTaskIcon = icon;
    }
    
    public Tool(StateBuilder state, int id, String title, Rectangle area, BufferedImage icon)
    {
        this.state = state;
        this.toolID = id;
        this.toolTitle = title;
        this.toolArea = area;
        this.toolFocus = true;
        this.toolSaved = true;
        this.toolTaskIcon = icon;
    }
    
    private Rectangle getCloseRect()
    {
        return new Rectangle(this.toolArea.x + this.toolArea.width - 28, this.toolArea.y + 6, 22, 22);
    }
    
    public StateBuilder getState()
    {
        return this.state;
    }
    
    public TaskbarItem getTaskbarItem()
    {
        return new TaskbarItem(this.toolTitle, this, this.toolTaskIcon);
    }
    
    public int getToolID()
    {
        return this.toolID;
    }
    
    public Rectangle getToolRect()
    {
        return this.toolArea;
    }
    
    public Rectangle getToolRectContent()
    {
        return new Rectangle(this.toolArea.x + 6, this.toolArea.y + 77, this.toolArea.width - 11, this.toolArea.height - 82);
    }
    
    public String getToolRef()
    {
        return this.toolRef;
    }
    
    public abstract void inputClick(MouseEvent e);
    
    public void inputClickClose()
    {
        if(this.toolSaved)
        {
            // NOTE: come back to this
        }
    }
    
    public abstract void inputKey(String key);
    
    public abstract void render(Graphics g);
    
    public void renderBorder(Graphics g)
    {
        Drawing.drawRect(g, this.toolArea, 1, "BLACK");
    }
    
    public void renderWindow(Graphics g)
    {
        this.renderWindow(g, "BLACK");
    }
    
    public void renderWindow(Graphics g, String background)
    {
        // Tool Background
        Drawing.fillRect(g, this.toolArea, background);
        
        // Titlebar
        Drawing.fillRect(g, this.toolArea.x, this.toolArea.y, this.toolArea.width, 34, "STANDARD_FORE");
        g.drawImage(Drawing.getImage("icon/icon.png"), this.toolArea.x + 8, this.toolArea.y, null);
        Text.write(g, this.toolTitle, this.toolArea.x + 50, this.toolArea.y + 24, "LEFT", "TITLEBAR", "BLACK");
        
        // Titlebar Close
        this.renderWindowClose(g);
        
        // Tool Border
        Drawing.drawLine(g, this.toolArea.x + 5, this.toolArea.y + 34, this.toolArea.x + 5, this.toolArea.y + (this.toolArea.height - 5), "BLACK");
        Drawing.drawLine(g, this.toolArea.x + this.toolArea.width - 5, this.toolArea.y + 34, this.toolArea.x + this.toolArea.width - 5, this.toolArea.y + (this.toolArea.height - 5), "BLACK");
        Drawing.drawLine(g, this.toolArea.x + 5, this.toolArea.y + (this.toolArea.height - 5), this.toolArea.x + this.toolArea.width - 5, this.toolArea.y + (this.toolArea.height - 5), "BLACK");
        Drawing.drawRect(g, this.toolArea, 5, "STANDARD_FORE");
        this.renderBorder(g);
    }
    
    private void renderWindowClose(Graphics g)
    {
        String buttonFill = "TITLEBAR_CLOSE";
        if(this.getCloseRect().contains(Engine.getMousePoint())) {buttonFill = "TITLEBAR_CLOSE_HOVER";}
        Drawing.fillRect(g, this.getCloseRect(), buttonFill);
        Text.write(g, "X", this.getCloseRect().x + 11, this.getCloseRect().y + 20, "CENTER", "TITLEBAR", "BLACK");
        Drawing.drawRect(g, this.getCloseRect(), "BLACK");
    }
    
    public abstract void tick();
    
}