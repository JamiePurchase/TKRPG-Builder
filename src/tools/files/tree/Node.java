package tools.files.tree;

import app.Engine;
import framework.files.FileItem;
import gfx.Drawing;
import gfx.Fonts;
import gfx.Text;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import styles.Scheme;

public class Node
{
    private FileTree nodeTree;
    private String nodeRef, nodeLabel;
    private boolean nodeClose;
    private ArrayList<Node> nodeChild;
    
    public Node(FileTree tree, String ref, String label)
    {
        this.nodeTree = tree;
        this.nodeRef = ref;
        this.nodeLabel = label;
        this.nodeClose = true;
        this.nodeChild = null;
    }
    
    public void addNode(Node node)
    {
        this.nodeChild.add(node);
    }
    
    public void addNodeArray(ArrayList<FileItem> files)
    {
        for(int x = 0; x < files.size(); x++)   
        {
            this.addNode(new NodeFile(this, files.get(x)));
        }
    }
    
    public boolean checkRef(String ref)
    {
        if(this.nodeRef.equals(ref)) {return true;}
        return false;
    }
    
    public void expand()
    {
        if(this.nodeClose) {this.nodeClose = false;}
        else {this.nodeClose = true;}
    }
    
    public Rectangle getAreaExpand(int position)
    {
        return new Rectangle(this.nodeTree.getArea().x + 15, this.nodeTree.getArea().y + 10 + (position * 25), 20, 20);
    }
    
    public Rectangle getAreaLabel(int position)
    {
        return new Rectangle(this.nodeTree.getArea().x + 45, this.nodeTree.getArea().y + 10 + (position * 25), 180, 20);
    }
    
    public FileTree getTree()
    {
        return this.nodeTree;
    }
    
    public void render(Graphics g, int position)
    {
        this.renderExpand(g, position);
        this.renderLabel(g, position);
    }
    
    private void renderExpand(Graphics g, int position)
    {
        String expand = "+";
        if(!this.nodeClose) {expand = "-";}
        if(this.getAreaExpand(position).contains(Engine.getMousePoint())) {Drawing.fillRect(g, this.getAreaExpand(position), Scheme.Colour("SECONDARY_FORE"));}
        Text.write(g, expand, this.getAreaExpand(position).x + (this.getAreaExpand(position).width / 2), this.getAreaExpand(position).y + 15, "CENTER", Fonts.getFont("STANDARD"), Color.BLACK);
        if(this.getAreaExpand(position).contains(Engine.getMousePoint())) {Drawing.drawRect(g, this.getAreaExpand(position), Color.BLACK);}
    }
    
    private void renderLabel(Graphics g, int position)
    {
        if(this.getAreaLabel(position).contains(Engine.getMousePoint())) {Drawing.fillRect(g, this.getAreaLabel(position), Scheme.Colour("SECONDARY_FORE"));}
        Text.write(g, this.nodeLabel, this.getAreaLabel(position).x + 5, this.getAreaLabel(position).y + 15, "LEFT", Fonts.getFont("STANDARD"), Color.BLACK);
    }
    
}