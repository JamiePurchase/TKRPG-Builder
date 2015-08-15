package tools.modal;

import app.Engine;
import blocks.BlockGroup;
import blocks.form.BlockForm;
import gfx.Drawing;
import gfx.Text;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import projects.Project;
import projects.ProjectManager;
import states.StateBuilder;

public class ModalProjectOpen extends Modal
{
    private StateBuilder state;
    private ArrayList<Project> projects;
    
    public ModalProjectOpen(StateBuilder state)
    {
        super("Open Project", new Rectangle(333, 159, 700, 450));
        this.state = state;
        this.projects = ProjectManager.getProjectArray();
        
        // TEMP
        /*BlockForm form = new BlockForm("NEW_");
        form.addBlock("LABEL_NAME", new Block());
        this.addBlock(form);*/
    }
    
    private Rectangle getFileRect(int pos)
    {
        return new Rectangle(350, (40 * pos) + 200, 250, 40);
    }
    
    public void inputClick(MouseEvent e)
    {
        for(int x = 0; x < this.projects.size(); x++)
        {
            if(this.getFileRect(x).contains(e.getPoint()))
            {
                this.state.setProjectOpen(this.projects.get(x));
                this.state.setModal();
            }
        }
    }
    
    public void inputKey(String key)
    {
        //
    }
    
    public void render(Graphics g)
    {
        this.renderModal(g);
        this.renderFiles(g);
    }
    
    private void renderFiles(Graphics g)
    {
        for(int x = 0; x < this.projects.size(); x++)
        {
            if(this.getFileRect(x).contains(Engine.getMousePoint()))
            {
                Drawing.drawRect(g, this.getFileRect(x), "STANDARD_FORE");
            }
            g.drawImage(Drawing.getImage("icon/io_project.png"), 360, (40 * x) + 200, null);
            Text.write(g, this.projects.get(x).getName(), 410, (40 * x) + 225, "LEFT", "FILE_BROWSE", "BLACK");
        }
    }
    
    public void tick()
    {
        
    }
    
}