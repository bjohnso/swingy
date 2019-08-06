package com.swingy.states;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class StateManager {

    private Map<String, State> map;
    private State currentState;

    public StateManager(){
        map = new HashMap<String, State>();
    }

    public void addState(State state){
        map.put(state.getName().toUpperCase(), state);
        if (currentState == null){
            state.enterState();
            currentState = state;
        }
    }

    public void setState(String name){
        State state = map.get(name.toUpperCase());
        if (state == null){
            System.err.println("State <" + name + "> does not exist");
            return;
        }
        if (currentState != null)
            currentState.exitState();
        currentState = state;
        state.init();
    }

    public void tick(){
        currentState.tick(this);
    }

    public void render(Graphics graphics){
        currentState.render(graphics);
    }

}
