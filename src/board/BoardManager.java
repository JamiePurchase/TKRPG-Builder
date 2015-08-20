package board;

import app.Engine;
import file.FileService;
import items.ItemFile;
import java.io.File;
import java.util.ArrayList;
import projects.Project;
import system.ID;
import tools.files.FileItem;
import tools.files.FileType;

public class BoardManager
{
    private String project;
    
    public BoardManager(String project)
    {
        this.project = project;
    }
    
    public ArrayList<FileItem> getBoardArray()
    {
        ArrayList<File> files = FileService.getFolder(getBoardDirectory(), true, false, "tk7brd");
        ArrayList<FileItem> items = new ArrayList();
        for(int x = 0; x < files.size(); x++)
        {
            items.add(new FileItem(files.get(x), FileType.ITEM));
        }
        return items;
    }
    
    private String getBoardDirectory()
    {
        return Engine.getAppVariable("BUILDER_PATH") + "Data/" + this.project + "/Boards/";
    }
    
    public String getPath(String file)
    {
        return Engine.getAppVariable("BUILDER_PATH") + "Data/" + this.project + "/Boards/" + file + ".tk7brd";
    }
    
    public BoardFile loadBoard(File file)
    {
        return loadBoard(file.getPath());
    }
    
    public BoardFile loadBoard(String file)
    {
        // Load the Board File
        ArrayList<String> data = FileService.loadFile(getPath(file));
        
        // Create the Board Object
        int sizeX = Integer.parseInt(data.get(1).split("\\|")[0]);
        int sizeY = Integer.parseInt(data.get(1).split("\\|")[1]);
        BoardFile board = new BoardFile(project, file, data.get(0), sizeX, sizeY);
        
        // Set the Board Terrain
        int tileX = 0;
        int tileY = 0;
        String tileSet = "";
        int tileCol = 0;
        int tileRow = 0;
        for(int tile = 0; tile < (sizeX * sizeY); tile++)
        {
            // Set Terrain
            tileSet = data.get(tile + 2).split("\\|")[0];
            tileCol = Integer.parseInt(data.get(tile + 2).split("\\|")[1]);
            tileRow = Integer.parseInt(data.get(tile + 2).split("\\|")[2]);
            board.setTerrainAt(tileX, tileY, new Terrain(tileSet, tileCol, tileRow));
            
            // Next Tile
            tileX ++;
            if(tileX >= sizeX)
            {
                tileX = 0;
                tileY ++;
            }
        }
        
        // Return the Board Object
        return board;
    }
    
}