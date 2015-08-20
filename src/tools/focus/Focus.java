package tools.focus;

public abstract class Focus
{
    private boolean focusActive;
    
    public Focus(boolean active)
    {
        this.focusActive = active;
    }
    
    public void focus(boolean active)
    {
        this.focusActive = active;
    }
    
    public boolean hasFocus()
    {
        return this.focusActive;
    }
    
    public void inputKey(String key)
    {
        System.out.println("FOCUS KEY: " + key);
    }
    
}