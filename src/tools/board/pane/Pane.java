package tools.board.pane;

import gfx.Drawing;
import gfx.Text;
import java.awt.Graphics;
import java.awt.Rectangle;
import styles.Scheme;
import tools.ToolBoard;

public abstract class Pane
{
    private ToolBoard paneBoard;
    private Rectangle paneArea;
    
    public Pane(ToolBoard tool, Rectangle area)
    {
        this.paneBoard = tool;
        this.paneArea = area;
    }
    
    public Rectangle getArea()
    {
        return this.paneArea;
    }
    
    public ToolBoard getBoard()
    {
        return this.paneBoard;
    }
    
    public void render(Graphics g)
    {
        // Background
        Drawing.fillRect(g, this.paneArea, Scheme.Colour("STANDARD_BACK"));
        
        // Title
        Text.write(g, "TOOL PANEL", this.paneArea.x + (this.paneArea.width / 2), this.paneArea.y + 22, "CENTER", "TOOLBAR", "BLACK");
        
        // Content
        this.renderContent(g);
        
        // Border
        Drawing.drawRect(g, this.paneArea, "BLACK");
    }
    
    public abstract void renderContent(Graphics g);
    
}