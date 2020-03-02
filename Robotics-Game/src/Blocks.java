import java.awt.*;

public class Blocks {

    public int xpos, ypos, dx, dy, width, height;
    String color;
    Boolean isDoubled, isPickedUp;
    public Rectangle rec;

    public Blocks(int pXpos, int pYpos, String Color) {
        xpos = pXpos;
        ypos = pYpos;
        height = 10;
        width = 10;
        color = Color;
        isPickedUp=false;
        rec = new Rectangle(xpos, ypos, width, height);
    }
}
