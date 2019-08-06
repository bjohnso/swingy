package com.swingy.rendering.ui;

import com.swingy.util.Fonts;
import com.swingy.view.Swingy;

import java.awt.*;

public class Button extends Rectangle {

    private Font font, selectedFont;
    private Color color, selectedColor;
    private boolean selected;
    private String text;
    private int textY;

    public Button(String text, int textY, Font font, Font selectedFont, Color color, Color selectedColor){
        this.text = text;
        this.textY = textY;
        this.font = font;
        this.selectedFont = selectedFont;
        this.color = color;
        this.selectedColor = selectedColor;
    }

    public void setSelected(boolean selected){
        this.selected = selected;
    }

    public void render(Graphics graphics){
        if (selected)
            Fonts.drawString(graphics, selectedFont, selectedColor, text, textY, false);
        else
            Fonts.drawString(graphics, font, color, text, textY, false);

        FontMetrics fontMetrics = graphics.getFontMetrics();
        this.x = (Swingy.WIDTH - fontMetrics.stringWidth(text)) / 2; //Horizontal Center
        this.y = textY - fontMetrics.getHeight();
        this.width = fontMetrics.stringWidth(text);
        this.height = fontMetrics.getHeight();
        graphics.drawRect(x, y, width, height);
    }

}
