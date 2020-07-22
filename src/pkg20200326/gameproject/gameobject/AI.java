/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg20200326.gameproject.gameobject;

import java.util.ArrayList;
import pkg20200326.gameproject.controller.AudioPath;
import pkg20200326.gameproject.controller.AudioResourceController;
import pkg20200326.gameproject.gameobject.BubbleInterface.CreateGreenBubble;
import pkg20200326.gameproject.gameobject.BubbleInterface.CreateYellowBubble;
import pkg20200326.gameproject.gameobject.bubbles.Bubble;
import pkg20200326.gameproject.state.actor_state.MoveLeft;
import pkg20200326.gameproject.state.actor_state.MoveRight;
import pkg20200326.gameproject.util.Global;
import pkg20200326.gameproject.util.Delay2;




/**
 *
 * @author salesengineer4891
 */
public class AI extends Actor {
    private float bubbleSearchRangeX;
    private float bubbleSearchRangeY;
    private float dxPerFrame;
    private float maxFrame;
    private Delay2 jumpDelay;
    private Delay2 bubbleDelay;
    private Delay2 turnDelay;
    private Bubble targetBubble;
    public ArrayList<Bubble> bubbleList;
    
    
    public AI(int index, float x, float y, float width, float height, int dir, int delayFrame, int leftFrame, int rightFrame) {
        super(index, x, y, width, height, dir, delayFrame, leftFrame, rightFrame);
        this.bubbleSearchRangeX = 220; //288 - actor width 68
        this.bubbleSearchRangeY = 153; //211 - actor height 153
        this.dxPerFrame = 0;
        this.maxFrame = 33; // xMax divide by moveSpeed of 8 and round up
        this.jumpDelay = new Delay2(15);
        this.bubbleDelay = new Delay2(15);
        this.turnDelay = new Delay2(15);
        this.targetBubble = null;
        this.bubbleList = new ArrayList<>();
    }
    
    public ArrayList<Bubble> getBubbleList()
    {
        return this.bubbleList;
    }

    private void getDxPerFrame()
    {
        float bubbleX = this.targetBubble.getX();
        float bubbleDx = this.targetBubble.getDx();
        float target_destination;
        float current_destination;
        float distance;
        float total_dxPerFrame;
        target_destination = bubbleX + bubbleDx * (maxFrame + jumpDelay.getDelayFrame());
        current_destination = this.getX() + (this.currentBubble.getDx() * jumpDelay.getDelayFrame());
        distance = target_destination - current_destination;
        total_dxPerFrame = distance / maxFrame;
        setDxPerFrame(total_dxPerFrame - currentBubble.getDx());
    }
    
    private void setDxPerFrame(float dxPerFrame)
    {
        this.dxPerFrame = dxPerFrame;
    }

    private void aiNearBoundary()
    {
        if(this.getX() < this.leftFrame + 200)
        {
            this.dir = Actor.DIR_RIGHT;
        }
        if(this.getX() > this.rightFrame - 200)
        {
            this.dir = Actor.DIR_LEFT;
        }
    }
    
    private void bubbleNearBoundary()
    {
        if(targetBubble != null)
        {
            if(targetBubble.getX() < this.leftFrame + 150)
            {
                this.dir = Actor.DIR_RIGHT;
                this.jumpDelay.stop();
            }
            if(targetBubble.getX() > this.rightFrame - 150)
            {
                this.dir = Actor.DIR_LEFT;
                this.jumpDelay.stop();
            }
        }
    }
    
    private void changeDir()
    {
        if(currentState == moveLeft)
        {
            this.dir = Actor.DIR_RIGHT;
        }
        if(currentState == moveRight)
        {
            this.dir = Actor.DIR_LEFT;
        }
    }
    
    private boolean hasBubble()
    { 
        for(int i = 0; i < bubbleList.size(); i++)
        {
            if(this.currentState.getType().equals(MoveRight.TYPE))
            {
              if(bubbleList.get(i).getX() >= this.getX() && bubbleList.get(i).getX() <= (this.getX() + bubbleSearchRangeX) &&
                 bubbleList.get(i).getY() <= this.getY() && bubbleList.get(i).getY() >= (this.getY() - bubbleSearchRangeY))
                {
                    this.targetBubble = bubbleList.get(i);
                    return true;
                }  
            }
            if(this.currentState.getType().equals(MoveLeft.TYPE))
            {
                if(bubbleList.get(i).getX() <= this.getX() && bubbleList.get(i).getX() >= (this.getX() + bubbleSearchRangeX * -1) &&
                   bubbleList.get(i).getY() <= this.getY() && bubbleList.get(i).getY() >= (this.getY() - bubbleSearchRangeY))
                {
                    this.targetBubble = bubbleList.get(i);
                    return true;
                }
            }
        }
        return false;
    }
    
    @Override
    public void setCurrentGround(float currentGround) {
        this.currentGround = currentGround;
        setY(this.currentGround - Actor.HEIGHT / 2 + current.collider_dist_fromObj);
        this.isJump = false;
    }
    
    @Override
    public void jump() 
    {
        setFalling(false);
        setXVel(this.dxPerFrame);
        offset(0, this.yVelocity);
        this.yVelocity += WEIGHT; //gravity immitation
        if(this.getY() + Actor.HEIGHT/2 - current.collider_dist_fromObj > Global.MAP_BOTTOM_Y)
        {
            this.isDust = true;
        }
        if (this.getY() + Actor.HEIGHT/2 - current.collider_dist_fromObj > this.currentGround) //keep character from falling through the ground
        {
            setY(currentGround - Actor.HEIGHT / 2 + current.collider_dist_fromObj);
            this.isJump = false;
        }
    }
    
    @Override
    public void fall() {

        this.jumpDelay.stop();
        offset(0, GRAVITY);
        GRAVITY += WEIGHT;
        if (this.getY() + Actor.HEIGHT / 2 - current.collider_dist_fromObj > Global.MAP_BOTTOM_Y) {
            this.isDust = true;
        }
        if (this.getY() + Actor.HEIGHT / 2 - current.collider_dist_fromObj > this.currentGround) {
            setY(this.currentGround - Actor.HEIGHT / 2 + current.collider_dist_fromObj);
            this.isFalling = false;
            GRAVITY = 0;
        }
    }
    
    private boolean fallToTheGround()
    {
        return this.getY() >= 4973;
    }

    @Override
    public void actionUpdate()
    {
        this.currentState.update();
        if(!hasBubble() && Global.random(1, 100) <= 40 && turnDelay.getIsPause() && !isJump && bubbleDelay.getIsPause() && jumpDelay.getIsPause())
        {
            turnDelay.start();
        }
        if(turnDelay.isTrig() && !isJump && bubbleDelay.getIsPause())
        {
            turnDelay.stop();
            changeDir();
        }
        if(!hasBubble() && !isJump && bubbleDelay.getIsPause() && 
           jumpDelay.getIsPause() && turnDelay.getIsPause())
        {
            bubbleDelay.start();
        }
        if(bubbleDelay.isTrig() && !isJump && turnDelay.getIsPause())
        {
            bubbleDelay.stop();
            createBubble();
        }
        aiNearBoundary();
        bubbleNearBoundary();       

        if(hasBubble() && !isJump && jumpDelay.getIsPause() && 
           turnDelay.getIsPause())
        {
            if(fallToTheGround())
            {
                this.currentBubble.setDx(0);
            }
            getDxPerFrame();
            jumpDelay.start();
        }
        if(jumpDelay.isTrig())
        {
            AudioResourceController.getInstance().play(AudioPath.JUMP);
            jumpDelay.stop();
            setYVel(Actor.JUMP_STRENGTH);
        }
        if (isJump) 
        {
            jump();
        }
        if (isFalling) {
            fall();
        }
        if (isMoveLeft) {
            moveLeft();
        }
        if (isMoveRight) {
            moveRight();
        }
        if(isDust)
        {
            dust.update();
            this.dustCounter++;
        }
        if(dustCounter >= 45)
        {
            this.isDust = false;
            this.dustCounter = 0;
        }
        switch (this.dir) 
        {
            case Actor.DIR_LEFT:
                newState = moveLeft;
                break;
            case Actor.DIR_RIGHT:
                newState = moveRight;
                break;
        }
        if (currentState != newState) {
            previousState = currentState;
            currentState = newState;
        }
    }
    public void createBubble()
    {
        int bubbleType = (int)(Global.random(1, 100));
        if( bubbleType <= 25)
        {
            Bubble bubble = Bubble.createBubble(new CreateYellowBubble(), this);
            this.bubbleList.add(bubble);
            return;
        }
        if(bubbleType > 25)
        {
            Bubble bubble = Bubble.createBubble(new CreateGreenBubble(), this);
            this.bubbleList.add(bubble);
        }

    }
}