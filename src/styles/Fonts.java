package styles;

import java.awt.Font;

public class Fonts
{
    
    public static void loadFonts()
    {
        gfx.Fonts.addFont("CLOCK_DATE", new Font("Courier New", Font.PLAIN, 16));
        gfx.Fonts.addFont("CLOCK_TIME", new Font("Courier New", Font.PLAIN, 18));
        gfx.Fonts.addFont("FILE_BROWSE", new Font("Courier New", Font.PLAIN, 26));
        gfx.Fonts.addFont("INFOBAR", new Font("Times New Roman", Font.PLAIN, 14));
        gfx.Fonts.addFont("STANDARD", new Font("Segoe Print", Font.PLAIN, 14));
        gfx.Fonts.addFont("TASKBAR_MENU", new Font("Times New Roman", Font.PLAIN, 36));
        gfx.Fonts.addFont("TASKBAR_MENU_ITEM", new Font("Times New Roman", Font.PLAIN, 22));
        gfx.Fonts.addFont("TITLEBAR", new Font("Times New Roman", Font.PLAIN, 22));
        gfx.Fonts.addFont("TOOLBAR", new Font("Times New Roman", Font.ITALIC, 20));
    }
    
}