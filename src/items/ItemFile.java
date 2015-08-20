package items;

import app.Engine;
import config.ConfigManager;
import file.FileService;
import java.util.ArrayList;

public class ItemFile
{
    private String itemProject, itemFile, itemName;
    private ItemType itemType;
    private boolean itemKey;
    
    public ItemFile(String project, String file, String name, String type, boolean key)
    {
        this.itemProject = project;
        this.itemFile = file;
        this.itemName = name;
        this.itemType = ItemType.WEAPON;
        this.itemKey = key;
    }
    
    public String getName()
    {
        return this.itemName;
    }
    
    public void save()
    {
        FileService.saveFile(this.saveData(), this.savePath());
    }
    
    private ArrayList<String> saveData()
    {
        ArrayList<String> data = new ArrayList();
        data.add(this.itemName);
        data.add(this.itemType.toString());
        String key = "FALSE";
        if(this.itemKey) {key = "TRUE";}
        data.add(key);
        return data;
    }
    
    private String savePath()
    {
        return Engine.getAppVariable("BUILDER_PATH") + "Data/" + this.itemProject + "/Items/" + this.itemFile + ".tk7itm";
    }
    
    public void setName(String name)
    {
        this.itemName = name;
    }
    
}