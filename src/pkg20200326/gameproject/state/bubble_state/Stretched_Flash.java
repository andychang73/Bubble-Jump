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
public class Stretched_Flash extends BubbleState{
    public static final String TYPE ="str_flash";
    
    private BufferedImage img;
    private int bubbleIndex;
    private int bubble_row_num;
    private int bubble_col_num;
    private int bubble_row_unit;
    private int bubble_col_unit;
    
    public Stretched_Flash(Bubble bubble, int bubbleIndex) 
    {
        super(bubble);
        this.img = ImageResourceController.getInstance().tryGetImage(ImagePath.BUBBLE_STRETCHED);
        this.bubbleIndex = bubbleIndex;
        this.bubble_row_num = 2;
        this.bubble_col_num = 2;
        this.bubble_row_unit = 400;
        this.bubble_col_unit = 385;
    }

    @Override
    public String getType()
    {
        return TYPE;
    }
    
    @Override
    public void update() {}

    @Override
    public void paint(Graphics g, float cameraTopY, float cameraBottomY) 
    {
        if(bubble.getCounter() % 3 == 0)
        {
            g.drawImage(img, (int)(bubble.getX() - bubble.getWidth() / 2), 
                             (int)(bubble.getY() - bubble.getHeight() / 2 - cameraTopY), 
                             (int)(bubble.getX() + bubble.getWidth() / 2), 
                             (int)(bubble.getY() + bubble.getHeight() / 2 - cameraTopY), 
                             (bubble_row_unit * (bubbleIndex % bubble_row_num)), 
                             (bubble_col_unit * (bubbleIndex / bubble_col_num)), 
                             (bubble_row_unit + bubble_row_unit * (bubbleIndex % bubble_row_num)), 
                             (bubble_col_unit + bubble_col_unit * (bubbleIndex / bubble_col_num)), null);
        }
    }
    
}
