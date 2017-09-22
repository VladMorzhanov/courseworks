
import java.awt.*;
import java.applet.*;

class GraphCanvas extends Canvas {

    Color oldColor;

    Color newColor;

    int[] xArray;
    int[] yArray;

    public GraphCanvas() {

        newColor = new Color(0, 0, 255);

        oldColor = new Color(0, 255, 0);

        xArray = new int[0];

        yArray = new int[0];
    }

    public void paint(Graphics g) {
        Dimension size = size();

        if (GUI.proceedInvalidate) {
            // Draw axes; 
            g.drawLine(20, 20, 20, 350);
            g.drawLine(20, 350, 760, 350);
            g.drawString("Count", 25, 30);
            g.drawString("Days", 750, 346);

            // Draw a curve; 
            int nPoint = xArray.length;
            nPoint = xArray.length;
            g.setColor(newColor);

//            xArray[0] = 20;
//            xArray[1] = 120;
//            xArray[2] = 220;
//            xArray[3] = 320;
//            xArray[4] = 420;
//            
//            yArray[0] = 20;
//            yArray[1] = 20;
//            yArray[2] = 20;
//            yArray[3] = 20;
//            yArray[4] = 20;
            g.drawPolyline(yArray, xArray, nPoint);
            g.setColor(oldColor);
        }
    }

    public void setXArray(int[] array) {

        xArray = array;

        int max = 0;

        for (int i = 0; i < array.length; ++i) {
            max = array[i];

            if (i != 0) {
                if (max < array[i]) {
                    max = array[i];
                }
            }
        }

        int coef;
        if(max <= 0)
            coef = 0;
        else
            coef = max < 100 ? (100 / max) : (max / 100);

        for (int i = 0; i < array.length; ++i) {

            if (i == 0) {
                xArray[i] = 320;
            } else {
                xArray[i] = 320 - coef * array[i];
            }
        }
    }

    public void setYArray(int arraySize) {

        yArray = new int[arraySize];

        for (int i = 0; i < arraySize; ++i) {

            if (i == 0) {
                yArray[i] = 20;
            } else {
                yArray[i] = 20 + i * 50;
            }
        }
    }
}
