package tools.files;

import gfx.Drawing;
import java.awt.image.BufferedImage;
import java.io.File;

public class FileItem
{
    private String fileName;
    private File filePath;
    private FileType fileType;
    
    public FileItem(String name, File path)
    {
        this.fileName = name;
        this.filePath = path;
        //this.fileType = type;
        // NOTE: alot of these values can be found automatically
    }
    
    public BufferedImage getIcon()
    {
        return Drawing.getImage("icon/data_" + this.fileType.toString());
    }
    
}