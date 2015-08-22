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
import styles.Scheme;

public abstract class Modal
{
    private String modalTitle;
    private Rectangle modalArea;
    private boolean modalClose;
    private BufferedImage modalIcon;
    private ArrayList<Block> modalBlock;
    
    public Modal(String title, Rectangle area, boolean close)
    {
        this.modalTitle = title;
        this.modalArea = area;
        this.modalClose = close;
        this.modalIcon = null;
        this.modalBlock = new ArrayList();
    }
    
    public Modal(String title, Rectangle area, boolean close, BufferedImage icon)
    {
        this.modalTitle = title;
        this.modalArea = area;
        this.modalClose = close;
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
    
    private Rectangle getAreaInner()
    {
        return new Rectangle(this.modalArea.x + 5, this.modalArea.y + 34, this.modalArea.width - 10, this.modalArea.height - 39);
    }
    
    public abstract void inputClick(MouseEvent e);
    
    public abstract void inputKey(String key);
    
    public abstract void render(Graphics g);
    
    public void renderModal(Graphics g)
    {
        // Tool Background
        Drawing.fillRect(g, this.modalArea, Scheme.Colour("STANDARD_BACK"));
        
        // Tool Border
        Drawing.drawRect(g, this.modalArea, 5, Scheme.Colour("STANDARD_FORE"));
        
        // Titlebar
        Drawing.fillRect(g, this.modalArea.x, this.modalArea.y, this.modalArea.width, 34, Scheme.Colour("STANDARD_FORE"));
        int titlePos = this.modalArea.x + 12;
        if(this.modalIcon != null)
        {
            g.drawImage(Drawing.getImage("icon/icon.png"), this.modalArea.x + 8, this.modalArea.y, null);
            titlePos = this.modalArea.x + 50;
        }
        Text.write(g, this.modalTitle, titlePos, this.modalArea.y + 24, "LEFT", "TITLEBAR", "BLACK");
        
        // Titlebar Close
        if(this.modalClose)
        {
            // NOTE: render the close button if we need to
        }
        
        // Blocks
        this.renderModalBlocks(g);
        
        // Border
        this.renderModalBorder(g);
    }
    
    private void renderModalBorder(Graphics g)
    {
        Drawing.drawRect(g, this.getArea(), "BLACK");
        Drawing.drawRect(g, this.getAreaInner(), "BLACK");
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