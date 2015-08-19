package board;

import app.Engine;
import file.FileService;
import java.io.File;
import java.util.ArrayList;
import projects.Project;
import system.ID;

public class BoardManager
{
    
    /*public static Board createBoard()
    {
        return new Board(new ID("1"), "BOARD", 20, 16);
    }*/
    
    /*public static Board getBoard()
    {
        return new Board();
    }*/
    
    // NOTE: it would be far too clunky and inefficient to build an array of board objects
    // we should create an array of FileItems (that are used as pointers in the FileBrowser)
    /*public static ArrayList<Project> getBoardArray()
    {
        ArrayList<File> files = FileService.getFolder(getBoardDirectory(), true, false, "tk7brd");
        ArrayList<Project> projects = new ArrayList();
        for(int x = 0; x < files.size(); x++)
        {
            projects.add(getBoard(files.get(x)));
        }
        return projects;
    }*/
    
    /*private static String getBoardDirectory()
    {
        return Engine.getAppVariable("BUILDER_PATH") + "Data/" + APPNAME + "/Boards/";
        // NOTE: appname in the path above needs sorting
    }
    
    private static String getBoardDirectory(String extend)
    {
        return getBoardDirectory() + extend;
    }*/
    
    public static String getPath(String project, String file)
    {
        return Engine.getAppVariable("BUILDER_PATH") + "Data/" + project + "/Boards/" + file + ".tk7brd";
    }
    
    public static Board loadBoard(String project, String file)
    {
        // Load the Board File
        ArrayList<String> data = FileService.loadFile(getPath(project, file));
        
        // Create the Board Object
        int sizeX = Integer.parseInt(data.get(1).split("\\|")[0]);
        int sizeY = Integer.parseInt(data.get(1).split("\\|")[1]);
        Board board = new Board(project, data.get(0), sizeX, sizeY);
        
        // Set the Board Terrain
        
        // Return the Board Object
        return board;
    }
    
}