import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;

public class grid extends Canvas implements Runnable{

    private int gridY = 0;
    private int gridX = 0;
    private int[] shipY = new int[17];
    private int[] shipX = new int[17];
    // private int[][] a = new int[][]{{1,2},{1,2}};
    private int rectY = 0;
    private int rectX = 0;
    private int mouseX = 0;
    private int mouseY = 0;
    private int oldX = 0;
    private Thread thread;
    private boolean running = false;
    private BufferStrategy bs;
    public grid() {
        setSize(600,600);
        JFrame frame = new JFrame("Battleships");
        frame.add(this);
        this.addMouseListener(new MyMouseListener());
        this.requestFocusInWindow();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        int lenght5X = (int)(Math.random()*(10));
        int lenght5Y = (int)(Math.random()*(10));
        int lenght4X = (int)(Math.random()*(10));
        int lenght4Y = (int)(Math.random()*(10));
        int lenght3X1 = (int)(Math.random()*(10));
        int lenght3Y1 = (int)(Math.random()*(10));
        int lenght3X2 = (int)(Math.random()*(10));
        int lenght3Y2 = (int)(Math.random()*(10));
        int lenght2X = (int)(Math.random()*(10));
        int lenght2Y = (int)(Math.random()*(10));
        int check5 = 0;
        int check4 = 0;
        int check31 = 0;
        int check32 = 0;
        int check2 = 0;
        int notOverlap4 = 0;
        int notOverlap31 = 0;
        int notOverlap32 = 0;
        int notOverlap2 = 0;
        int direction = 0;
        while (true){
            direction = (int)(Math.random()*(4));
            if (direction ==1 && check5 ==0){
                if (lenght5X >= 5){
                    for (int z = 0; z<5; z++) {
                        shipX[z] = lenght5X - z;
                        shipY[z] = lenght5Y;
                        check5 = 1;
                    }
                    }
                }
            else if (direction == 2 && check5 ==0) {
                    if (lenght5Y >= 5) {
                        for (int z = 0; z < 5; z++) {
                            shipX[z] = lenght5X;
                            shipY[z] = lenght5Y - z;
                            check5 = 1;
                        }
                    }
                }
            else if (direction == 3 && check5 ==0) {
                    if (lenght5X <= 6) {
                        for (int z = 0; z < 5; z++) {
                            shipX[z] = lenght5X + z;
                            shipY[z] = lenght5Y;
                            check5 = 1;
                        }
                    }
                }
            else if (check5 != 1) {
                    if (lenght5Y <= 6) {
                        for (int z = 0; z < 5; z++) {
                            shipX[z] = lenght5X;
                            shipY[z] = lenght5Y + z;
                            check5 = 1;
                        }
                    }
                }

            // NEW SHIP

            notOverlap4 = 0;

            if (direction ==1 && check4 ==0 && check5 == 1) {
                if (lenght4X >= 4) {
                    for (int z = 0; z < 4; z++) {
                        shipX[z + 5] = lenght4X - z;
                        shipY[z + 5] = lenght4Y;
                        notOverlap4 = grid.legalShipPossition(notOverlap4, 5, shipX, shipY, shipX[z + 5], shipY[z + 5]);
                    }
                    if (notOverlap4 == 0)
                        check4 = 1;
                }
            }
                else if (direction == 2 && check4 ==0 && check5 == 1) {
                    if (lenght4Y >= 4) {
                        for (int z = 0; z < 4; z++) {
                            shipX[z+5] = lenght4X;
                            shipY[z+5] = lenght4Y - z;
                            notOverlap4 = grid.legalShipPossition(notOverlap4,5,shipX,shipY,shipX[z+5],shipY[z+5]);
                        }
                        if (notOverlap4 == 0)
                            check4 = 1;
                    }
                }
                else if (direction == 3 && check4 ==0 && check5 == 1) {
                    if (lenght4X <= 7) {
                        for (int z = 0; z < 4; z++) {
                            shipX[z+5] = lenght4X + z;
                            shipY[z+5] = lenght4Y;
                            notOverlap4 = grid.legalShipPossition(notOverlap4,5,shipX,shipY,shipX[z+5],shipY[z+5]);
                        }
                        if (notOverlap4 == 0)
                            check4 = 1;
                    }

                }
                else if (check4 != 1 && check5 == 1 ) {
                    lenght4Y = (int)(Math.random()*(10));
                    lenght4X = (int)(Math.random()*(10));
                    if (lenght4Y <= 7) {
                        for (int z = 0; z < 4; z++) {
                            shipX[z + 5] = lenght4X;
                            shipY[z + 5] = lenght4Y + z;
                            notOverlap4 = grid.legalShipPossition(notOverlap4,5,shipX,shipY,shipX[z+5],shipY[z+5]);
                        }
                        if (notOverlap4 == 0)
                            check4 = 1;
                    }
                }


            // NEW SHIP

            notOverlap31 = 0;

            if (direction ==1 && check4 == 1 && check5 == 1 && check31 ==0) {
                if (lenght3X1 >= 3) {
                    for (int z = 0; z < 3; z++) {
                        shipX[z + 9] = lenght3X1 - z;
                        shipY[z + 9] = lenght3Y1;
                        notOverlap31 = grid.legalShipPossition(notOverlap31, 9, shipX, shipY, shipX[z + 9], shipY[z + 9]);
                    }
                    if (notOverlap31 == 0)
                        check31 = 1;
                }
            }
                else if (direction == 2 && check4 == 1 && check5 == 1 && check31 ==0) {
                    if (lenght3Y1 >= 3) {
                        for (int z = 0; z < 3; z++) {
                            shipX[z+9] = lenght3X1;
                            shipY[z+9] = lenght3Y1 - z;
                            notOverlap31 = grid.legalShipPossition(notOverlap31,9,shipX,shipY,shipX[z+9],shipY[z+9]);
                        }
                        if (notOverlap31 == 0)
                            check31 = 1;
                    }
                } else if (direction == 3 && check4 == 1 && check5 == 1 && check31 ==0) {
                    if (lenght3X1 <= 8) {
                        for (int z = 0; z < 3; z++) {
                            shipX[z+9] = lenght3X1 + z;
                            shipY[z+9] = lenght3Y1;
                            notOverlap31 = grid.legalShipPossition(notOverlap31,9,shipX,shipY,shipX[z+9],shipY[z+9]);
                        }
                        if (notOverlap31 == 0)
                            check31 = 1;
                    }

                }
                else if (check4 == 1 && check5 == 1 && check31 !=1) {
                    lenght3Y1 = (int)(Math.random()*(10));
                    lenght3X1 = (int)(Math.random()*(10));
                    if (lenght3Y1 <= 8) {
                        for (int z = 0; z < 3; z++) {
                            shipX[z + 9] = lenght3X1;
                            shipY[z + 9] = lenght3Y1 + z;
                            notOverlap31 = grid.legalShipPossition(notOverlap31,9,shipX,shipY,shipX[z+9],shipY[z+9]);
                        }
                        if (notOverlap31 == 0)
                            check31 = 1;
                    }
                }

            if (check5 ==1 && check4 == 1 && check31 == 1)
                break;


            if (check5 == 1 && check4 == 1 && check31 == 1 && check32 == 1 && check2 == 1) break;
        }
        this.requestFocusInWindow();
    }

    public static int legalShipPossition(int overLapp, int antalExisterandeSkäppPungter, int[] shipX, int[] shipY, int skäppPositionX, int skäppPositionY){
        int fun = 0;
        int bugs = 0;
        for (int q = 0; q<= antalExisterandeSkäppPungter; q++){
            fun = 0;
            bugs = 0;
            if (shipX[q] == shipX[skäppPositionX])
                fun++;
            if (shipY[q] == shipY[skäppPositionY])
                bugs++;

            if (fun + bugs ==2)
                overLapp++;
        }
        return overLapp;

    }


  //  public void render() {
    //    bs = getBufferStrategy();
  //  if (bs == null) {
    //      createBufferStrategy(3);
    //        return;
    //    }
    //  Graphics g = bs.getDrawGraphics();

        // Rita ut den nya bilden
    //  draw(g);

    //  g.dispose();
    //  bs.show();
    // }

    public void paint(Graphics g) {
        drawGrid(50,50,g);
    }

    public void draw(Graphics g) {
        String control = "false";
        if (rectX != 0 && rectX <550 && rectY < 550) {
            for (int z = 0; z<17; z++)
                if (shipX[z] == gridX && shipY[z] == gridY) {
                    g.setColor(new Color(0xFF0000));
                    g.fillRect(rectX, rectY, 40, 40);
                    control = "true";
                }
            if (!control.equals("true")) {
                g.setColor(new Color(0xFFFFFF));
                g.fillRect(rectX, rectY, 40, 40);
            }
        }
    }

    public void drawGrid(int x, int y, Graphics g) {
        g.setColor(new Color(0x000000));
        int l = x;
        g.fillRect(x-20,y-20,530,530);
        g.setColor(new Color(0x3775DC));
        for (int j = 0; j<10;j++){
            for (int k = 0; k<10; k++){
                g.fillRect(x,y,40,40);
                x+= 50;
            }
            y+=50;
            x=l;
        }
    }
    private void update() {
        rectX = 0;
        rectY = 0;
        if (mouseX != oldX) {
            oldX = mouseX;
            mouseX = mouseX - 50;
            mouseY = mouseY - 50;
            while (mouseX>50){
                mouseX = mouseX-50;
                rectX++;
            }
            mouseX = oldX;
            while (mouseY>50){
                mouseY = mouseY - 50;
                rectY++;
            }
            gridX = rectX + 1;
            gridY = rectY + 1;
            rectX = rectX * 50 + 50;
            rectY = rectY * 50 + 50;

        }
    }



    public static void main(String[] args) {
        grid minGrafik = new grid();
        minGrafik.start();
    }

    public synchronized void start() {
        running = true;
        thread = new Thread(this);
        thread.start();
    }
    public synchronized void stop() {
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        double ns = 1000000000.0 / 25.0;
        double delta = 0;
        long lastTime = System.nanoTime();

        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1) {
                // Uppdatera koordinaterna
                update();
                // Rita ut bilden med updaterad data
                draw(getGraphics());
                delta--;
            }
        }
        stop();
    }

    public class MyMouseListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {


        }

        @Override
        public void mousePressed(MouseEvent e) {
            mouseY = e.getY();
            mouseX = e.getX();
        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

}
