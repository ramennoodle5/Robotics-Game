import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import java.awt.event.KeyListener;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main implements Runnable, KeyListener {
//sebby wuz here
    final int WIDTH = 500; //800
    final int HEIGHT = 500; //700
    public BufferStrategy bufferStrategy;
    public JFrame frame;
    public JPanel panel;
    public Canvas canvas;

    public Robot robot;
    public Blocks[] block;

    public static void main(String[] args) {

        Main ex = new Main();
        new Thread(ex).start();

    }

    public Main() {

        setUpGraphics();
        robot = new Robot(120, 120);
        block = new Blocks[10];
        for (int x=0;x<block.length;x++){
            int random = ((int) (Math.random() * 250))+125;
            int random2 = ((int) (Math.random() * 250))+125;
            block[x] = new Blocks(random,random2,"");
            block[x].isPickedUp=false;
        }

    }

    public void run() {

        while (true) {
            moveThings();
            renderGraphics();
//            checkIntersections();
            pause(20);
        }
    }

    public void moveThings() {
        if (robot.isThrusting) {
            robot.thrust();
        } //else {
            //robot.drift();
        //}
        if (robot.xpos>375) {
            if(robot.ythrust>0){
                robot.reorient(-5);
            }
            else if(robot.ythrust<0){
                robot.reorient(5);
            }
            robot.xpos -= 3;
        }
        if (robot.xpos<125) {
            if(robot.ythrust>0){
                robot.reorient(5);
            }
            else if(robot.ythrust<0){
                robot.reorient(-5);
            }
            robot.xpos += 3;
        }
        if (robot.ypos>375) {
            if(robot.xthrust>0){
                robot.reorient(5);
            }
            else if(robot.xthrust<0){
                robot.reorient(-5);
            }
            robot.ypos -= 3;
        }
        if(robot.ypos<125) {
            if(robot.xthrust>0){
                robot.reorient(-5);
            }
            else if(robot.xthrust<0){
                robot.reorient(5);
            }
            robot.ypos += 3;
        }

        if (robot.isRight) {
            robot.reorient(5);
        }
        if (robot.isLeft) {
            robot.reorient(-5);
        }
    }

    public void renderGraphics() {
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.fillRect(0, 0, frame.getWidth(), frame.getHeight());

        Graphics2D g2 = (Graphics2D) bufferStrategy.getDrawGraphics();

        Rectangle bot = new Rectangle(robot.xpos, robot.ypos,20,20);

        if (robot.isAlive) {

            g2.rotate(Math.toRadians(robot.angle), robot.xpos+robot.width/2,robot.ypos+robot.height/2);
            g2.setColor(Color.gray);
            g2.draw(bot);
            g2.fill(bot);


            g.setColor(Color.white);
            g.drawRect(125,125,250,250);

            g.setColor(Color.white);
            g.drawString(robot.angle + "°", 20, 20);

        }

        for (int x=0; x<block.length;x++){
            if (block[x].isPickedUp=false){
            g.drawRect(block[x].xpos, block[x].ypos,10,10);}
        }

        g.dispose();
        g2.dispose();

        bufferStrategy.show();

    }

    public void checkIntersections(){

        for (int x=0; x<block.length;x++){
            if (robot.rec.intersects(block[x].rec)){
                block[x].isPickedUp=true;
            }
        }

    }

    public void setUpGraphics() {
        frame = new JFrame("Robotics Simulator");   //Create the program window or frame.  Names it.

        panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
        panel.setLayout(null);   //set the layout

        // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
        // and trap input events
        canvas = new Canvas();
        canvas.setBounds(0, 0, WIDTH, HEIGHT);
        canvas.setIgnoreRepaint(true);


        panel.add(canvas);  // adds the canvas to the panel.

        // frame operations
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
        frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
        frame.setResizable(false);   //makes it so the frame cannot be resized
        frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!

        // sets up things so the screen displays images nicely.

        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        canvas.requestFocus();
        canvas.addKeyListener(this);

        frame.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent evt) {
//                Component c = (Component)evt.getSource();
                canvas.setBounds(0, 0, frame.getWidth(), frame.getHeight());
            }
        });

        System.out.println("Graphic Setup Finished");

    }

    //Pauses or sleeps the computer for the amount specified in milliseconds
    public void pause(int time) {
        //sleep
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
        }
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == 39) {
            robot.isRight = true;
        }
        if (key == 37) {
            robot.isLeft = true;
        }
        if (key == 38) {
            robot.isThrusting = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == 39) {
            robot.isRight = false;
        }
        if (key == 37) {
            robot.isLeft = false;
        }
        if (key == 38) {
            robot.isThrusting = false;
        }
    }
}
