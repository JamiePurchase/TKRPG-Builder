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
    
    public static ConfigFile init()
    {
        // Load existing config file
        if(FileService.findFile(getPath())) {return initLoad();}
        
        // Create a new config file
        return initCreate();
    }
    
    private static ConfigFile initCreate()
    {
        // Create a config object
        ConfigFile file = new ConfigFile(1);
        
        // Save the config file
        file.save();
        
        // Return the config object
        return file;
    }
    
    private static ConfigFile initLoad()
    {
        // Load the config file
        ArrayList<String> data = FileService.loadFile(getPath());
        
        // Build the config object
        return new ConfigFile(Integer.parseInt(data.get(0)));
    }
    
}