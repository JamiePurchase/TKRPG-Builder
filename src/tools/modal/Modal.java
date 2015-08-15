package tools.modal;

import blocks.Block;
import gfx.Drawing;
import gfx.Text;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public abstract class Modal
{
    private String modalTitle;
    private Rectangle modalArea;
    private BufferedImage modalIcon;
    private ArrayList<Block> modalBlock;
    
    public Modal(String title, Rectangle area)
    {
        this.modalTitle = title;
        this.modalArea = area;
        this.modalIcon = null;
        this.modalBlock = new ArrayList();
    }
    
    public Modal(String title, Rectangle area, BufferedImage icon)
    {
        this.modalTitle = title;
        this.modalArea = area;
        this.modalIcon = icon;
        this.modalBlock = new ArrayList();
    }
    
    public void addBlock(Block block)
    {
        this.modalBlock.add(block);
    }
    
    public Rectangle getArea()
    {
        return this.modalArea;
    }
    
    public abstract void inputClick(MouseEvent e);
    
    public abstract void inputKey(String key);
    
    public abstract void render(Graphics g);
    
    public void renderModal(Graphics g)
    {
        // Tool Background
        Drawing.fillRect(g, this.modalArea, "STANDARD_BACK");
        
        // Tool Border
        Drawing.drawRect(g, this.modalArea, 5, "STANDARD_FORE");
        
        // Titlebar
        Drawing.fillRect(g, this.modalArea.x, this.modalArea.y, this.modalArea.width, 34, "STANDARD_FORE");
        int titlePos = this.modalArea.x + 12;
        if(this.modalIcon != null)
        {
            g.drawImage(Drawing.getImage("icon/icon.png"), this.modalArea.x + 8, this.modalArea.y, null);
            titlePos = this.modalArea.x + 50;
        }
        Text.write(g, this.modalTitle, titlePos, this.modalArea.y + 24, "LEFT", "TITLEBAR", "BLACK");
        
        // Titlebar Close
        //this.renderWindowClose(g);
        
        // Blocks
        this.renderModalBlocks(g);
    }
    
    private void renderModalBlocks(Graphics g)
    {
        for(int x = 0; x < this.modalBlock.size(); x++)
        {
            this.modalBlock.get(x).render(g);
        }
    }
    
    public abstract void tick();
    
}