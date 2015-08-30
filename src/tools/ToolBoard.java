package tools;

import app.Engine;
import board.BoardFile;
import board.BoardFile;
import board.BoardManager;
import board.BoardTerrain;
import gfx.Drawing;
import gfx.Text;
import items.ItemFile;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import states.StateBuilder;
import styles.Scheme;
import tools.board.ModalStats;
import tools.board.ToolEntity;
import tools.board.ToolLighting;
import tools.board.ToolMode;
import tools.board.ToolTerrain;
import tools.board.ToolZone;
import tools.files.FileBrowser;
import tools.modal.Modal;
import tools.modal.ModalBoardNew;
import tools.modal.ModalBoardSettings;
import tools.modal.ModalTileset;
import tools.toolbar.Toolbar;
import tools.toolbar.ToolbarItem;

public class ToolBoard extends Tool
{
    // Board File
    private boolean boardFileActive, boardFileSaved;
    private BoardFile boardFileObject;
    
    // Board Display
    private int boardOffsetX, boardOffsetY;
    
    // Options
    private ToolMode boardMode;
    private ToolTerrain optionPaintType;
    private BoardTerrain optionPaintTerrain;
    private ToolEntity optionEntityType;
    private ToolZone optionZoneType;
    private ToolLighting optionLightingType;
    private boolean optionViewGrid, optionViewRuler;
    
    // Settings Modal
    private boolean modalSettingsActive;
    private ModalStats modalSettingsObject;
    
    // Tileset Selection
    private boolean modalTilesetActive;
    private ModalTileset modalTilesetObject;
    
    // Toolbar
    private Toolbar toolbar;
    
    public ToolBoard(StateBuilder state)
    {
        super(state, "BRD", state.getToolNext(), "BOARD EDITOR", Drawing.getImage("icon/tool_board.png"));
        
        // Board File
        this.fileClose();
        
        // Board Offset
        this.boardOffsetX = 0;
        this.boardOffsetY = 0;
        
        // Mode
        this.boardMode = ToolMode.TERRAIN;
        this.optionPaintType = ToolTerrain.BRUSH;
        this.optionPaintTerrain = new BoardTerrain(this.getState().getManager().Tileset().loadTileset("TEST"), 2, 0);
        this.optionEntityType = ToolEntity.SELECT;
        this.optionZoneType = ToolZone.SELECT;
        this.optionLightingType = ToolLighting.SELECT;
        
        // Settings Modal
        this.modalSettingsActive = false;
        this.modalSettingsObject = new ModalStats(this);
        
        // Tileset Selection
        this.modalTilesetActive = false;
        this.modalTilesetObject = new ModalTileset(this, this.getState().getManager().Tileset().loadTileset("TEST"));
        
        // Toolbar
        this.toolbarInit();
    }
    
    public void fileClose()
    {
        this.boardFileActive = false;
        this.boardFileObject = null;
        this.boardFileSaved = true;
        this.updateTitle();
    }
    
    public void fileNew()
    {
        // NOTE: consider how this works
        this.boardFileActive = true;
        this.boardFileObject = null;
        this.boardFileSaved = false;
        this.updateTitle();
    }
    
    public void fileOpen(BoardFile file)
    {
        this.boardFileActive = true;
        this.boardFileObject = file;
        this.boardFileSaved = true;
        this.updateTitle();
        
        // NOTE: there are many toolbar items that should be disabled until a board file has been opened
    }
    
    private Rectangle getAreaBoard()
    {
        if(this.optionViewRuler)
        {
            return new Rectangle(this.getToolRectContent().x + 32, this.getToolRectContent().y + 32, this.getToolRectContent().width - 64, this.getToolRectContent().height - 64);
        }
        else {return new Rectangle(this.getToolRectContent().x, this.getToolRectContent().y, this.getToolRectContent().width - 32, this.getToolRectContent().height - 32);}
    }
    
    private Rectangle getAreaRuler(boolean horizontal)
    {
        if(horizontal) {return new Rectangle(this.getToolRectContent().x, getToolRectContent().y, getToolRectContent().width, 32);}
        return new Rectangle(this.getToolRectContent().x, getToolRectContent().y, 32, getToolRectContent().height);
    }
    
    private Rectangle getAreaScroll(boolean horizontal)
    {
        if(horizontal) {return new Rectangle(this.getToolRectContent().x, getToolRectContent().y + getToolRectContent().height - 32, getToolRectContent().width, 32);}
        return new Rectangle(this.getToolRectContent().x + this.getToolRectContent().width - 32, getToolRectContent().y, 32, getToolRectContent().height);
    }
    
    private Rectangle getAreaStatus()
    {
        return new Rectangle(this.getToolRectContent().x, this.getToolRectContent().y + this.getToolRectContent().height - 32, this.getToolRectContent().width, 32);
    }
    
    private Rectangle getAreaTools()
    {
        return new Rectangle();
    }
    
    public BoardFile getFile()
    {
        return this.boardFileObject;
    }
    
    public void inputClick(MouseEvent e)
    {
        // Settings Modal
        if(this.modalSettingsActive)
        {
            this.modalSettingsObject.inputClick(e);
            return;
        }
        
        // Tileset Modal
        if(this.modalTilesetActive)
        {
            BoardTerrain terrain = this.modalTilesetObject.inputClickFile(e);
            if(terrain != null)
            {
                this.modalTilesetActive = false;
                this.optionPaintTerrain = terrain;
                this.toolbar.getButton("TILE_SET").setImage(terrain.getImage());
            }
            return;
        }
        
        // Close Button
        if(this.getCloseRect().contains(e.getPoint())) {this.inputClickClose();}
        
        // Toolbar (should check if toolbar area contains e point)
        ToolbarItem click = this.toolbar.inputClick(e);
        if(click != null) {this.toolbarEvent(click);}
        
        // Scrollbar
        if(this.getAreaScroll(true).contains(e.getPoint()))
        {
            this.inputClickScrollH(e);
            return;
        }
        if(this.getAreaScroll(false).contains(e.getPoint()))
        {
            this.inputClickScrollV(e);
            return;
        }
        
        // Board
        if(this.getAreaBoard().contains(e.getPoint())) {this.inputClickBoard(e);}
    }
    
    private void inputClickBoard(MouseEvent e)
    {
        // Coordinates on Board Area
        int clickX = e.getX() - this.getAreaBoard().x;
        int clickY = e.getY() - this.getAreaBoard().y;
        
        // Calculate Tile X
        int tileX = 0;
        while(clickX > 32)
        {
            tileX ++;
            clickX -= 32;
        }
        
        // Calculate Tile Y
        int tileY = 0;
        while(clickY > 32)
        {
            tileY ++;
            clickY -= 32;
        }
        
        // Click on Board Tile
        this.inputClickBoardTile(tileX, tileY);
    }
    
    private void inputClickBoardTile(int tileX, int tileY)
    {
        if(this.boardMode == ToolMode.TERRAIN) {this.inputClickBoardTileTerrain(tileX, tileY);}
        if(this.boardMode == ToolMode.ENTITY) {this.inputClickBoardTileEntity(tileX, tileY);}
        if(this.boardMode == ToolMode.ZONE) {this.inputClickBoardTileZone(tileX, tileY);}
        if(this.boardMode == ToolMode.LIGHTING) {this.inputClickBoardTileLighting(tileX, tileY);}
    }
    
    private void inputClickBoardTileEntity(int tileX, int tileY)
    {
        //
    }
    
    private void inputClickBoardTileLighting(int tileX, int tileY)
    {
        //
    }
    
    private void inputClickBoardTileTerrain(int tileX, int tileY)
    {
        // Brush
        if(this.optionPaintType == ToolTerrain.BRUSH)
        {
            // Set board tile at position to current terrain
            this.boardFileObject.setTerrainAt(tileX, tileY, this.optionPaintTerrain);
        }
        
        // Fill
        if(this.optionPaintType == ToolTerrain.FILL)
        {
            // Set all connected same tiles to current terrain
        }
        
        // Delete
        if(this.optionPaintType == ToolTerrain.DELETE)
        {
            // Remove? Reset?
        }
        
        // Clone
        if(this.optionPaintType == ToolTerrain.CLONE)
        {
            // Set the current terrain to that of the target tile
        }
    }
    
    private void inputClickBoardTileZone(int tileX, int tileY)
    {
        //
    }
    
    private void inputClickScrollH(MouseEvent e)
    {
        // NOTE: may wish to combine these functions and require an h/v argument
    }
    
    private void inputClickScrollV(MouseEvent e)
    {
        //
    }
    
    public void inputKey(String key)
    {
        //
    }
    
    public void render(Graphics g)
    {
        this.renderWindow(g);
        this.toolbar.render(g);
        this.renderContent(g);
        if(this.modalSettingsActive)
        {
            //Drawing.fadeScreen(g, Scheme.Colour("DESKTOP_FILL"));
            this.modalSettingsObject.render(g);
        }
        if(this.modalTilesetActive) {this.renderModal(g, this.modalTilesetObject);}
    }
    
    private void renderContent(Graphics g)
    {
        // Background
        Drawing.fillRect(g, this.getAreaBoard(), "WHITE");
        
        // Ruler
        if(this.optionViewRuler) {this.renderContentRuler(g);}
        
        // Scrollbars
        this.renderContentScroll(g);
        
        // Board
        this.renderContentBoard(g);
        
        // Status
        this.renderStatus(g);
    }
    
    private void renderContentBoard(Graphics g)
    {
        // Terrain
        if(this.boardFileActive) {this.boardFileObject.render(g, this.getAreaBoard(), this.boardOffsetX, this.boardOffsetY, this.optionViewGrid);}
        
        // Border
        if(this.optionViewRuler) {Drawing.drawRect(g, this.getAreaBoard(), "BLACK");}
    }
    
    private void renderContentRuler(Graphics g)
    {
        Drawing.fillRect(g, this.getAreaRuler(true), Scheme.Colour("STANDARD_BACK"));
        Drawing.fillRect(g, this.getAreaRuler(false), Scheme.Colour("STANDARD_BACK"));
        Drawing.drawRect(g, this.getAreaRuler(true), "BLACK", false, true, true, false);
        Drawing.drawRect(g, this.getAreaRuler(false), "BLACK", false, true, true, false);
    }
    
    private void renderContentScroll(Graphics g)
    {
        Drawing.fillRect(g, this.getAreaScroll(true), Scheme.Colour("STANDARD_BACK"));
        Drawing.fillRect(g, this.getAreaScroll(false), Scheme.Colour("STANDARD_BACK"));
        Drawing.drawRect(g, this.getAreaScroll(true), "BLACK", true, true, true, false);
        Drawing.drawRect(g, this.getAreaScroll(false), "BLACK", false, true, true, true);
    }
    
    private void renderModal(Graphics g, Modal modal)
    {
        Drawing.fadeScreen(g, Scheme.Colour("STANDARD_FADE"));
        modal.render(g);
    }
    
    private void renderStatus(Graphics g)
    {
        // Fill
        Drawing.fillRect(g, this.getAreaStatus(), Scheme.Colour("SECONDARY_FORE"));
        
        // Content
        if(this.boardFileActive) {this.renderStatusFile(g);}
        else
        {
            Text.write(g, "BOARD EDITOR", this.getAreaStatus().x + 14, this.getAreaStatus().y + 22, "LEFT", "TOOLBAR", "BLACK");
        }
        
        // Border
        Drawing.drawLine(g, this.getAreaStatus().x, this.getAreaStatus().y, this.getAreaStatus().x + this.getAreaStatus().width, this.getAreaStatus().y, "BLACK");
    }
    
    private void renderStatusFile(Graphics g)
    {
        // File
        Text.write(g, "FILE", this.getAreaStatus().x + 14, this.getAreaStatus().y + 22, "LEFT", "TOOLBAR", "BLACK");
        Text.write(g, this.boardFileObject.getName(), this.getAreaStatus().x + 70, this.getAreaStatus().y + 22, "LEFT", "TOOLBAR_BOLD", "BLACK");
        
        // Position
        int[] position = this.renderStatusFilePosition();
        Text.write(g, "POSITION", this.getAreaStatus().x + 468, this.getAreaStatus().y + 22, "LEFT", "TOOLBAR", "BLACK");
        Text.write(g, "" + position[0], this.getAreaStatus().x + 595, this.getAreaStatus().y + 22, "RIGHT", "TOOLBAR_BOLD", "BLACK");
        Text.write(g, "" + position[1], this.getAreaStatus().x + 635, this.getAreaStatus().y + 22, "RIGHT", "TOOLBAR_BOLD", "BLACK");
        //this.inputScript.getCursorPosX()
        Drawing.drawLine(g, this.getAreaStatus().x + 454, this.getAreaStatus().y, this.getAreaStatus().x + 444, this.getAreaStatus().y + this.getAreaStatus().height, "BLACK");
        
        // Column
        //Text.write(g, "COLUMN", this.getAreaStatus().x + 624, this.getAreaStatus().y + 22, "LEFT", "TOOLBAR", "BLACK");
        //Text.write(g, "1", this.getAreaStatus().x + 700, this.getAreaStatus().y + 22, "RIGHT", "TOOLBAR_BOLD", "BLACK");
        //Text.write(g, "1", this.getAreaStatus().x + 764, this.getAreaStatus().y + 22, "RIGHT", "TOOLBAR_BOLD", "BLACK");
        //this.inputScript.getCursorPosY()
        //Drawing.drawLine(g, this.getAreaStatus().x + 610, this.getAreaStatus().y, this.getAreaStatus().x + 600, this.getAreaStatus().y + this.getAreaStatus().height, null);
        
        // Errors
        //Text.write(g, "ERROR COUNT", this.getAreaStatus().x + 820, this.getAreaStatus().y + 22, "LEFT", "TOOLBAR", "BLACK");
        //Text.write(g, "0", this.getAreaStatus().x + 1044, this.getAreaStatus().y + 22, "RIGHT", "TOOLBAR_BOLD", "BLACK");
        //Drawing.drawLine(g, this.getAreaStatus().x + 806, this.getAreaStatus().y, this.getAreaStatus().x + 796, this.getAreaStatus().y + this.getAreaStatus().height, null);
        
        // Updated
        Text.write(g, "LAST UPDATED", this.getAreaStatus().x + 1100, this.getAreaStatus().y + 22, "LEFT", "TOOLBAR", "BLACK");
        Text.write(g, this.boardFileObject.getFileDate().getDisplay("dd/MM/yyyy"), this.getAreaStatus().x + this.getAreaStatus().width - 15, this.getAreaStatus().y + 22, "RIGHT", "TOOLBAR_BOLD", "BLACK");
        Drawing.drawLine(g, this.getAreaStatus().x + 1086, this.getAreaStatus().y, this.getAreaStatus().x + 1076, this.getAreaStatus().y + this.getAreaStatus().height, "BLACK");
    }
    
    private int[] renderStatusFilePosition()
    {
        // Coordinates on Board Area
        int hoverX = Engine.getMousePoint().x - this.getAreaBoard().x;
        int hoverY = Engine.getMousePoint().y - this.getAreaBoard().y;
        
        // Calculate Tile X
        int tileX = 0;
        while(hoverX > 32)
        {
            tileX ++;
            hoverX -= 32;
        }
        
        // Calculate Tile Y
        int tileY = 0;
        while(hoverY > 32)
        {
            tileY ++;
            hoverY -= 32;
        }
        
        // Hover on Board Tile
        int[] result = new int[2];
        result[0] = tileX;
        result[1] = tileY;
        return result;
    }
    
    public void tick()
    {
        //
    }
    
    private void toolbarEvent(ToolbarItem item)
    {
        if(item.getRef().equals("FILE_NEW"))
        {
            // NOTE: need to create separate modals / filebrowser modal
            //this.setModal(new ModalBoardNew());
        }
        if(item.getRef().equals("FILE_OPEN"))
        {
            // NOTE: open the filebrowser modal
            
            // TEMP
            //this.modal(new FileBrowser("OPEN BOARD", this.getState().managerBoard.getBoardArray()));
            this.fileOpen(this.getState().getManager().Board().loadBoard("TEST"));
        }
        if(item.getRef().equals("FILE_SAVE"))
        {
            System.out.println("TOOL BOARD -> SAVE");
            this.boardFileObject.update();
            this.boardFileObject.save();
        }
        if(item.getRef().equals("FILE_SAVE_AS"))
        {
            //
        }
        if(item.getRef().equals("OPTIONS_STATS"))
        {
            if(this.boardFileActive)
            {
                this.modalSettingsActive = true;
                //this.modalSettingsObject = new ModalStats(this);
                this.modalSettingsObject.loadValues();
                return;
            }
        }
        if(item.getRef().equals("OPTIONS_ENEMY"))
        {
            //
        }
        if(item.getRef().equals("OPTIONS_AUDIO"))
        {
            //
        }
        if(item.getRef().equals("VIEW_GRID"))
        {
            if(this.optionViewGrid)
            {
                this.optionViewGrid = false;
                this.toolbar.getButton("VIEW_GRID").setDisable(true);
            }
            else
            {
                this.optionViewGrid = true;
                this.toolbar.getButton("VIEW_GRID").setDisable(false);
            }
        }
        if(item.getRef().equals("VIEW_RULER"))
        {
            if(this.optionViewRuler)
            {
                this.optionViewRuler = false;
                this.toolbar.getButton("VIEW_RULER").setDisable(true);
            }
            else
            {
                this.optionViewRuler = true;
                this.toolbar.getButton("VIEW_RULER").setDisable(false);
            }
        }
        if(item.getRef().equals("MODE_PAINT"))
        {
            this.boardMode = ToolMode.TERRAIN;
            this.toolbarInit();
            this.toolbar.getButton("MODE_PAINT").setDisable(false);
            this.toolbar.getButton("MODE_ENTITIES").setDisable(true);
            this.toolbar.getButton("MODE_ZONES").setDisable(true);
            this.toolbar.getButton("MODE_LIGHTING").setDisable(true);
        }
        if(item.getRef().equals("MODE_ENTITIES"))
        {
            this.boardMode = ToolMode.ENTITY;
            this.toolbarInit();
            this.toolbar.getButton("MODE_PAINT").setDisable(true);
            this.toolbar.getButton("MODE_ENTITIES").setDisable(false);
            this.toolbar.getButton("MODE_ZONES").setDisable(true);
            this.toolbar.getButton("MODE_LIGHTING").setDisable(true);
        }
        if(item.getRef().equals("MODE_ZONES"))
        {
            this.boardMode = ToolMode.ZONE;
            this.toolbarInit();
            this.toolbar.getButton("MODE_PAINT").setDisable(true);
            this.toolbar.getButton("MODE_ENTITIES").setDisable(true);
            this.toolbar.getButton("MODE_ZONES").setDisable(false);
            this.toolbar.getButton("MODE_LIGHTING").setDisable(true);
        }
        if(item.getRef().equals("MODE_LIGHTING"))
        {
            this.boardMode = ToolMode.LIGHTING;
            this.toolbarInit();
            this.toolbar.getButton("MODE_PAINT").setDisable(true);
            this.toolbar.getButton("MODE_ENTITIES").setDisable(true);
            this.toolbar.getButton("MODE_ZONES").setDisable(true);
            this.toolbar.getButton("MODE_LIGHTING").setDisable(false);
        }
        if(item.getRef().equals("PAINT_BRUSH"))
        {
            this.optionPaintType = ToolTerrain.BRUSH;
            this.toolbar.getButton("PAINT_BRUSH").setDisable(false);
            this.toolbar.getButton("PAINT_FILL").setDisable(true);
            this.toolbar.getButton("PAINT_DELETE").setDisable(true);
            this.toolbar.getButton("PAINT_CLONE").setDisable(true);
        }
        if(item.getRef().equals("PAINT_FILL"))
        {
            this.optionPaintType = ToolTerrain.FILL;
            this.toolbar.getButton("PAINT_BRUSH").setDisable(true);
            this.toolbar.getButton("PAINT_FILL").setDisable(false);
            this.toolbar.getButton("PAINT_DELETE").setDisable(true);
            this.toolbar.getButton("PAINT_CLONE").setDisable(true);
        }
        if(item.getRef().equals("PAINT_DELETE"))
        {
            this.optionPaintType = ToolTerrain.DELETE;
            this.toolbar.getButton("PAINT_BRUSH").setDisable(true);
            this.toolbar.getButton("PAINT_FILL").setDisable(true);
            this.toolbar.getButton("PAINT_DELETE").setDisable(false);
            this.toolbar.getButton("PAINT_CLONE").setDisable(true);
        }
        if(item.getRef().equals("PAINT_CLONE"))
        {
            this.optionPaintType = ToolTerrain.CLONE;
            this.toolbar.getButton("PAINT_BRUSH").setDisable(true);
            this.toolbar.getButton("PAINT_FILL").setDisable(true);
            this.toolbar.getButton("PAINT_DELETE").setDisable(true);
            this.toolbar.getButton("PAINT_CLONE").setDisable(false);
        }
        if(item.getRef().equals("TILE_SET"))
        {
            this.modalTilesetActive = true;
        }
        if(item.getRef().equals("ENTITY_SELECT"))
        {
            this.optionEntityType = ToolEntity.SELECT;
            this.toolbar.getButton("ENTITY_SELECT").setDisable(false);
            this.toolbar.getButton("ENTITY_ADD").setDisable(true);
            this.toolbar.getButton("ENTITY_DELETE").setDisable(true);
        }
        if(item.getRef().equals("ENTITY_ADD"))
        {
            this.optionEntityType = ToolEntity.ADD;
            this.toolbar.getButton("ENTITY_SELECT").setDisable(true);
            this.toolbar.getButton("ENTITY_ADD").setDisable(false);
            this.toolbar.getButton("ENTITY_DELETE").setDisable(true);
        }
        if(item.getRef().equals("ENTITY_DELETE"))
        {
            this.optionEntityType = ToolEntity.DELETE;
            this.toolbar.getButton("ENTITY_SELECT").setDisable(true);
            this.toolbar.getButton("ENTITY_ADD").setDisable(true);
            this.toolbar.getButton("ENTITY_DELETE").setDisable(false);
        }
        if(item.getRef().equals("ZONE_SELECT"))
        {
            this.optionZoneType = ToolZone.SELECT;
            this.toolbar.getButton("ZONE_SELECT").setDisable(false);
            this.toolbar.getButton("ZONE_DRAW").setDisable(true);
            this.toolbar.getButton("ZONE_DELETE").setDisable(true);
        }
        if(item.getRef().equals("ZONE_DRAW"))
        {
            this.optionZoneType = ToolZone.DRAW;
            this.toolbar.getButton("ZONE_SELECT").setDisable(true);
            this.toolbar.getButton("ZONE_DRAW").setDisable(false);
            this.toolbar.getButton("ZONE_DELETE").setDisable(true);
        }
        if(item.getRef().equals("ZONE_DELETE"))
        {
            this.optionZoneType = ToolZone.DELETE;
            this.toolbar.getButton("ZONE_SELECT").setDisable(true);
            this.toolbar.getButton("ZONE_DRAW").setDisable(true);
            this.toolbar.getButton("ZONE_DELETE").setDisable(false);
        }
        if(item.getRef().equals("LIGHTING_SELECT"))
        {
            this.optionLightingType = ToolLighting.SELECT;
            this.toolbar.getButton("LIGHTING_SELECT").setDisable(false);
            this.toolbar.getButton("LIGHTING_ADD").setDisable(true);
            this.toolbar.getButton("LIGHTING_DELETE").setDisable(true);
        }
        if(item.getRef().equals("LIGHTING_ADD"))
        {
            this.optionLightingType = ToolLighting.ADD;
            this.toolbar.getButton("LIGHTING_SELECT").setDisable(true);
            this.toolbar.getButton("LIGHTING_ADD").setDisable(false);
            this.toolbar.getButton("LIGHTING_DELETE").setDisable(true);
        }
        if(item.getRef().equals("LIGHTING_DELETE"))
        {
            this.optionLightingType = ToolLighting.DELETE;
            this.toolbar.getButton("LIGHTING_SELECT").setDisable(true);
            this.toolbar.getButton("LIGHTING_ADD").setDisable(true);
            this.toolbar.getButton("LIGHTING_DELETE").setDisable(false);
        }
    }
    
    private void toolbarInit()
    {
        this.toolbar = new Toolbar(new Rectangle(0, 34, 1366, 42));
        this.toolbar.addLabel("BOARD");
        this.toolbar.addButton("FILE_NEW", "file_new");
        this.toolbar.addButton("FILE_OPEN", "file_open");
        this.toolbar.addButton("FILE_SAVE", "file_save");
        this.toolbar.addButton("FILE_SAVE_AS", "file_saveAs");
        this.toolbar.addDivider();
        this.toolbar.addLabel("CONFIG");
        this.toolbar.addButton("OPTIONS_STATS", "board_stats");
        this.toolbar.addButton("OPTIONS_ENEMY", "board_enemy");
        this.toolbar.addButton("OPTIONS_AUDIO", "board_audio");
        this.toolbar.addDivider();
        this.toolbar.addLabel("VIEW");
        this.toolbar.addButton("VIEW_GRID", "board_grid", true);
        this.toolbar.addButton("VIEW_RULER", "board_ruler", true);
        this.toolbar.addDivider();
        this.toolbar.addLabel("MODE");
        this.toolbar.addButton("MODE_PAINT", "board_paint", false);
        this.toolbar.addButton("MODE_ENTITIES", "board_entity", true);
        this.toolbar.addButton("MODE_ZONES", "board_zone", true);
        this.toolbar.addButton("MODE_LIGHTING", "board_light", true);
        
        // Mode Specific Items
        if(this.boardMode == ToolMode.TERRAIN) {this.toolbarInitPaint();}
        if(this.boardMode == ToolMode.ENTITY) {this.toolbarInitEntities();}
        if(this.boardMode == ToolMode.ZONE) {this.toolbarInitZones();}
        if(this.boardMode == ToolMode.LIGHTING) {this.toolbarInitLighting();}
    }
    
    private void toolbarInitEntities()
    {
        this.toolbar.addDivider();
        this.toolbar.addLabel("ENTITY");
        this.toolbar.addButton("ENTITY_SELECT", "board_entity_select");
        this.toolbar.addButton("ENTITY_ADD", "board_entity_create", true);
        this.toolbar.addButton("ENTITY_DELETE", "board_entity_delete", true);
    }
    
    private void toolbarInitLighting()
    {
        this.toolbar.addDivider();
        this.toolbar.addLabel("LIGHTING");
        this.toolbar.addButton("LIGHTING_SELECT", "board_light_select");
        this.toolbar.addButton("LIGHTING_ADD", "board_light_create", true);
        this.toolbar.addButton("LIGHTING_DELETE", "board_light_delete", true);
    }
    
    private void toolbarInitPaint()
    {
        this.toolbar.addDivider();
        this.toolbar.addLabel("PAINT");
        this.toolbar.addButton("PAINT_BRUSH", "board_brush");
        this.toolbar.addButton("PAINT_FILL", "board_fill", true);
        this.toolbar.addButton("PAINT_DELETE", "board_delete", true);
        this.toolbar.addButton("PAINT_CLONE", "board_clone", true);
        this.toolbar.addDivider();
        this.toolbar.addLabel("TILE");
        this.toolbar.addButton("TILE_SET", "board_tile", true);
        
        // NOTE: when the mode changes, the toolbar has the default paint option selected
        // HOWEVER the editor option for the terrain brush may be different
    }
    
    private void toolbarInitZones()
    {
        this.toolbar.addDivider();
        this.toolbar.addLabel("ZONE");
        this.toolbar.addButton("ZONE_SELECT", "board_zone_select");
        this.toolbar.addButton("ZONE_DRAW", "board_zone_create", true);
        this.toolbar.addButton("ZONE_DELETE", "board_zone_delete", true);
    }
    
    public void setModalSettings(boolean value)
    {
        this.modalSettingsActive = value;
    }
    
    public void setOptionViewGrid(boolean value)
    {
        this.optionViewGrid = value;
    }
    
    public void setOptionViewRuler(boolean value)
    {
        this.optionViewRuler = value;
    }
    
    public void setPaint(ToolTerrain paint)
    {
        this.optionPaintType = paint;
    }
    
    private void updateTitle()
    {
        String title = "BOARD EDITOR";
        if(this.boardFileActive) {title += " - " + this.boardFileObject.getName();}
        if(!this.boardFileSaved) {title += " *";}
        this.setTitle(title);
    }
    
}