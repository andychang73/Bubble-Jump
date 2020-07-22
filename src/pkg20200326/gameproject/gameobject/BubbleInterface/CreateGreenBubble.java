/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg20200326.gameproject.gameobject.BubbleInterface;

import pkg20200326.gameproject.gameobject.Actor;
import pkg20200326.gameproject.gameobject.bubbles.Bubble;
import pkg20200326.gameproject.gameobject.bubbles.GreenBubble;
import pkg20200326.gameproject.state.actor_state.MoveLeft;
import pkg20200326.gameproject.state.actor_state.MoveRight;
import pkg20200326.gameproject.util.Global;

/**
 *
 * @author salesengineer4891
 */
public class CreateGreenBubble implements BubbleCreator{
    
    @Override
    public Bubble bubbleCreator(Actor actor) 
    {
        if(actor.getState().getType().equals(MoveLeft.TYPE))
        {
            return new GreenBubble(actor.getX() - Bubble.WIDTH - 10, actor.getY(), 
                                   actor.getLeftFrame(), actor.getRightFrame(),
                                  (Global.random(0.5f, 0) * -1), (Global.random(0.5f, 0) * -1), GreenBubble.INDEX);
        }
        if(actor.getState().getType().equals(MoveRight.TYPE))
        {
            return new GreenBubble(actor.getX() + Bubble.WIDTH + 10, actor.getY(), 
                                   actor.getLeftFrame(), actor.getRightFrame(),
                                  (Global.random(0.5f, 0)), (Global.random(0.5f, 0) * -1), GreenBubble.INDEX);
        }
        return null;
    }
  
}
