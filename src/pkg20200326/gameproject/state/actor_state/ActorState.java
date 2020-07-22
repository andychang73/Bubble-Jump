/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg20200326.gameproject.state.actor_state;

import java.awt.Graphics;
import pkg20200326.gameproject.gameobject.Actor;

/**
 *
 * @author salesengineer4891
 */
public abstract class ActorState {
    
    protected Actor actor;
    
    public ActorState(Actor actor)
    {
        this.actor = actor;
    }
    
    public abstract String getType();
     
    public abstract void update();
    
    public abstract void paint(Graphics g,float cameraTopY, float cameraBottomY);
}
