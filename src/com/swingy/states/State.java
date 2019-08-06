package com.swingy.states;

import java.awt.*;

public interface State {
    public void init();
    public void enterState();
    public void exitState();
    public String getName();
    public void tick(StateManager stateManager);
    public void render(Graphics graphics);
}
