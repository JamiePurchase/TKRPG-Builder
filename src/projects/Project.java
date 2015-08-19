package projects;

import config.ConfigManager;
import file.FileService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Project
{
    private String name;
    private String updated;
    
    public Project(String name)
    {
        this.name = name;
        //this.updated = 
    }
    
    public String getInfo()
    {
        // TEMP (version? file size? number of files? date saved?)
        // Note: could even iterate through all of the above (five seconds each?)
        return "V 1.0 - 15/08/2015";
    }
    
    public String getName()
    {
        return this.name;
    }
    
    public String getUpdated()
    {
        return this.updated;
    }
    
    public void save()
    {
        FileService.saveFile(this.saveData(), ProjectManager.getPath(this.name));
    }
    
    private ArrayList<String> saveData()
    {
        ArrayList<String> data = new ArrayList();
        data.add(this.getName());
        data.add(this.getUpdated());
        return data;
    }
    
    public void setUpdated()
    {
        this.updated = new SimpleDateFormat("dd/mm/yyyy").format(Calendar.getInstance().getTime());
    }
    
}