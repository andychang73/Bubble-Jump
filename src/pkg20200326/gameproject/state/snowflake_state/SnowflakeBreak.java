/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg20200326.gameproject.state.snowflake_state;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import pkg20200326.gameproject.controller.*;
import pkg20200326.gameproject.gameobject.Snowflake;

/**
 *
 * @author salesengineer4891
 */
public class SnowflakeBreak extends SnowflakeState{
    public static final String TYPE = "SnowFlakeBreak";
    
    private BufferedImage img;
    private int sf_break_row_num;
    private int sf_break_col_index;
    private int sf_break_row_index;
    private int sf_break_col_unit;
    private int sf_break_row_unit;

    public SnowflakeBreak(Snowflake snowflake) {
        super(snowflake);
        this.img = ImageResourceController.getInstance().tryGetImage(ImagePath.SNOWFLAKE_BREAKING);
        this.sf_break_row_num = 7;
        this.sf_break_col_index = 0;
        this.sf_break_row_index = 0;
        this.sf_break_col_unit = 94;
        this.sf_break_row_unit = 60;
    }

    @Override
    public String getType()
    {
        return TYPE;
    }

    @Override
    public void update() 
    {
        if(snowflake.getCounter() % 3 == 0)
        {
            sf_break_row_index = (sf_break_row_index + 1) % sf_break_row_num; 
        }
    }

    @Override
    public void paint(Graphics g, float cameraTopY, float cameraBottomY) 
    {
        g.drawImage(img, (int)(snowflake.getX() - snowflake.getWidth() / 2), 
                         (int)(snowflake.getY() - snowflake.getHeight() / 2 - cameraTopY), 
                         (int)(snowflake.getX() + snowflake.getWidth() / 2), 
                         (int)(snowflake.getY() + snowflake.getHeight() / 2 - cameraTopY), 
                         (sf_break_row_unit * sf_break_row_index), 
                         (sf_break_col_unit * sf_break_col_index), 
                         (sf_break_row_unit + sf_break_row_unit * sf_break_row_index), 
                         (sf_break_col_unit + sf_break_col_unit * sf_break_col_index), null);
    }
}
