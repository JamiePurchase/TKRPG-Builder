package tiles;

import app.Engine;

public class TilesetManager
{
    
    public static String getPath(String project, String file)
    {
        return Engine.getAppVariable("BUILDER_PATH") + "Data/" + project + "/Terrain/" + file + ".tk7tst";
    }
    
    public static String getPathSheet(String project, String file)
    {
        return Engine.getAppVariable("BUILDER_PATH") + "Data/" + project + "/TEMP_SHEET/" + file + ".png";
    }
    
    public static Tileset loadTileset(String project, String file)
    {
        // NOTE: load the tileset file and get data for the sheet image and tile size
        //return new Tileset(project, file, "", 0);
        
        // TEMP
        return new Tileset("Mushroom", "TEST", "TEST", 32);
    }
    
}