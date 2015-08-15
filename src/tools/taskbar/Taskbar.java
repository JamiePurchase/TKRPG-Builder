package tools.taskbar;

import app.Engine;
import gfx.Drawing;
import gfx.Text;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import projects.Project;
import states.StateBuilder;
import tools.modal.ModalProjectOpen;

public class Taskbar
{
    private StateBuilder state;
    private final Rectangle taskbarArea;
    private ArrayList<TaskbarItem> taskbarItems;
    
    public Taskbar(StateBuilder state)
    {
        this.state = state;
        this.taskbarArea = new Rectangle(0, 718, 1366, 50);
        this.taskbarItems = new ArrayList();
        
        // TEMP
        //this.taskbarItems.add(new TaskbarItem("HELLO", null, null));
    }
    
    public void addItem(TaskbarItem item)
    {
        this.taskbarItems.add(item);
    }
    
    private String getClockDate()
    {
        return new SimpleDateFormat("EEE dd MMM yyyy").format(Calendar.getInstance().getTime());
    }
    
    private String getClockTime()
    {
        return new SimpleDateFormat("HH:mm:ss aa").format(Calendar.getInstance().getTime());
    }
    
    private Rectangle getMenuRect()
    {
        return new Rectangle(0, 718, 150, 718);
    }
    
    private Rectangle getProjectRect()
    {
        return new Rectangle(982, 718, 200, 50);
    }
    
    public Rectangle getTaskbarRect()
    {
        return this.taskbarArea;
    }
    
    public void inputClick(MouseEvent e)
    {
        if(this.getMenuRect().contains(e.getPoint())) {this.state.getTaskMenu().inputClick();}
        if(this.getProjectRect().contains(e.getPoint())) {this.state.setModal(new ModalProjectOpen(this.state));}
    }
    
    public void render(Graphics g)
    {
        // Background
        Drawing.fillRect(g, this.getTaskbarRect(), "STANDARD_FORE");
        
        // Border
        g.setColor(Color.BLACK);
        g.drawLine(0, 718, 1366, 718);
        
        // Items
        this.renderItems(g);
        
        // Menu
        this.renderMenu(g);
        
        // Project
        this.renderProject(g);
        
        // Clock
        this.renderClock(g);
    }
    
    private void renderClock(Graphics g)
    {
        Text.write(g, this.getClockTime(), 1274, 738, "CENTER", "CLOCK_TIME", "BLACK");
        Text.write(g, this.getClockDate(), 1274, 758, "CENTER", "CLOCK_DATE", "BLACK");
        g.setColor(Color.BLACK);
        g.drawLine(1182, 718, 1182, 768);
    }
    
    private void renderItems(Graphics g)
    {
        for(int x = 0; x < this.taskbarItems.size(); x++)
        {
            this.taskbarItems.get(x).render(g, x);
        }
    }
    
    private void renderMenu(Graphics g)
    {
        // Fill
        String fill = "STANDARD_FORE";
        if(this.getMenuRect().contains(Engine.getMousePoint())) {fill = "SECONDARY_FORE";}
        Drawing.fillRect(g, this.getMenuRect(), fill);
        
        // Text
        Text.write(g, "MENU", 75, 754, "CENTER", "TASKBAR_MENU", "BLACK");
        
        // Border
        g.setColor(Color.BLACK);
        g.drawLine(0, 718, 150, 718);
        g.drawLine(150, 718, 150, 768);
    }
    
    private void renderProject(Graphics g)
    {
        // Fill
        String fill = "STANDARD_FORE";
        if(this.getProjectRect().contains(Engine.getMousePoint())) {fill = "SECONDARY_FORE";}
        Drawing.fillRect(g, this.getProjectRect(), fill);
        
        // Project Name
        Project project = this.state.getProject();
        String caption1 = "NO PROJECT";
        String caption2 = "CLICK TO OPEN";
        if(project != null)
        {
            caption1 = this.state.getProject().getName();
            caption2 = this.state.getProject().getInfo();
        }
        Text.write(g, caption1, 1082, 738, "CENTER", "CLOCK_TIME", "BLACK");
        Text.write(g, caption2, 1082, 758, "CENTER", "CLOCK_DATE", "BLACK");
        
        // Border
        g.setColor(Color.BLACK);
        g.drawLine(982, 718, 1182, 718);
        g.drawLine(982, 718, 982, 768);
        g.drawLine(1182, 718, 1182, 768);
    }
    
}