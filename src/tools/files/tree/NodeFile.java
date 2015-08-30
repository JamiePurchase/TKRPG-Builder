package tools.files.tree;

import framework.files.FileItem;
import java.awt.Graphics;

public class NodeFile extends Node
{
    private Node nodeParent;
    
    public NodeFile(Node parent, FileItem file)
    {
        super(parent.getTree(), "", file.getName());
        this.nodeParent = parent;
    }
    
    public void render(Graphics g)
    {
        
    }
    
}