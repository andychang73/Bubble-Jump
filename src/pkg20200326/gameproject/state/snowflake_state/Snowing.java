/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg20200326.gameproject.state.snowflake_state;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import pkg20200326.gameproject.controller.*;
import pkg20200326.gameproject.gameobject.Snowflake;

/**
 *
 * @author salesengineer4891
 */
public class Snowing extends SnowflakeState{
    public static final String TYPE ="Snowing";
    
    private BufferedImage img;
    private int counter;
    private int angle;
    private int angle_per_frame;
    private int sf_row_num;
    private int sf_col_index;
    private int sf_row_index;
    private int sf_col_unit;
    private int sf_row_unit;
    
    public Snowing(Snowflake snowflake) {
        super(snowflake);
        this.img = ImageResourceController.getInstance().tryGetImage(ImagePath.SNOWFLAKE);
        this.counter = 0;
        this.angle = 0;
        this.angle_per_frame = 2;
        this.sf_row_num = 1;
        this.sf_col_index = 0;
        this.sf_row_index = 0;
        this.sf_col_unit = 226;
        this.sf_row_unit = 226;
    }

    @Override
    public String getType()
    {
        return TYPE;
    }
    
    @Override
    public void update() 
    {
        this.angle += angle_per_frame % 360;
    }

    @Override
    public void paint(Graphics g, float cameraTopY, float cameraBottomY) 
    {
        Graphics2D g2d = (Graphics2D)g;
        AffineTransform oldXForm = g2d.getTransform();
        g2d.rotate(Math.toRadians(angle), (int)snowflake.getX(), (int)snowflake.getY() - cameraTopY);
        g2d.drawImage(img, (int)(snowflake.getX() - snowflake.getWidth() / 2), 
                           (int)(snowflake.getY() - snowflake.getHeight() / 2 - cameraTopY), 
                           (int)(snowflake.getX() + snowflake.getWidth() / 2), 
                           (int)(snowflake.getY() + snowflake.getHeight() / 2 - cameraTopY), 
                           (sf_row_unit * sf_row_index), 
                           (sf_col_unit * sf_col_index), 
                           (sf_row_unit + sf_row_unit * sf_row_index), 
                           (sf_col_unit + sf_col_unit * sf_col_index), null);
        g2d.setTransform(oldXForm);  
    }
    
}
