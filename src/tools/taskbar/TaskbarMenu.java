package tools.taskbar;

import app.Engine;
import gfx.Colour;
import gfx.Drawing;
import gfx.Text;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import states.StateBuilder;
import states.StateExit;
import tools.modal.ModalProjectOpen;

public class TaskbarMenu
{
    private StateBuilder state;
    private ArrayList<TaskbarMenuItem> menuItems;
    private boolean menuActive;
    
    public TaskbarMenu(StateBuilder state)
    {
        this.state = state;
        this.menuActive = false;
        this.build();
    }
    
    public void build()
    {
        // Blank Array
        this.menuItems = new ArrayList();
        
        // No Project Options
        if(this.state.getProject() == null)
        {
            this.menuItems.add(new TaskbarMenuItem(this, "NEW", "NEW PROJECT", "file_new"));
            this.menuItems.add(new TaskbarMenuItem(this, "OPEN", "OPEN PROJECT", "file_open"));
        }
        
        // Regular Options
        else
        {
            this.menuItems.add(new TaskbarMenuItem(this, "PRO", "PROJECT SETTINGS", "file_new"));
            this.menuItems.add(new TaskbarMenuItem(this, "BRD", "BOARD EDITOR", "file_new"));
            this.menuItems.add(new TaskbarMenuItem(this, "ITM", "ITEM EDITOR", "file_new"));
        }
        
        // Standard Options
        this.menuItems.add(new TaskbarMenuItem(this, "CONFIG", "SETTINGS", "main_settings"));
        this.menuItems.add(new TaskbarMenuItem(this, "ABOUT", "ABOUT BUILDER", "main_about"));
        this.menuItems.add(new TaskbarMenuItem(this, "QUIT", "EXIT BUILDER", "board_delete"));
    }
    
    public Rectangle getArea()
    {
        return new Rectangle(0, 718 - (this.menuItems.size() * 50), 350, this.menuItems.size() * 50);
        // NOTE: may wish to get the text length of the longest item and use that to determine width
    }
    
    public void inputClickButton()
    {
        // Clicked on the 'MENU' button
        if(this.menuActive) {this.menuActive = false;}
        else {this.menuActive = true;}
    }
    
    public void inputClickMenu(MouseEvent e)
    {
        // Clicked in the menu area
        for(int x = 0; x < this.menuItems.size(); x++)
        {
            // Clicked on an menu item
            if(this.menuItems.get(x).getArea(x).contains(e.getPoint())) {this.inputClickMenuItem(this.menuItems.get(x).getRef());}
            
            // Close the menu
            this.setActive(false);
        }
    }
    
    private void inputClickMenuItem(String ref)
    {
        // New Project
        if(ref.equals("NEW"))
        {
            //
        }
        
        // Open Project
        if(ref.equals("OPEN")) {this.state.setModal(new ModalProjectOpen(this.state));}
        
        // Project Settings
        if(ref.equals("PRO")) {this.state.toolActionProject();}
        
        // Board Editor
        if(ref.equals("BRD")) {this.state.toolActionBoard();}
        
        // Item Editor
        if(ref.equals("ITM")) {this.state.toolActionItem();}
        
        // Settings
        if(ref.equals("CONFIG")) {state.actionConfig(true);}
        
        // About Builder
        if(ref.equals("ABOUT")) {state.actionAbout(true);}
        
        // Exit Builder
        if(ref.equals("QUIT")) {state.actionExit();}
    }
    
    public boolean isActive()
    {
        return this.menuActive;
    }
    
    public void render(Graphics g)
    {
        // Background
        Drawing.fillRect(g, this.getArea(), "STANDARD_FORE");
        
        // Items
        for(int x = 0; x < this.menuItems.size(); x++)
        {
            this.menuItems.get(x).render(g, x);
        }
        
        // Border (top, bottom and left) - could we make a Drawing method that takes a rect, colour and which sides to draw?
        g.setColor(Colour.getColour("BLACK"));
        g.drawLine(this.getArea().x, this.getArea().y, this.getArea().x + this.getArea().width, this.getArea().y);
        g.drawLine(this.getArea().x + this.getArea().width, this.getArea().y, this.getArea().x + this.getArea().width, this.getArea().y + this.getArea().height);
        g.drawLine(this.getArea().x, this.getArea().y + this.getArea().height, this.getArea().x + this.getArea().width, this.getArea().y + this.getArea().height);
    }
    
    public void setActive(boolean value)
    {
        this.menuActive = value;
    }
    
    public void tick()
    {
        
    }
    
}