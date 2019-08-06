package com.swingy.input;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter {

    private static final int NUM_BUTTONS = 10;

    private static final boolean buttons[]  = new boolean[NUM_BUTTONS];
    private static final boolean lastButtons[] = new boolean[NUM_BUTTONS];

    @Override
    public void mousePressed(MouseEvent e) {
        buttons[e.getButton()] = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        buttons[e.getButton()] = false;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        super.mouseDragged(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        super.mouseMoved(e);
    }

    public static void update(){
        for (int i = 0; i < NUM_BUTTONS; i++){
            lastButtons[i] = buttons[i];
        }
    }

    public static boolean isDown(int buttonCode){
        return buttons[buttonCode];
    }

    public static boolean wasPressed(int buttonCode){
        return isDown(buttonCode) && !lastButtons[buttonCode];
    }

    public static boolean wasReleased(int buttonCode){
        return !isDown(buttonCode) && lastButtons[buttonCode];
    }
}
