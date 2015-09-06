package tools.board.pane;

import board.zones.Zone;
import gfx.Text;
import java.awt.Graphics;
import java.awt.Rectangle;
import tools.ToolBoard;

public class PaneZone extends Pane
{
    //
    
    public PaneZone(ToolBoard tool, Rectangle area)
    {
        super(tool, area);
    }
    
    public Zone getZoneSelect()
    {
        return this.getBoard().toolZoneSelect();
    }
    
    public void renderContent(Graphics g)
    {
        if(this.getZoneSelect() != null) {this.renderContentSelect(g);}
        //
    }
    
    private void renderContentSelect(Graphics g)
    {
        Text.write(g, this.getZoneSelect().getRef(), this.getArea().x + 15, this.getArea().y + 100, "LEFT", "TOOLBAR", "BLACK");
        Text.write(g, this.getZoneSelect().getType(), this.getArea().x + 15, this.getArea().y + 150, "LEFT", "TOOLBAR", "BLACK");
    }
    
}