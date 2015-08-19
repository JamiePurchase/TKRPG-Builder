package config;

import file.FileService;
import java.util.ArrayList;

public class Config
{
    private int configScheme;
    
    public Config(int scheme)
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