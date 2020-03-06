import java.awt.*;

public class Blocks {

    public int xpos, ypos, dx, dy, width, height;
    Boolean isDoubled, isPickedUp;
    public Rectangle rec;

    public Blocks(int pXpos, int pYpos) {
        xpos = pXpos;
        ypos = pYpos;
        height = 10;
        width = 10;
        isPickedUp=false;
        rec = new Rectangle(xpos, ypos, width, height);
    }

}
