package com.swingy.view;

import com.swingy.input.MouseInput;
import com.swingy.util.Fonts;

import java.awt.*;
import java.awt.event.MouseEvent;

import com.swingy.rendering.ui.Button;

public class Menu {

    private Swingy swingy;

    private final Button[] options;
    private int currentSelection;

    public Menu(Swingy swingy){
        this.swingy = swingy;

        options = new Button[4];
        options[0] = new Button("New Game", (200 + 0 * 80),
                new Font("Arial", Font.PLAIN, 32),
                new Font("Arial", Font.BOLD, 48),
                Color.WHITE,
                Color.YELLOW);
        options[1] = new Button("Load Game", (200 + 1 * 80),
                new Font("Arial", Font.PLAIN, 32),
                new Font("Arial", Font.BOLD, 48),
                Color.WHITE,
                Color.YELLOW);
        options[2] = new Button("Settings", (200 + 2 * 80),
                new Font("Arial", Font.PLAIN, 32),
                new Font("Arial", Font.BOLD, 48),
                Color.WHITE,
                Color.YELLOW);
        options[3] = new Button("Exit", (200 + 3 * 80),
                new Font("Arial", Font.PLAIN, 32),
                new Font("Arial", Font.BOLD, 48),
                Color.WHITE,
                Color.YELLOW);
    }

    public void tick(){
        boolean clicked = false;

        for (int i = 0; i < options.length; i++){
            if (options[i].intersects(new Rectangle(MouseInput.getX(), MouseInput.getY(), 1, 1))) {
                currentSelection = i;
                clicked = MouseInput.wasPressed(MouseEvent.BUTTON1);
            }
        }

        if (clicked)
            select();
    }

    private void select(){
        switch (currentSelection){
            case 0 :
                System.out.println("new game");
                break ;
            case 1 :
                System.out.println("load game");
                break ;
            case 2 :
                System.out.println("settings");
                break ;
            case 3 :
                System.out.println("exit");
                swingy.stop();
                break ;
        }
    }

    public void render (Graphics graphics){
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, Swingy.WIDTH, Swingy.HEIGHT);
        Fonts.drawString(graphics, new Font("Arial", Font.BOLD, 72), Color.GREEN, Swingy.TITLE, 80, false);

        for (int i = 0; i < options.length; i++){
            if (i == currentSelection)
                options[i].setSelected(true);
            else
                options[i].setSelected(false);
            options[i].render(graphics);
        }
    }
}
