/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg20200326.gameproject.gameobject.BubbleInterface;

import pkg20200326.gameproject.gameobject.Actor;
import pkg20200326.gameproject.gameobject.bubbles.Bubble;
import pkg20200326.gameproject.gameobject.bubbles.YellowBubble;
import pkg20200326.gameproject.state.actor_state.MoveLeft;
import pkg20200326.gameproject.state.actor_state.MoveRight;

/**
 *
 * @author salesengineer4891
 */
public class CreateYellowBubble implements BubbleCreator {

    @Override
    public Bubble bubbleCreator(Actor actor) 
    {
        if(actor.getState().getType().equals(MoveLeft.TYPE))
        {
            return new YellowBubble(actor.getX() - Bubble.WIDTH - 10, actor.getY(), 
                                    actor.getLeftFrame(), actor.getRightFrame(),
                                    0, 0, YellowBubble.INDEX);
        }
        if(actor.getState().getType().equals(MoveRight.TYPE))
        {
            return new YellowBubble(actor.getX() + Bubble.WIDTH + 10, actor.getY(), 
                                    actor.getLeftFrame(), actor.getRightFrame(),
                                    0, 0, YellowBubble.INDEX);
        }
        return null;
    }
}
