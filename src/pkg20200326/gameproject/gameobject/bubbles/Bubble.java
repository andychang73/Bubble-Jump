/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg20200326.gameproject.gameobject.bubbles;

import java.awt.Graphics;
import pkg20200326.gameproject.controller.ImagePath;
import pkg20200326.gameproject.controller.ImageResourceController;
import pkg20200326.gameproject.gameobject.Actor;
import pkg20200326.gameproject.gameobject.BubbleInterface.BubbleCreator;
import pkg20200326.gameproject.gameobject.GameObject;
import pkg20200326.gameproject.state.bubble_state.*;

/**
 *
 * @author salesengineer4891
 */
public class Bubble extends GameObject{
    public static final float HEIGHT = 68;
    public static final float WIDTH = 68;
    public static final int FLASH_COUNT = 240;
    public static final int POP_COUNT = 300;
    public static final int REMOVE_COUNT = 313;
    public static Bubble createBubble(BubbleCreator creator, Actor actor)
    {
        return creator.bubbleCreator(actor);
    }
    
    private float dx;
    private float dy;
    private int leftFrame;
    private int rightFrame;
    protected int counter;
    protected Explode explode;
    protected Stretched stretched;
    protected Stretched_Flash str_flash;
    protected Flashing flashing;
    protected Floating floating;
    protected BubbleState currentState;
    protected boolean isStretched;
    protected boolean isMeteorSummoned;

    public Bubble(float x, float y,int leftFrame, int rightFrame, 
                  float dx, float dy, int bubbleIndex)
    {
        super(x, y, Bubble.WIDTH, Bubble.HEIGHT);
        this.img = ImageResourceController.getInstance().tryGetImage(ImagePath.BUBBLE);
        this.dx = dx;
        this.dy = dy;
        this.leftFrame = leftFrame;
        this.rightFrame = rightFrame;
        this.counter = 0;
        this.explode = new Explode(this);
        this.stretched = new Stretched(this, bubbleIndex);
        this.str_flash = new Stretched_Flash(this, bubbleIndex);
        this.flashing = new Flashing(this, bubbleIndex);
        this.floating = new Floating(this, bubbleIndex);
        this.currentState = floating;
        this.isStretched = false;
        this.isMeteorSummoned = false;
    }
    
    public float getDx()
    {
        return this.dx;
    }
    
    public void setDx(float dx)
    {
        this.dx = dx;
    }
    
    public float getDy()
    {
        return this.dy;
    }
    
    public void floating()
    {
        offset(this.dx,this.dy);
    }
    
    public int getCounter()
    {
        return this.counter;
    }
        
    public String getType()
    {
        return null;
    }

    public void setCounter(int counter)
    {
        this.counter = counter;
    }

    public void setIsStretched(boolean stretched)
    {
        this.isStretched = stretched;
    }

    public boolean getIsMeteorSummoned()
    {
        return this.isMeteorSummoned;
    }
    
    public void setIsMeteorSummoned(boolean meteorSummoned)
    {
        this.isMeteorSummoned = meteorSummoned;
    }

    public boolean isOutOfBound() 
    {
        if (this.getX() < this.leftFrame + Bubble.WIDTH / 2)
        {
            return true;
        }
        if (this.getX() > this.rightFrame - Bubble.WIDTH / 2) 
        {
            return true;
        }
        return false;
    }

    @Override
    public void actionUpdate() 
    {
        currentState.update();
        floating(); 
        if(isOutOfBound())
        {
            setCounter(Bubble.REMOVE_COUNT);
        }
        if(this.isStretched && !currentState.getType().equals("Stretched") &&
           this.counter < Bubble.FLASH_COUNT)
        {
            currentState = stretched;
        }
        if(!this.isStretched && !currentState.getType().equals("Floating") &&
           this.counter < Bubble.FLASH_COUNT)
        {
            currentState = floating;
        }
        if(this.isStretched && this.counter == Bubble.FLASH_COUNT)
        {
            currentState = str_flash;
        }
        if(!this.isStretched && this.counter == Bubble.FLASH_COUNT)
        {
            currentState = flashing;
        }
        if(this.counter == Bubble.POP_COUNT)
        {
            currentState = explode;
        }
        this.counter++;
    }

    @Override
    public void paintComponent(Graphics g,float cameraTopY, float cameraBottomY) 
    {
        if(this.getY() >= cameraTopY && this.getY() <= cameraBottomY)
        {
            currentState.paint(g,cameraTopY, cameraBottomY);
        }
    }
    
}
