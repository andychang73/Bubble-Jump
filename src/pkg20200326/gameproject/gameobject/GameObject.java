/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg20200326.gameproject.gameobject;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import pkg20200326.gameproject.util.Global;

/**
 *
 * @author salesengineer4891
 */
public abstract class GameObject {

    public BufferedImage img;
    public Collider previous;
    public Collider current;

    public GameObject(float x, float y, float width, float height) {
        this.previous = new Collider(x, y, width, height);
        this.current = new Collider(x, y, width, height);
    }

    public float getX() {
        return this.current.x;
    }

    public float getY() {
        return this.current.y;
    }

    public float getWidth() {
        return this.current.width;
    }

    public float getHeight() {
        return this.current.height;
    }

    public float getLeft() {
        return this.current.getLeft();
    }

    public float getRight() {
        return this.current.getRight();
    }

    public float getTop() {
        return this.current.getTop();
    }

    public float getBottom() {
        return this.current.getBottom();
    }

    public void offset(float dx, float dy) {
        this.current.offset(dx, dy);
    }

    public void setX(float x) {
        this.current.setX(x);
    }

    public void setY(float y) {
        this.current.setY(y);
    }

    private float getMax(float dx, float dy, float objDx, float objDy)
    {
        float max = dx;
        if (max < dy) {
            max = dy;
        }
        if (max < objDx) {
            max = objDx;
        }
        if (max < objDy) {
            max = objDy;
        }
        return max;
    }
    
    public static class CCounter {

        public int dir;
        public float offsetX;
        public float offsetY;
    }
    
    public CCounter cCounter = new CCounter();

    public int isCollided(GameObject obj) 
    {
        Collider p = new Collider(previous);
        Collider objp = new Collider(obj.previous);
        float dx = current.getX() - p.getX();
        float dy = current.getY() - p.getY();
        float objDx = obj.current.getX() - objp.getX();
        float objDy = obj.current.getY() - objp.getY();
        float max = getMax(Math.abs(dx), Math.abs(dy), Math.abs(objDx), Math.abs(objDy));
        float loopNum = max / Collider.colliderThickness;
        if(loopNum <= 0)
        {
            loopNum = 1;
        }
        float dxStepPerFrame = dx / loopNum;
        float dyStepPerFrame = dy / loopNum;
        float objDxStepPerFrame = objDx / loopNum;
        float objDyStepPerFrame = objDy / loopNum;
        
        for (int i = 0; i < loopNum; i++) 
        {
            p.offset(dxStepPerFrame, dyStepPerFrame);
            objp.offset(objDxStepPerFrame, objDyStepPerFrame);
            if (Rect.intersect(p.rectLeft, objp.rectRight)) {
                cCounter.offsetX = current.rectLeft.getLeft() - (objp.rectRight.getRight() + 0.1f);
                cCounter.dir = 1;
                return 1;
            }
            if (Rect.intersect(p.rectRight, objp.rectLeft)) {
                cCounter.offsetX = current.rectRight.getRight() - (objp.rectLeft.getLeft() - 0.1f);
                cCounter.dir = 2;
                return 2;
            }
            if (Rect.intersect(p.rectBottom, objp.rectTop)) {
                cCounter.dir = 3;
                return 3;
            }
            if (Rect.intersect(p.rectTop, objp.rectBottom)) {
                cCounter.dir = 4;
                return 4;
            }
        }
        return 0;
    }
    
    public boolean ObjCollision(GameObject obj)
    {
        if(this.getLeft() > obj.getRight()) return false;
        if(this.getRight() < obj.getLeft()) return false;
        if(this.getTop() > obj.getBottom()) return false;
        if(this.getBottom() < obj.getTop()) return false;
        return true;
    }

    public void update() 
    {
        this.previous.set(current);
        actionUpdate();
    }

    public abstract void actionUpdate();

    public void paint(Graphics g,float cameraTopY, float cameraBottomY) 
    {
        paintComponent(g,cameraTopY, cameraBottomY);
        if (Global.IS_DEBUG) {
            this.current.paint(g,cameraTopY, cameraBottomY);
        }
    }

    public abstract void paintComponent(Graphics g,float cameraTopY, float cameraBottomY);
}
