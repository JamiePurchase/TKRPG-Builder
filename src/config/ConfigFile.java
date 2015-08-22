package config;

import file.FileService;
import java.util.ArrayList;

public class ConfigFile
{
    private int configScheme;
    
    public ConfigFile(int scheme)
    {
        this.configScheme = scheme;
    }
    
    public int getScheme()
    {
        return this.configScheme;
    }
    
    public void save()
    {
        FileService.saveFile(this.saveData(), ConfigManager.getPath());
    }
    
    private ArrayList<String> saveData()
    {
        ArrayList<String> data = new ArrayList();
        data.add("" + this.getScheme());
        return data;
    }
    
}