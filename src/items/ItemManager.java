package items;

import app.Engine;
import file.FileService;
import java.io.File;
import java.util.ArrayList;
import projects.Project;
import static projects.ProjectManager.getProject;
import tools.files.FileItem;
import tools.files.FileType;

public class ItemManager
{
    private String project;
    
    public ItemManager(String project)
    {
        this.project = project;
    }
    
    public ArrayList<FileItem> getItemArray()
    {
        ArrayList<File> files = FileService.getFolder(getItemDirectory(), true, false, "tk7itm");
        ArrayList<FileItem> items = new ArrayList();
        for(int x = 0; x < files.size(); x++)
        {
            items.add(new FileItem(files.get(x), FileType.ITEM));
        }
        return items;
    }
    
    private String getItemDirectory()
    {
        return Engine.getAppVariable("BUILDER_PATH") + "Data/" + this.project + "/Items/";
    }
    
    public ItemType getType(String type)
    {
        return ItemType.WEAPON;
    }
    
    public String getPath(String file)
    {
        return Engine.getAppVariable("BUILDER_PATH") + "Data/" + this.project + "/Items/" + file + ".tk7itm";
    }
    
    public ItemFile loadItem(File file)
    {
        return loadItem(file.getPath());
    }
    
    public ItemFile loadItem(String file)
    {
        // Load the Item File
        ArrayList<String> data = FileService.loadFile(getPath(file));
        
        // Create the Item Object
        boolean key = false;
        if(data.get(2).equals("TRUE")) {key = true;}
        ItemFile item = new ItemFile(this.project, file, data.get(0), data.get(1), key);
        
        // Return the Board Object
        return item;
    }
    
}