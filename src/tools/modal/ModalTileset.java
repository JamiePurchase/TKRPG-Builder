package tools.modal;

import board.BoardTerrain;
import gfx.Drawing;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import tiles.TilesetFile;
import tools.ToolBoard;

public class ModalTileset extends Modal
{
    private ToolBoard tool;
    private TilesetFile tilesetFile;
    private int viewScroll = 0;
    
    public ModalTileset(ToolBoard tool, TilesetFile tileset)
    {
        super("Tileset", new Rectangle(433, 259, 500, 250), true);
        this.tool = tool;
        this.tilesetFile = tileset;
        this.viewScroll = 0;
    }
    
    private Rectangle getTileArea(int col, int row)
    {
        return new Rectangle(450 + (32 * col), 340 + (32 * row), 32, 32);
    }
    
    public void inputClick(MouseEvent e)
    {
        //
    }
    
    public BoardTerrain inputClickFile(MouseEvent e)
    {
        for(int row = 0; row < this.tilesetFile.getCountY(); row ++)
        {
            for(int col = 0; col < this.tilesetFile.getCountX(); col ++)
            {
                if(this.getTileArea(col, row).contains(e.getPoint()))
                {
                    return new BoardTerrain(tilesetFile, col, row);
                }
            }
        }
        return null;
    }
    
    public void inputKey(String key)
    {
        //
    }
    
    public void render(Graphics g)
    {
        this.renderModal(g);
        this.renderTiles(g);
    }
    
    private void renderTiles(Graphics g)
    {
        for(int row = 0; row < this.tilesetFile.getCountY(); row ++)
        {
            for(int col = 0; col < this.tilesetFile.getCountX(); col ++)
            {
                Drawing.drawImage(g, this.tilesetFile.getTileAt(col, row), this.getTileArea(col, row).x, this.getTileArea(col, row).y);
            }
        }
    }
    
    public void tick()
    {
        //
    }
    
}