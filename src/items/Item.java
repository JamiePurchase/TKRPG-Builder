package items;

public class Item
{
    private String itemProject, itemName;
    private ItemType itemType;
    private boolean itemKey;
    
    public Item(String project, String name, String type, boolean key)
    {
        this.itemProject = project;
        this.itemName = name;
        this.itemType = this.buildType(type);
        this.itemKey = key;
    }
    
    private ItemType buildType(String type)
    {
        return ItemType.WEAPON;
    }
    
    public String getName()
    {
        return this.itemName;
    }
    
    public ItemType getType()
    {
        return this.itemType;
    }
    
}