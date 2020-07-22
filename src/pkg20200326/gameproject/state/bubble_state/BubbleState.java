/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg20200326.gameproject.state.bubble_state;

import java.awt.Graphics;
import pkg20200326.gameproject.gameobject.bubbles.Bubble;

/**
 *
 * @author salesengineer4891
 */
public abstract class BubbleState {

    protected Bubble bubble;

    public BubbleState(Bubble bubble) {
        this.bubble = bubble;
    }

    public abstract String getType();

    public abstract void update();
        
    public abstract void paint(Graphics g, float cameraTopY, float cameraBottomY);
}
