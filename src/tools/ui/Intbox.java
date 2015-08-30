package tools.ui;

import app.Engine;
import debug.Console;
import gfx.Drawing;
import gfx.Fonts;
import gfx.Text;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import styles.Scheme;

public class Intbox extends Textbox
{
    private int valueMin, valueMax;
    
    public Intbox(String ref, Rectangle area, int value, int min, int max)
    {
        super(ref, area, "" + value, 0);
        this.valueMin = min;
        this.valueMax = max;
    }
    
    public void arrow1()
    {
        int newValue = Integer.parseInt(this.getValue()) + 1;
        if(newValue > this.valueMax) {newValue = this.valueMax;}
        this.setValue("" + newValue);
    }
    
    public void arrow2()
    {
        int newValue = Integer.parseInt(this.getValue()) - 1;
        if(newValue < this.valueMin) {newValue = this.valueMin;}
        this.setValue("" + newValue);
    }
    
    public Rectangle getAreaArrow1()
    {
        return new Rectangle(this.getArea().x + this.getArea().width - (this.getArea().height * 2), this.getArea().y, this.getArea().height, this.getArea().height);
    }
    
    public Rectangle getAreaArrow2()
    {
        return new Rectangle(this.getArea().x + this.getArea().width - this.getArea().height, this.getArea().y, this.getArea().height, this.getArea().height);
    }
    
    public void inputKey(String key)
    {
        // NOTE: check if numeric
    }
    
    public void render(Graphics g)
    {
        super.render(g);
        this.renderArrows(g);
    }
    
    private void renderArrows(Graphics g)
    {
        // NOTE: arrows at the edge of the textbox
        if(this.getAreaArrow1().contains(Engine.getMousePoint())) {Drawing.fillRect(g, this.getAreaArrow1(), Scheme.Colour("STANDARD_BACK"));}
        else {Drawing.fillRect(g, this.getAreaArrow1(), Scheme.Colour("SECONDARY_FORE"));}
        if(this.getAreaArrow2().contains(Engine.getMousePoint())) {Drawing.fillRect(g, this.getAreaArrow2(), Scheme.Colour("STANDARD_BACK"));}
        else {Drawing.fillRect(g, this.getAreaArrow2(), Scheme.Colour("SECONDARY_FORE"));}
        Text.write(g, "+", this.getAreaArrow1().x + (this.getAreaArrow1().width / 2), this.getAreaArrow1().y + 17, "CENTER", Fonts.getFont("STANDARD"), Color.BLACK);
        Text.write(g, "-", this.getAreaArrow2().x + (this.getAreaArrow2().width / 2), this.getAreaArrow2().y + 17, "CENTER", Fonts.getFont("STANDARD"), Color.BLACK);
        Drawing.drawRect(g, this.getAreaArrow1(), Color.BLACK);
        Drawing.drawRect(g, this.getAreaArrow2(), Color.BLACK);
    }
    
    public int getValueInt()
    {
        return Integer.parseInt(this.getValue());
    }
    
}