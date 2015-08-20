package board;

import app.Engine;
import file.FileService;
import gfx.Drawing;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import tiles.TilesetManager;

public class BoardFile
{
    private String boardProject, boardFile, boardName;
    private int boardSizeX, boardSizeY;
    private Terrain[][] boardTerrain;
    
    public BoardFile(String project, String file, String name, int sizeX, int sizeY)
    {
        this.boardProject = project;
        this.boardFile = file;
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
        return this.boardSizeY;
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
        FileService.saveFile(this.saveData(), savePath());
    }
    
    private ArrayList<String> saveData()
    {
        System.out.println("BOARD FILE -> SAVE");
        
        ArrayList<String> data = new ArrayList();
        data.add(this.getName());
        data.add(this.getSizeX() + "|" + this.getSizeY());
        for(int x = 0; x < this.getSizeX(); x++)
        {
            for(int y = 0; y < this.getSizeY(); y++)
            {
                System.out.println(this.getTerrainAt(x, y).getData());
                data.add(this.getTerrainAt(x, y).getData());
            }
        }
        return data;
    }
    
    private String savePath()
    {
        return Engine.getAppVariable("BUILDER_PATH") + "Data/" + this.boardProject + "/Boards/" + this.boardFile + ".tk7brd";
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