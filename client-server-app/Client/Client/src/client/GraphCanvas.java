package client;

import java.awt.*;
import java.applet.*;

class GraphCanvas extends Canvas {

    Color oldColor;

    Color newColor;

    static int[] xArray;
    static int[] yArray;

    public GraphCanvas() {

        newColor = new Color(0, 0, 255);

        oldColor = new Color(0, 255, 0);
    }

    public void paint(Graphics g) {
        Dimension size = size();

        // Draw axes; 
        g.drawLine(20, 220, 20, 350);
        g.drawLine(20, 350, 360, 350);
        g.drawString("Y", 25, 230);
        g.drawString("X", 350, 346);

        // Draw a curve; 
        int nPoint = 10;
        g.setColor(newColor);
        g.drawPolyline(xArray, yArray, nPoint);
        g.setColor(oldColor);
        g.drawString("y = f(x)", 180, 267);
    }
    
    public void setXArray(int[] array){
        
        yArray = array;
        
        int max = 0;
        
        for(int i = 0; i <  array.length; ++i){
            max = array[i];
            
            if(i != 0){
                if(max < array[i]){
                    max = array[i];
                }
            }
        }
        
        int coef = max < 100 ? (100/max) : (max/100);
        
        for(int i = 0; i < array.length; ++i){
            yArray[i] = coef * array[i];
        }
    }
    
    public void setYArray(int arraySize){
        
        xArray = new int[arraySize];
        
        for(int i = 0; i < arraySize; ++i){
            xArray[i] = 300 - i*10;
        }
    }
}