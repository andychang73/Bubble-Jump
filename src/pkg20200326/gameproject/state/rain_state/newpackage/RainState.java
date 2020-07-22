/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg20200326.gameproject.state.rain_state.newpackage;

import java.awt.Graphics;
import pkg20200326.gameproject.gameobject.Rain;

/**
 *
 * @author salesengineer4891
 */
public abstract class RainState {
    
    protected Rain rain;
    
    public RainState(Rain rain)
    {
        this.rain = rain;
    }
    
    public abstract String getType();
    
    public abstract void update();
    
    public abstract void paint(Graphics g, float cameraTopY, float cameraBottomY);
    
}
