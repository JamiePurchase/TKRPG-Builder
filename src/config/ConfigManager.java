package config;

import app.Engine;
import file.FileService;
import java.util.ArrayList;

public class ConfigManager
{
    
    public static String getPath()
    {
        return Engine.getAppVariable("BUILDER_PATH") + "Config/Config.tk7cfg";
    }
    
    public static Config init()
    {
        // Load existing config file
        if(FileService.findFile(getPath())) {return initLoad();}
        
        // Create a new config file
        return initCreate();
    }
    
    private static Config initCreate()
    {
        // Create a config object
        Config file = new Config(1);
        
        // Save the config file
        file.save();
        
        // Return the config object
        return file;
    }
    
    private static Config initLoad()
    {
        // Load the config file
        ArrayList<String> data = FileService.loadFile(getPath());
        
        // Build the config object
        return new Config(Integer.parseInt(data.get(0)));
    }
    
}