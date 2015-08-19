package projects;

import app.Engine;
import file.FileService;
import java.io.File;
import java.util.ArrayList;

public class ProjectManager
{
    
    public static void createProject(String name)
    {
        // Create the project file
        FileService.saveFile(name, getProjectDirectory(name + ".tk7pro"));
        
        // Create the project directory
        FileService.createFolder(Engine.getAppVariable("BUILDER_PATH") + "Data/" + name);
        
        // Create the data subdirectories
        for(int x = 0; x < getProjectFolders().size(); x++)
        {
            FileService.createFolder(Engine.getAppVariable("BUILDER_PATH") + "Data/" + name + "/" + getProjectFolders().get(x));
        }
        
        // Update ??? (list of recent projects?)
    }
    
    public static String getPath(String name)
    {
        return Engine.getAppVariable("BUILDER_PATH") + "Projects/" + name + ".tk7pro";
    }
    
    public static Project getProject(File file)
    {
        return new Project(FileService.getFileName(file));
    }
    
    public static ArrayList<Project> getProjectArray()
    {
        ArrayList<File> files = FileService.getFolder(getProjectDirectory(), true, false, "tk7pro");
        ArrayList<Project> projects = new ArrayList();
        for(int x = 0; x < files.size(); x++)
        {
            projects.add(getProject(files.get(x)));
        }
        return projects;
    }
    
    private static String getProjectDirectory()
    {
        return Engine.getAppVariable("BUILDER_PATH") + "Projects/";
    }
    
    private static String getProjectDirectory(String extend)
    {
        return getProjectDirectory() + extend;
    }
    
    private static ArrayList<String> getProjectFolders()
    {
        ArrayList<String> folders = new ArrayList();
        folders.add("Boards");
        folders.add("Characters");
        folders.add("Items");
        folders.add("Quests");
        folders.add("Scenes");
        folders.add("Terrain");
        return folders;
    }
    
}