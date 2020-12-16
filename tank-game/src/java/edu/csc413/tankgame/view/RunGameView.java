package edu.csc413.tankgame.view;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
//if x is smaller than
/**
 * RunGameView is the view representing the screen shown when the game is actually playing. As a result, RunGameView is
 * responsible for tracking what is being drawn on the screen in the form of DrawableEntities. The GameDriver class
 * should interact with RunGameView by asking it to draw a specific entity, named by its ID, at a location and angle.
 */
//tanks have to stop
public class RunGameView extends JPanel {
    public static final Dimension SCREEN_DIMENSIONS = new Dimension(1024,
            768);

    public static final String PLAYER_TANK_IMAGE_FILE = "player-tank.png";
    public static final double PLAYER_TANK_INITIAL_X = 300.0;  //INITIAL X&Y FOR TANKS AND ANGLE
    public static final double PLAYER_TANK_INITIAL_Y = 350.0;
    public static final double PLAYER_TANK_INITIAL_ANGLE = Math.toRadians(45.0);

    public static final String AI_TANK_IMAGE_FILE = "ai-tank.png";
    public static final double AI_TANK_INITIAL_X = 500.0;
    public static final double AI_TANK_INITIAL_Y = 200.0;
    public static final double AI_TANK_INITIAL_ANGLE = Math.toRadians(2.0);

    // TODO: Feel free to add more constants if you would like multiple AI tanks -- just be sure to set them at
    // different initial locations.

    public static final String SHELL_IMAGE_FILE = "shell.png";

    private final BufferedImage worldImage;
    //DRAWABLEENTITY, ANYTHING THAT HAS AN IMAGE TANKS SHELLS, THEY ALL HAVE A UNIQUE STRING ID
    //GIVEN IN GAMESTATE  ex.("player-tank", "ai-tank") FROM SHELL ex("shell-<uniqueID>")
   //map<string-ID, IMAGE>
    private final Map<String, DrawableEntity> drawableEntitiesById = new HashMap<>();

    public RunGameView() {
        worldImage = new BufferedImage(SCREEN_DIMENSIONS.width, SCREEN_DIMENSIONS.height, BufferedImage.TYPE_INT_RGB);
        setBackground(Color.BLACK);
    }

    /** Clears the DrawableEntities map. This should be invoked if the game is reset. */
    public void reset() {
        drawableEntitiesById.clear();
    }

    /**
     * Adds a new DrawableEntity to the view. The DrawableEntity must be identified by an ID, so that it can be updated
     * later on by that same ID. It must be initialized with an image file name, as well as an initial location and
     * angle.
     */
//    addDrawable(STRING-ID, NAME-FILE-IMAGE, IMITIAL LOCATION, ANGLE)
    public void addDrawableEntity(
            String id, String entityImageFile, double initialX, double initialY, double initialAngle) {

        DrawableEntity drawableEntity = new DrawableEntity(entityImageFile);
        drawableEntity.setLocationAndAngle(initialX, initialY, initialAngle);
        drawableEntitiesById.put(id, drawableEntity);
    }

//Nov 30 1st due dates
    //Tanks moving
    //ai tank moving
    //shoot
    //start end game buttons

    /**
     * Removes the DrawableEntity identified by the provided ID from this view. This should be invoked if an entity has
     * been removed from the game and should no longer be drawn.
     */
    public void removeDrawableEntity(String id) {
        drawableEntitiesById.remove(id);
    }

    /** Updates the DrawableEntity with the provided ID to a new location and angle. */
    //changes an images location no need for image-file
    public void setDrawableEntityLocationAndAngle(String id, double x, double y, double angle) {
        drawableEntitiesById.get(id).setLocationAndAngle(x, y, angle);
    }

    @Override
    //black grid of game
    public void paintComponent(Graphics g) {
        // The "image" on which we draw the game screen is just a buffer colored entirely black. We can then draw the
        // DrawableEntities on top of the buffer.
        Graphics2D buffer = worldImage.createGraphics();
        buffer.setColor(Color.BLACK);
        buffer.fillRect(0, 0, SCREEN_DIMENSIONS.width, SCREEN_DIMENSIONS.height);

        //sets images to correct location and rotation
        for (DrawableEntity drawableEntity : drawableEntitiesById.values()) {
            buffer.drawImage(drawableEntity.getEntityImage(), drawableEntity.getAffineTransform(), null);
        }
//draws the grid
        g.drawImage(worldImage, 0, 0, null);
    }
}
