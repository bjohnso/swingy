package com.swingy.interfaces;

import java.awt.*;

public interface Renderable {
    public abstract void tick();
    public abstract void render(Graphics graphics);
}
