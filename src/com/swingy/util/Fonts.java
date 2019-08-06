package com.swingy.util;

import com.swingy.view.Swingy;

import java.awt.*;

public class Fonts {

    public static void drawString(Graphics graphics, Font font, Color color, String text, int x, int y){
        graphics.setColor(color);
        graphics.setFont(font);
        graphics.drawString(text, x, y);
    }

    public static void drawString(Graphics graphics, Font font, Color color, String text){
        FontMetrics fontMetrics = graphics.getFontMetrics(font);

        int x = (Swingy.WIDTH - fontMetrics.stringWidth(text)) / 2; //Horizontal Center
        int y = ((Swingy.HEIGHT - fontMetrics.getHeight()) / 2) + fontMetrics.getAscent(); //Vertical Center

        drawString(graphics, font, color, text, x, y);
    }

    public static void drawString(Graphics graphics, Font font, Color color, String text, int size, boolean xY){
        FontMetrics fontMetrics = graphics.getFontMetrics(font);
        int x;
        int y;
        if (xY) {
            x = size;
            y = ((Swingy.HEIGHT - fontMetrics.getHeight()) / 2) + fontMetrics.getAscent(); //Vertical
        }
        else {
            x = (Swingy.WIDTH - fontMetrics.stringWidth(text)) / 2; //Horizontal Center
            y = size;
        }
        drawString(graphics, font, color, text, x, y);
    }
}
