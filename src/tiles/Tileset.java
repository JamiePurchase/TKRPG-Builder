package tiles;

import config.ConfigManager;
import file.FileService;
import gfx.Drawing;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Tileset
{
    private String tilesetProject, tilesetName;
    private BufferedImage tilesetSheet;
    private int tilesetTileSize;
    
    // TEMP
    private int tileCols, tileRows;
    
    public Tileset(String project, String file, String name, int size)
    {
        this.tilesetProject = project;
        this.tilesetName = name;
        this.tilesetSheet = Drawing.getImageFile(TilesetManager.getPathSheet(project, file));
        this.tilesetTileSize = size;
        
        // TEMP
        this.tileCols = 1;
        this.tileRows = 1;
    }
    
    public BufferedImage getTileAt(int col, int row)
    {
        if(col <= this.tileCols && row <= this.tileRows)
        {
            return this.tilesetSheet.getSubimage(col * this.tilesetTileSize, row * this.tilesetTileSize, this.tilesetTileSize, this.tilesetTileSize);
        }
        return this.tilesetSheet.getSubimage(0, 0, this.tilesetTileSize, this.tilesetTileSize);
    }
    
    public void save()
    {
        FileService.saveFile(this.saveData(), TilesetManager.getPath(this.tilesetProject, this.tilesetName));
    }
    
    private ArrayList<String> saveData()
    {
        ArrayList<String> data = new ArrayList();
        //data.add();
        return data;
    }
    
}