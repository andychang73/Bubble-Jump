/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg20200326.gameproject.gameobject;

import java.awt.Color;
import java.awt.Graphics;
import pkg20200326.gameproject.util.Global;

/**
 *
 * @author salesengineer4891
 */
public class Collider {
    public static final int colliderThickness = 2;
    
    public float x;
    public float y;
    public float width;
    public float height;
    public float collider_dist_fromObj;
    public Rect rectLeft;
    public Rect rectRight;
    public Rect rectTop;
    public Rect rectBottom;
    
    public Collider(float x, float y, float width, float height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.collider_dist_fromObj = 7;
        this.rectRight = Rect.genWithCenter(x + this.width/2 - collider_dist_fromObj, y, colliderThickness, this.height- this.collider_dist_fromObj*2);
        this.rectLeft = Rect.genWithCenter(x - this.width/2 +collider_dist_fromObj, y, colliderThickness, this.height-this.collider_dist_fromObj*2);
        this.rectTop = Rect.genWithCenter(x, y - this.height/2+collider_dist_fromObj, this.width - this.collider_dist_fromObj*2, colliderThickness);
        this.rectBottom = Rect.genWithCenter(x, y + this.height/2-collider_dist_fromObj, this.width - this.collider_dist_fromObj*2, colliderThickness);
    }
    
    public Collider(Collider c){
        this.rectRight = new Rect(0,0,0,0);
        this.rectLeft = new Rect(0,0,0,0);
        this.rectTop = new Rect(0,0,0,0);
        this.rectBottom = new Rect(0,0,0,0);
        set(c);
    }
    
    public void set(Collider c){
        this.x = c.x;
        this.y = c.y;
        this.width = c.width;
        this.height = c.height;
        this.rectRight.set(c.rectRight);
        this.rectLeft.set(c.rectLeft);
        this.rectTop.set(c.rectTop);
        this.rectBottom.set(c.rectBottom);
    }
    
    public float getX()
    {
        return this.x;
    }
    
    public float getY()
    {
        return this.y;
    }
    
    public float getWidth()
    {
        return this.width;
    }
    
    public float getHeight()
    {
        return this.height;
    }
    
    public float getLeft()
    {
        return this.rectLeft.getLeft();
    }
    
    public float getRight()
    {
        return this.rectRight.getRight();
    }
    
    public float getTop()
    {
        return this.rectTop.getTop();
    }
    
    public float getBottom()
    {
        return this.rectBottom.getBottom();
    }
    
    public void offset(float dx, float dy)
    {
        this.x += dx;
        this.y += dy;
        this.rectRight.offset(dx, dy);
        this.rectLeft.offset(dx, dy);
        this.rectTop.offset(dx, dy);
        this.rectBottom.offset(dx, dy);
    }
    
    public void setX(float x)
    {
        this.rectRight.offset(x - this.rectRight.centerX() + this.width/2-collider_dist_fromObj, 0);
        this.rectLeft.offset(x - this.rectLeft.centerX() - this.width/2+collider_dist_fromObj, 0);
        this.rectTop.offset(x - this.rectTop.centerX(), 0);
        this.rectBottom.offset(x - this.rectBottom.centerX(), 0);
        this.x = x;
    }
    
    public void setY(float y)
    {
        this.rectRight.offset(0, y - this.rectRight.centerY());
        this.rectLeft.offset(0, y - this.rectLeft.centerY());
        this.rectTop.offset(0, y - this.rectTop.centerY() - this.height/2 + collider_dist_fromObj);
        this.rectBottom.offset(0, y - this.rectBottom.centerY() + this.height/2 - collider_dist_fromObj);
        this.y = y;
    }
    
    public void paint(Graphics g,float cameraTopY, float cameraBottomY){
        if(Global.IS_DEBUG)
        {
            if(this.y >= cameraTopY && this.y <= cameraBottomY)
            {
               g.setColor(Color.red);
               g.drawRect((int)(this.rectRight.getLeft()),(int)(this.rectRight.getTop()-cameraTopY), 
                          (int)(this.rectRight.getWidth()),(int)(this.rectRight.getHeight()));
               g.drawRect((int)(this.rectLeft.getLeft()), (int)(this.rectLeft.getTop()-cameraTopY), 
                          (int)(this.rectLeft.getWidth()),(int)(this.rectLeft.getHeight()));
               g.drawRect((int)(this.rectTop.getLeft()), (int)(this.rectTop.getTop()-cameraTopY), 
                          (int)(this.rectTop.getWidth()),(int)(this.rectTop.getHeight()));
               g.drawRect((int)(this.rectBottom.getLeft()), (int)(this.rectBottom.getTop()-cameraTopY), 
                          (int)(this.rectBottom.getWidth()),(int)(this.rectBottom.getHeight())); 
            }
        }
    }

    @Override
    public String toString(){
        return this.rectTop + " " + this.rectBottom;
    }
}
