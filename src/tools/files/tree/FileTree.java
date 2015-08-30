package tools.files.tree;

import gfx.Drawing;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import shapes.RectangleTools;
import styles.Scheme;

public class FileTree
{
    private Rectangle treeArea;
    private ArrayList<Node> treeNode;
    private int treeList;
    
    public FileTree()
    {
        this.treeArea = new Rectangle(1000, 50, 300, 600);
        this.treeNode = new ArrayList();
        this.treeList = 0;
    }
    
    public void addNode(String ref, String label)
    {
        this.treeNode.add(new Node(this, ref, label));
    }
    
    public Rectangle getArea()
    {
        return this.treeArea;
    }
    
    public Node getNode(String ref)
    {
        for(int x = 0; x < this.treeNode.size(); x++)
        {
            if(this.treeNode.get(x).checkRef(ref)) {return this.treeNode.get(x);}
        }
        return null;
    }
    
    public void inputClick(MouseEvent e)
    {
        for(int x = 0; x < this.treeNode.size(); x++)
        {
            // Expand
            if(this.treeNode.get(x).getAreaExpand(x).contains(e.getPoint())) {this.treeNode.get(x).expand();}
            
            // Label
            if(this.treeNode.get(x).getAreaLabel(x).contains(e.getPoint()))
            {
                // DO SOMETHING
            }
        }
    }
    
    public void render(Graphics g)
    {
        this.renderPane(g);
        this.renderTree(g);
    }
    
    private void renderPane(Graphics g)
    {
        // Background
        Drawing.fillRect(g, this.treeArea, Scheme.Colour("STANDARD_BACK"));
        
        // Border
        Drawing.drawRect(g, this.treeArea, 5, Scheme.Colour("STANDARD_FORE"));
        Drawing.drawRect(g, this.treeArea, Color.BLACK);
        Drawing.drawRect(g, RectangleTools.inner(treeArea, 5), Color.BLACK);
    }
    
    private void renderTree(Graphics g)
    {
        for(int x = 0; x < this.treeNode.size(); x++)
        {
            this.treeNode.get(x).render(g, x);
        }
    }
    
}