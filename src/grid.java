import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;

public class grid extends Canvas implements Runnable{

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
    }


    public void render() {
        bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();

        // Rita ut den nya bilden
        draw(g);

        g.dispose();
        bs.show();
    }

    public void paint(Graphics g) {
        drawGrid(50,50,g);
    }

    public void draw(Graphics g) {
        drawGrid (50,50,g);
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
        g.drawRect(mouseX,mouseY,60,60);


    }
    private void update() {
        int y1 = 0;
        int x1 = 0;
        if (mouseX != oldX) {
            oldX = mouseX;
            mouseX = mouseX - 50;
            mouseY = mouseY - 50;
            while (mouseX>40){
                mouseX = mouseX-50;
                x1++;
            }
            mouseX = oldX;
            while (mouseY>40){
                mouseY = mouseY - 50;
                y1++;
            }


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
                render();
                getGraphics().drawRect(mouseX,mouseY,60,60);
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
            mouseY = getY();
            mouseX = getX();
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
