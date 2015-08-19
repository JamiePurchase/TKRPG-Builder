package board;

import java.awt.image.BufferedImage;
import tiles.Tileset;
import tiles.TilesetManager;

public class Terrain
{
    private String terrainTileset;
    private int terrainTileCol, terrainTileRow;
    
    public Terrain(String tileset, int col, int row)
    {
        this.terrainTileset = tileset;
        this.terrainTileCol = col;
        this.terrainTileRow = row;
    }
    
    public String getData()
    {
        return this.terrainTileset + "|" + this.terrainTileCol + "|" + this.terrainTileRow;
    }
    
    public BufferedImage getImage()
    {
        return this.getTileset().getTileAt(0, 0);
    }
    
    private Tileset getTileset()
    {
        // TEMP
        return TilesetManager.loadTileset("Mushroom", this.terrainTileset);
    }
    
}