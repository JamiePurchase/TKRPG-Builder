package projects;

public class Project
{
    private String name;
    
    public Project(String name)
    {
        this.name = name;
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
    
}