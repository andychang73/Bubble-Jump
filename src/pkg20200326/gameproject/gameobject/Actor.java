/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg20200326.gameproject.gameobject;

import pkg20200326.gameproject.state.actor_state.*;
import java.awt.Graphics;
import pkg20200326.gameproject.gameobject.bubbles.Bubble;
import pkg20200326.gameproject.gameobject.bubbles.GreenBubble;
import pkg20200326.gameproject.util.Global;

/**
 *
 * @author salesengineer4891
 */
public class Actor extends GameObject {

    public static final int ACTOR1_LEFT_FRAME = 0;
    public static final int ACTOR1_RIGHT_FRAME = Global.SCREEN_X / 2 - (int) (Actor.WIDTH / 2);
    public static final int ACTOR2_LEFT_FRAME = Global.SCREEN_X / 2 + (int) (Actor.WIDTH);
    public static final int ACTOR2_RIGHT_FRAME = Global.SCREEN_X;
    public static final int DIR_LEFT = 1;
    public static final int DIR_RIGHT = 2;
    public static final float HEIGHT = 68;
    public static final float WIDTH = 68;
    public static final float LEFT_SPEED = -8;
    public static final float RIGHT_SPEED = 8;
    public static final float WEIGHT = 1.5f;
    public static final float JUMP_STRENGTH = -25;
    public static final float JUMP_BONUS = -35;/////
    public static float GRAVITY = 0;

    protected int timeStopCounter;
    protected int delayFrame;
    protected int dir;
    protected int leftFrame;
    protected int rightFrame;
    protected int dustCounter;
    protected float yVelocity;
    protected float xVelocity;
    protected float currentGround;
    protected boolean isMoveLeft;
    protected boolean isMoveRight;
    protected boolean isJump;
    protected boolean isFalling;
    protected boolean isDust;
    protected Dust dust;
    protected MoveRight moveRight;
    protected MoveLeft moveLeft;
    protected ActorState previousState;
    protected ActorState currentState;
    protected ActorState newState;
    protected Bubble currentBubble;

    public Actor(int index, float x, float y, float width, float height, int dir, int delayFrame, int leftFrame, int rightFrame) {
        super(x, y, width, height);
        this.timeStopCounter = -1;
        this.delayFrame = delayFrame;
        this.dir = dir;
        this.leftFrame = leftFrame;
        this.rightFrame = rightFrame;
        this.dustCounter = 0;
        this.xVelocity = 0;
        this.yVelocity = 0;
        this.currentGround = Global.MAP_BOTTOM_Y;
        this.isMoveLeft = false;
        this.isMoveRight = false;
        this.isJump = false;
        this.isFalling = false;
        this.isDust = false;
        this.dust = new Dust(this);
        this.moveRight = new MoveRight(this, index);
        this.moveLeft = new MoveLeft(this, index);
        this.previousState = moveLeft;
        this.currentState = previousState;
        this.currentBubble = new GreenBubble(0, 0, 0, 0, 0, 0, 0);

    }

    public int leftFrame() {
        return this.leftFrame;
    }

    public int rightFrame() {
        return this.rightFrame;
    }

    public void setXVel(float xVelocity) {
        this.xVelocity = xVelocity;
        if (this.xVelocity < 0) {
            this.isMoveLeft = true;
            this.dir = Actor.DIR_LEFT;
        }
        if (this.xVelocity > 0) {
            this.isMoveRight = true;
            this.dir = Actor.DIR_RIGHT;
        }
    }

    public void moveLeft() {
        offset(this.xVelocity, 0);
        if (this.getX() < this.leftFrame + Global.UNIT_X / 2) {
            setX(this.leftFrame + Global.UNIT_X / 2);
        }
        this.isMoveLeft = false;
    }

    public void moveRight() {
        offset(this.xVelocity, 0);
        if (this.getX() > this.rightFrame - Global.UNIT_X / 2) {
            setX(this.rightFrame - Global.UNIT_X / 2);
        }
        this.isMoveRight = false;
    }

    public void revertMoveLeft(float revertLeftSpeed) {
        offset(revertLeftSpeed, 0);
    }

    public void revertMoveRight(float revertRightSpeed) {
        offset(revertRightSpeed, 0);
    }

    public boolean stayInFrame() {
        if (this.getX() < this.leftFrame + Global.UNIT_X / 2) {
            setX(this.leftFrame + Global.UNIT_X / 2);
            return false;
        }
        if (this.getX() > this.rightFrame - Global.UNIT_X / 2) {
            setX(this.rightFrame - Global.UNIT_X / 2);
            return false;
        }
        return true;
    }

    public float getYVel() {
        return this.yVelocity;
    }

    public void setYVel(float yVelocity) {
        this.yVelocity = yVelocity;
        if (this.yVelocity < 0) {
            setJump(true);
        }
    }

    public int getLeftFrame() {
        return this.leftFrame;
    }

    public int getRightFrame() {
        return this.rightFrame;
    }

    public int getDustCounter() {
        return this.dustCounter;
    }

    public int getTimeStopCounter() {
        return this.timeStopCounter;
    }

    public void setTimeStopCounter(int counter) {
        this.timeStopCounter = counter;
    }

    public void startTimeCounter() {
        this.timeStopCounter++;
    }

    public int getDelayFrame() {
        return this.delayFrame;
    }

    public void setJump(boolean isJump) {
        this.isJump = isJump;
    }

    public boolean getIsJump() {
        return isJump;
    }

    public float getCurrentGround() {
        return this.currentGround;
    }

    public ActorState getState() {
        return this.currentState;
    }

    public void setFalling(boolean isFalling) {
        this.isFalling = isFalling;
    }

    public Bubble getCurrentBubble() {
        return this.currentBubble;
    }

    public void setCurrentBubble(Bubble bubble) {
        this.currentBubble = bubble;
    }

    public void jump() {
        setFalling(false);
        offset(0, this.yVelocity);
        this.yVelocity += WEIGHT; //gravity immitation
        if (this.getY() + Actor.HEIGHT / 2 - current.collider_dist_fromObj > Global.MAP_BOTTOM_Y) {
            this.isDust = true;
        }
        if (this.getY() + Actor.HEIGHT / 2 - current.collider_dist_fromObj > this.currentGround) //keep character from falling through the ground
        {
            setY(currentGround - Actor.HEIGHT / 2 + current.collider_dist_fromObj);
            this.isJump = false;
            return;
        }
    }

    public void fall() {
        setJump(true);
        offset(0, GRAVITY);
        GRAVITY += WEIGHT;
        if (this.getY() + Actor.HEIGHT / 2 - current.collider_dist_fromObj > Global.MAP_BOTTOM_Y) {
            this.isDust = true;
        }
        if (this.getY() + Actor.HEIGHT / 2 - current.collider_dist_fromObj > this.currentGround) {
            setY(this.currentGround - Actor.HEIGHT / 2 + current.collider_dist_fromObj);
            this.isFalling = false;
            GRAVITY = 0;
            return;
        }
    }

    public void setCurrentGround(float currentGround) {
        this.currentGround = currentGround;
        setY(this.currentGround - Actor.HEIGHT / 2 + current.collider_dist_fromObj);
        this.isJump = false;
    }

    public void setCurrentGroundToMapBottom(float mapBottom) {
        this.currentGround = mapBottom;
    }

    @Override
    public void actionUpdate() {
        currentState.update();
        if (isJump) {
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
        if (isDust) {
            dust.update();
            this.dustCounter++;
        }
        if (dustCounter >= 45) {
            this.isDust = false;
            this.dustCounter = 0;
        }
        switch (this.dir) {
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

    @Override
    public void paintComponent(Graphics g, float cameraTopY, float cameraBottomY) {
        if (this.getY() >= cameraTopY && this.getY() < cameraBottomY) {
            currentState.paint(g, cameraTopY, cameraBottomY);
            if (isDust) {
                dust.paint(g, cameraTopY, cameraBottomY);
            }
        }
    }
}
