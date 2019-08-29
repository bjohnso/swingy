package com.swingy.rendering.ui;

import com.swingy.util.Fonts;

import java.awt.*;

public class Button extends Rectangle {

    private Font font, selectedFont;
    private Color color, selectedColor;
    private boolean selected;
    private String text;
    private int textY;
    private int textX = -1;

    public Button(String text, int textY, Font font, Font selectedFont, Color color, Color selectedColor){
        this.text = text;
        this.textY = textY;
        this.font = font;
        this.selectedFont = selectedFont;
        this.color = color;
        this.selectedColor = selectedColor;
    }

    public Button(String text, int textX, int textY, Font font, Font selectedFont, Color color, Color selectedColor){
        this.text = text;
        this.textY = textY;
        this.font = font;
        this.selectedFont = selectedFont;
        this.color = color;
        this.selectedColor = selectedColor;
        this.textX = textX;
    }

    public void setSelected(boolean selected){
        this.selected = selected;
    }

    public void render(Graphics graphics){
        FontMetrics fontMetrics = graphics.getFontMetrics(font);

        if(textX == -1)
            this.x = (Window.WIDTH - fontMetrics.stringWidth(text)) / 2;
        else {
            this.x = (Window.WIDTH - fontMetrics.stringWidth(text)) / 4;
            textX = this.x;
        }

        this.y = textY - fontMetrics.getHeight();
        this.width = fontMetrics.stringWidth(text);
        this.height = fontMetrics.getHeight();

        if (selected)
            Fonts.drawString(graphics, selectedFont, selectedColor, text, textX, textY);
        else
            Fonts.drawString(graphics, font, color, text, textX, textY);

        graphics.drawRect(x, y, width, height);
    }

}
