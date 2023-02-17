import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;

public class grid extends Canvas implements Runnable{

    private int gridY = 0;
    private int gridX = 0;
    private String[] shipY;
    private String[] shipX;
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
        JFrame frame = new JFrame("Enkel grafik");
        frame.add(this);
        this.addMouseListener(new MyMouseListener());
        requestFocus();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        shipY = new String[17];
        shipX = new String[17];
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
        int direction = 0;
        while (true){
            direction = (int)(Math.random()*(4));
            if (direction ==1 && check5 ==0)
                if (lenght5X >= 5){
                    for (int z = 0; z<5; z++){
                        shipX[z] = String.valueOf((lenght5X-z));
                        shipY[z] = String.valueOf(lenght5Y);
                        check5 = 1;
                    }
                }
            else if (direction == 2 && check5 ==0) {
                    if (lenght5Y >= 5) {
                        for (int z = 0; z < 5; z++) {
                            shipY[z] = String.valueOf((lenght5Y - z));
                            shipX[z] = String.valueOf(lenght5X);
                            check5 = 1;
                        }
                    }
                } else if (direction == 3 && check5 ==0) {
                    if (lenght5X <= 6) {
                        for (int z = 0; z < 5; z++) {
                            shipX[z] = String.valueOf((lenght5X - z));
                            shipY[z] = String.valueOf(lenght5Y);
                            check5 = 1;
                        }
                    }

                }
            else if (direction == 4 && check5 ==0){
                    if (lenght5Y <= 6) {
                        for (int z = 0; z < 5; z++) {
                            shipY[z] = String.valueOf((lenght5Y - z));
                            shipX[z] = String.valueOf(lenght5X);
                            check5 = 1;
                        }
                    }
                }
            if (check5 ==1)
                break;


            if (check5 == 1 && check4 == 1 && check31 == 1 && check32 == 1 && check2 == 1) break;
        }
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
        g.setColor(new Color(0xFFFFFF));
        if (rectX != 0 && rectX <550 && rectY < 550) {
            for (int z = 0; z<17; z++)
                if (shipX[z] == String.valueOf(gridX) && shipY[z] == String.valueOf(gridY))
                    g.setColor(new Color(0xFF0000));
            g.fillRect(rectX, rectY, 40, 40);
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
