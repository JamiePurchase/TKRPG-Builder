package tools.files;

import gfx.Drawing;
import java.awt.image.BufferedImage;
import java.io.File;

public class FileItem
{
    private File filePath;
    private FileType fileType;
    
    public FileItem(File path, FileType type)
    {
        this.filePath = path;
        this.fileType = type;
    }
    
    public BufferedImage getIcon()
    {
        return Drawing.getImage("icon/io_" + this.fileType.toString() + ".png");
    }
    
    public String getName()
    {
        String name = this.filePath.getName();
        return name.substring(0, name.length() - 7);
    }
    
    public File getPath()
    {
        return this.filePath;
    }
    
    public FileType getType()
    {
        return this.fileType;
    }
    
}