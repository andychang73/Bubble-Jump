/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg20200326.gameproject.state.bubble_state;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import pkg20200326.gameproject.controller.ImagePath;
import pkg20200326.gameproject.controller.ImageResourceController;
import pkg20200326.gameproject.gameobject.bubbles.Bubble;

/**
 *
 * @author salesengineer4891
 */
public class Explode extends BubbleState{
    public static final String TYPE = "Explode";
    
    private BufferedImage img;
    private int exp_row_index;
    private int exp_col_index;
    private int exp_col_num;
    private int exp_row_num;
    private int exp_row_unit;
    private int exp_col_unit;
    
    public Explode(Bubble bubble) {
        super(bubble);
        this.img = ImageResourceController.getInstance().tryGetImage(ImagePath.EXPLODE);
        this.exp_row_index = 0;
        this.exp_col_index = 0;
        this.exp_col_num = 4;
        this.exp_row_num = 2;
        this.exp_row_unit = 256;
        this.exp_col_unit = 256;
    }

    @Override
    public String getType()
    {
        return TYPE;
    }
    
    @Override
    public void update() 
    {
        if(bubble.getCounter() % 2 == 0)
        {
            exp_col_index = (exp_col_index + 1) % exp_col_num;
        }
        if(bubble.getCounter() == 248)
        {
            exp_row_index += 1;
        }
    }

    @Override
    public void paint(Graphics g,float cameraTopY, float cameraBottomY) 
    {
        g.drawImage(img, (int)(bubble.getX() - bubble.getWidth() / 2), 
                         (int)(bubble.getY() - bubble.getHeight() / 2 - cameraTopY), 
                         (int)(bubble.getX() + bubble.getWidth() /2), 
                         (int)(bubble.getY() + bubble.getHeight() /2 - cameraTopY), 
                         (exp_row_unit * exp_row_index), 
                         (exp_col_unit * exp_col_index), 
                         (exp_row_unit + exp_row_unit * exp_row_index), 
                         (exp_col_unit + exp_col_unit * exp_col_index), null); 
    }
}
