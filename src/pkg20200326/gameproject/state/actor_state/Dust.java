/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg20200326.gameproject.state.actor_state;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import pkg20200326.gameproject.controller.ImagePath;
import pkg20200326.gameproject.controller.ImageResourceController;
import pkg20200326.gameproject.gameobject.Actor;

/**
 *
 * @author salesengineer4891
 */
public class Dust extends ActorState {

    private BufferedImage img;
    private int dust_row_num;
    private int dust_col_index;
    private int dust_row_index;
    private int dust_col_unit;
    private int dust_row_unit;

    public Dust(Actor actor) {
        super(actor);
        img = ImageResourceController.getInstance().tryGetImage(ImagePath.DUST);
        this.dust_row_num = 15;
        this.dust_col_index = 0;
        this.dust_row_index = 0;
        this.dust_col_unit = 40;
        this.dust_row_unit = 43;
    }

    @Override
    public String getType() {
        return null;
    }

    @Override
    public void update() {
        if (actor.getDustCounter() % 3 == 0) {
            dust_row_index = (dust_row_index + 1) % dust_row_num;
        }
    }

    @Override
    public void paint(Graphics g, float cameraTopY, float cameraBottomY) 
    {
        if(actor.getState().getType().equals("MoveLeft"))
        {
            g.drawImage(img, (int)(actor.getX()), 
                             (int)(actor.getY() - actor.getHeight() / 4 - cameraTopY), 
                             (int)(actor.getX() + actor.getWidth() * 1.25), 
                             (int)(actor.getY() + actor.getHeight() * 0.75  - cameraTopY), 
                             (dust_row_unit * dust_row_index), 
                             (dust_col_unit * dust_col_index), 
                             (dust_row_unit + dust_row_unit * dust_row_index), 
                             (dust_col_unit + dust_col_unit * dust_col_index), null);
        }
        if(actor.getState().getType().equals("MoveRight"))
        {
            g.drawImage(img, (int)(actor.getX()), 
                         (int)(actor.getY() - actor.getHeight() / 4 - cameraTopY), 
                         (int)(actor.getX() + actor.getWidth() * -1.25), 
                         (int)(actor.getY() + actor.getHeight() *0.75 - cameraTopY), 
                         (dust_row_unit * dust_row_index), 
                         (dust_col_unit * dust_col_index), 
                         (dust_row_unit + dust_row_unit * dust_row_index), 
                         (dust_col_unit + dust_col_unit * dust_col_index), null);
        }
    }

}
