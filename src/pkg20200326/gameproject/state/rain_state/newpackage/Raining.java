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
public class Raining extends RainState{
    public static final String TYPE ="Raining";
    private BufferedImage img;
    
    public Raining(Rain rain) {
        super(rain);
        img = ImageResourceController.getInstance().tryGetImage(ImagePath.RAIN);
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
        g.drawImage(img, (int) (rain.getX() - rain.getWidth() / 2), 
                         (int) (rain.getY() - rain.getHeight() / 2 - cameraTopY), 
                         (int) (rain.getX() + rain.getWidth() / 2), 
                         (int) (rain.getY() + rain.getHeight() / 2 - cameraTopY), 
                          0, 0, 148, 230, null); 
    }
    
}
