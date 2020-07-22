/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg20200326.gameproject.state.actor_state;

import java.awt.Graphics;
import pkg20200326.gameproject.gameobject.Actor;
import pkg20200326.gameproject.gameobject.Renderer;

/**
 *
 * @author salesengineer4891
 */
public class MoveLeft extends ActorState {

    public static final String TYPE = "MoveLeft";
    private Renderer r;

    public MoveLeft(Actor actor, int index) {
        super(actor);
        this.r = new Renderer(index, Renderer.WALK, actor.getDelayFrame(),0);
        this.r.setDir(1);
    }

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public void update() {
        r.update();
    }

    @Override
    public void paint(Graphics g, float cameraTopY, float cameraBottomY) {
        r.paint(g, (int) actor.getX(),
                (int) (actor.getY() - cameraTopY),
                (int) actor.getWidth(),
                (int) actor.getHeight());
    }

}
