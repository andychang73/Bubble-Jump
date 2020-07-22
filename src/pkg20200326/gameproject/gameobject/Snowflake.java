/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg20200326.gameproject.gameobject;

import java.awt.Graphics;
import pkg20200326.gameproject.state.snowflake_state.*;
import pkg20200326.gameproject.util.Global;

/**
 *
 * @author salesengineer4891
 */
public class Snowflake extends GameObject{
    public static final int endCount = 21;
    private int counter;
    private float dy;
    protected SnowflakeBreak snowbreak;
    protected SnowflakeState currentState;
    protected boolean isSummoned;

    public Snowflake(float x, float y, float width, float height, float dy) {
        super(x, y, width, height);
        this.counter = 0;
        this.dy = dy;
        this.snowbreak = new SnowflakeBreak(this);
        this.currentState = new Snowing(this);
        this.isSummoned = false;
    }
    
    public boolean isSummoned()
    {
        return this.isSummoned;
    }
    
    public void setIsSummoned(boolean summoned)
    {
        this.isSummoned = summoned;
    }
    
    public void dropping()
    {
        this.current.offset(0, dy);
    }
    
    public int getCounter()
    {
        return this.counter;
    }
    
    @Override
    public void actionUpdate() 
    {
        dropping();
        currentState.update();
        if(this.current.rectBottom.getBottom() > Global.MAP_BOTTOM_Y)
        {
            this.dy = 0;
            currentState = snowbreak;
        }
        if(currentState.getType().equals("SnowFlakeBreak"))
        {
            this.counter++;
        }
    }

    @Override
    public void paintComponent(Graphics g, float cameraTopY, float cameraBottomY) 
    {
        if(this.getY() >= cameraTopY && this.getY() <= cameraBottomY)
        {
            currentState.paint(g, cameraTopY, cameraBottomY);
        }
    }
}
