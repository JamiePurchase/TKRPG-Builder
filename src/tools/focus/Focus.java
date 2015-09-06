package tools.focus;

import debug.Console;

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
        Console.print("FOCUS KEY: " + key);
    }
    
}