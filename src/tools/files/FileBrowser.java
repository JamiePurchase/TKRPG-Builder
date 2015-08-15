package tools.files;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import tools.modal.Modal;

public class FileBrowser extends Modal
{
    private ArrayList<FileItem> browserItems;
    private int browserPage;
    
    public FileBrowser(String title)
    {
        super(title, new Rectangle(333, 159, 700, 450));
        this.browserItems = new ArrayList();
        this.browserPage = 0;
    }
    
    public FileBrowser(String title, Rectangle area)
    {
        super(title, area);
        this.browserItems = new ArrayList();
        this.browserPage = 0;
    }
    
    public void inputClick(MouseEvent e)
    {
        //
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
        //
    }
    
    public void tick()
    {
        
    }
    
}