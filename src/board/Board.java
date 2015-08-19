package board;

import file.FileService;
import gfx.Drawing;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import tiles.TilesetManager;

public class Board
{
    private String boardProject, boardName;
    private int boardSizeX, boardSizeY;
    private Terrain[][] boardTerrain;
    
    public Board(String project, String name, int sizeX, int sizeY)
    {
        this.boardProject = project;
        this.boardName = name;
        this.boardSizeX = sizeX;
        this.boardSizeY = sizeY;
        this.boardTerrain = new Terrain[sizeX][sizeY];
    }
    
    public String getName()
    {
        return this.boardName;
    }
    
    public int getSizeX()
    {
        return this.boardSizeX;
    }
    
    public int getSizeY()
    {
        return this.boardSizeX;
    }
    
    public Terrain[][] getTerrain()
    {
        return this.boardTerrain;
    }
    
    public Terrain getTerrainAt(int posX, int posY)
    {
        return this.boardTerrain[posX][posY];
    }
    
    public void save()
    {
        FileService.saveFile(this.saveData(), TilesetManager.getPath(this.boardProject, this.boardName));
    }
    
    private ArrayList<String> saveData()
    {
        ArrayList<String> data = new ArrayList();
        data.add(this.getName());
        data.add(this.getSizeX() + "|" + this.getSizeY());
        // Loop through all tiles
        data.add(this.getTerrainAt(0, 0).getData());
        //
        return data;
    }
    
    public void setTerrainAll(Terrain terrain)
    {
        for(int x = 0; x < this.getSizeX(); x++)
        {
            for(int y = 0; y < this.getSizeY(); y++)
            {
                this.setTerrainAt(x, y, terrain);
            }
        }
    }
    
    public void setTerrainAt(int posX, int posY, Terrain terrain)
    {
        this.boardTerrain[posX][posY] = terrain;
    }
    
    public void render(Graphics g, Rectangle area, int offsetX, int offsetY)
    {
        for(int x = 0; x < this.getSizeX(); x++)
        {
            for(int y = 0; y < this.getSizeY(); y++)
            {
                Drawing.drawImage(g, this.boardTerrain[x + offsetX][y + offsetY].getImage(), area.x + (x * 32), area.y + (y * 32));
            }
        }
    }
    
}