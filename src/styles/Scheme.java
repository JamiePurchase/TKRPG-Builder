package styles;

import app.Engine;
import gfx.Colour;
import java.awt.Color;

public class Scheme
{
    
    public static Color Colour(String colour)
    {
        if(getScheme().equals("DEFAULT"))
        {
            if(colour.equals("DESKTOP_FILL")) {return Colour.getColourRGB(153, 219, 181);}
            if(colour.equals("STANDARD_FADE")) {return Colour.getColourRGB(85, 107, 62);}
            if(colour.equals("STANDARD_BACK")) {return Colour.getColourRGB(196, 216, 150);}
            if(colour.equals("STANDARD_FORE")) {return Colour.getColourRGB(125, 157, 102);}
            if(colour.equals("SECONDARY_FORE")) {return Colour.getColourRGB(163, 200, 125);}
            if(colour.equals("TITLEBAR_CLOSE")) {return Colour.getColourRGB(157, 107, 82);}
            if(colour.equals("TITLEBAR_CLOSE_HOVER")) {return Colour.getColourRGB(173, 89, 65);}
        }
        if(getScheme().equals("MYSTIC"))
        {
            if(colour.equals("DESKTOP_FILL")) {return Colour.getColourRGB(138, 115, 136);}
            if(colour.equals("STANDARD_FADE")) {return Colour.getColourRGB(163, 73, 164);}
            if(colour.equals("STANDARD_BACK")) {return Colour.getColourRGB(181, 145, 165);}
            if(colour.equals("STANDARD_FORE")) {return Colour.getColourRGB(124, 79, 125);}
            if(colour.equals("SECONDARY_FORE")) {return Colour.getColourRGB(160, 86, 163);}
            if(colour.equals("TITLEBAR_CLOSE")) {return Colour.getColourRGB(157, 107, 82);}
            if(colour.equals("TITLEBAR_CLOSE_HOVER")) {return Colour.getColourRGB(173, 89, 65);}
        }
        return Colour.getColourRGB(0, 0, 0);
    }
    
    public static String getScheme()
    {
        // NOTE: check if value exists in schemes
        return Engine.getAppVariable("BUILDER_SCHEME").toString();
        //return "DEFAULT";
    }
    
}