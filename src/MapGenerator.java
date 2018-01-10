import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static java.lang.Math.abs;

public class MapGenerator extends JFrame {

    public MapGenerator (){
        super("Map generator");

        setContentPane(new DrawPane());
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setInitialSize();
        setBackground(Color.BLACK);
        setVisible(true);
    }

    private void setInitialSize() {
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(1000,800);

        setLocation(0, 0);
    }
}

class DrawPane extends JPanel {

    public double robotPositionX = 450;
    public double robotPositionY = 450;
    JButton button = new JButton("Save to file");
    public DrawPane(){
        JPanel panel2 = new JPanel();
        JScrollPane scrollPane = new JScrollPane(panel2);
        scrollPane.setViewportView(panel2);
        add(panel2);
        panel2.add(button);
        add(panel2);
        panel2.setSize(1000,1000);
        setBackground(Color.BLACK);
    }
    public void paintComponent(Graphics g) {
        try {
            g.setColor(Color.WHITE);
            drawPoints(g);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void drawPoints (Graphics g) throws IOException {
        super.paintComponent(g);
        String fileName = "src/coorinates/test.txt";
        BufferedReader file = new BufferedReader(new FileReader(fileName));
        String c2 = file.readLine();
        Graphics2D g2 = (Graphics2D) g;
        try{
            while ((c2 = file.readLine()) != null) {
                c2 = file.readLine();
                String[] arr = c2.split(" ");
                Ellipse2D point = new Ellipse2D.Double(abs((Double.parseDouble(arr[0]) + robotPositionX)/5), (abs(Double.parseDouble(arr[1]) + robotPositionY)/5), 2, 2);
                g2.fill(point);
            }

            g.setColor(Color.BLUE);
            g.fillOval(500,500,10,10);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}