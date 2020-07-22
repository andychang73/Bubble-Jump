/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg20200326.gameproject.state.rain_state.newpackage;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import pkg20200326.gameproject.controller.*;
import pkg20200326.gameproject.gameobject.Rain;

/**
 *
 * @author salesengineer4891
 */
public class Splash extends RainState{
    public static final String TYPE= "Splash";
    
    private BufferedImage img;
    private int splash_row_num;
    private int splash_col_index;
    private int splash_row_index;
    private int splash_col_unit;
    private int splash_row_unit;
    
    public Splash(Rain rain) {
        super(rain);
        img = ImageResourceController.getInstance().tryGetImage(ImagePath.RAIN_SPLASH);
        this.splash_row_num = 9;
        this.splash_col_index = 0;
        this.splash_row_index = 0;
        this.splash_col_unit = 223;
        this.splash_row_unit = 98;
    }

    @Override
    public String getType()
    {
        return TYPE;
    }
    
    @Override
    public void update() 
    {
        if(rain.getCounter() % 3 == 0)
        {
            splash_row_index = (splash_row_index + 1) % splash_row_num;
        }
    }

    @Override
    public void paint(Graphics g, float cameraTopY, float cameraBottomY)
    {
        g.drawImage(img, (int)(rain.getX() - rain.getWidth()/2), 
                         (int)(rain.getY() - rain.getHeight()/2 - cameraTopY), 
                         (int)(rain.getX() + rain.getWidth()/2), 
                         (int)(rain.getY() + rain.getHeight()/2 - cameraTopY), 
                         (splash_row_unit * splash_row_index), 
                         (splash_col_unit * splash_col_index), 
                         (splash_row_unit + splash_row_unit * splash_row_index), 
                         (splash_col_unit + splash_col_unit * splash_col_index), null); 
        
    }
    
}
