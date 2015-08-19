package items;

import app.Engine;
import file.FileService;
import java.util.ArrayList;

public class ItemManager
{
    private String project;
    
    public ItemManager(String project)
    {
        this.project = project;
    }
    
    public ItemType getType(String type)
    {
        return ItemType.WEAPON;
    }
    
    public String getPath(String file)
    {
        return Engine.getAppVariable("BUILDER_PATH") + "Data/" + this.project + "/Items/" + file + ".tk7itm";
    }
    
    public Item loadItem(String file)
    {
        // Load the Item File
        ArrayList<String> data = FileService.loadFile(getPath(file));
        
        // DEBUG
        System.out.println("LOAD ITEM");
        System.out.println(data);
        
        // Create the Item Object
        boolean key = false;
        if(data.get(2).equals("TRUE")) {key = true;}
        //Item item = new Item(project, data.get(0), data.get(1), key);
        
        // TEST
        Item item = new Item("Mushroom", "Iron Sword", "WEAPON", false);
        
        // Return the Board Object
        return item;
    }
    
}