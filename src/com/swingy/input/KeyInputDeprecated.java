package com.swingy.input;

import com.swingy.handlers.GameObjectHandler;
import com.swingy.id.ID;
import com.swingy.objects.GameObject;
import com.swingy.objects.Player;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInputDeprecated extends KeyAdapter {

    private GameObjectHandler gameObjectHandler;

    public KeyInputDeprecated(GameObjectHandler gameObjectHandler){
        this.gameObjectHandler = gameObjectHandler;
    }

    public void keyPressed(KeyEvent event){
        int key = event.getKeyCode();

        for (int i = 0; i < gameObjectHandler.getObjects().size(); i++){
            if (!gameObjectHandler.getObjects().get(i).getClass().getName().equalsIgnoreCase("com.swingy.objects.HUD")){
                Player tempObject = (Player) gameObjectHandler.getObjects().get(i);

                if (tempObject.getId() == ID.Challenger) {
                    if (key == KeyEvent.VK_A)
                        tempObject.attack();
                } else if (tempObject.getId() == ID.Defender) {
                    if (key == KeyEvent.VK_D)
                        tempObject.attack();
                }
            }
        }
    }

    public void KeyReleased(KeyEvent event){
        int key = event.getKeyCode();
    }

}
