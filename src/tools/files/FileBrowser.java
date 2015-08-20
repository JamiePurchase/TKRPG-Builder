package tools.files;

import gfx.Text;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import tools.modal.Modal;

public class FileBrowser extends Modal
{
    private ArrayList<FileItem> browserItems;
    private int browserPage;
    
    // NOTE: need to take in the directory and the file type
    public FileBrowser(String title, ArrayList<FileItem> items)
    {
        super(title, new Rectangle(333, 159, 700, 450), true);
        //this.browserItems = new ArrayList();
        this.browserItems = items;
        this.browserPage = 0;
    }
    
    public FileBrowser(String title, ArrayList<FileItem> items, Rectangle area)
    {
        super(title, area, true);
        //this.browserItems = new ArrayList();
        this.browserItems = items;
        this.browserPage = 0;
    }
    
    private Rectangle getFileItemArea(int pos)
    {
        return new Rectangle(360, (40 * pos) + 200, 32, 32);
    }
    
    public void inputClick(MouseEvent e)
    {
        inputClickFile(e);
    }
    
    public FileItem inputClickFile(MouseEvent e)
    {
        for(int x = 0; x < this.browserItems.size(); x++)
        {
            if(this.getFileItemArea(x).contains(e.getPoint()))
            {
                return this.browserItems.get(x);
            }
        }
        return null;
    }
    
    public void inputKey(String key)
    {
        //
    }
    
    public void render(Graphics g)
    {
        this.renderModal(g);
        this.renderFile(g);
    }
    
    private void renderFile(Graphics g)
    {
        for(int x = 0; x < this.browserItems.size(); x++)
        {
            this.renderFileItem(g, x);
        }
    }
    
    private void renderFileItem(Graphics g, int pos)
    {
        // Highlight
        /*if(this.getFileRect(pos).contains(Engine.getMousePoint()))
        {
            Drawing.drawRect(g, this.getFileRect(pos), "STANDARD_FORE");
        }*/
        
        // Icon
        g.drawImage(this.browserItems.get(pos).getIcon(), 360, (40 * pos) + 200, null);
        
        // Text
        Text.write(g, this.browserItems.get(pos).getName(), 410, (40 * pos) + 225, "LEFT", "FILE_BROWSE", "BLACK");
    }
    
    public void tick()
    {
        
    }
    
}