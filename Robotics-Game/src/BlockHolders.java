import java.awt.*;
public class BlockHolders {

    public int xpos, ypos, width, height;
    String color;
    public Rectangle rec;

    public BlockHolders(int pXpos, int pYpos) {
        xpos = pXpos;
        ypos = pYpos;
        height = 10;
        width = 10;
        rec = new Rectangle(xpos, ypos, width, height);
    }
}


