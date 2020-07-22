/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg20200326.gameproject.gameobject;

/**
 *
 * @author salesengineer4891
 */
public class Rect {
    private float left;
    private float right;
    private float top;
    private float bottom;

    
    public Rect(float left, float right, float top, float bottom)
    {
        this.left = left;
        this.right = right;
        this.top = top;
        this.bottom = bottom;
    }
    
    public static Rect genWithCenter(float x, float y, float width, float height)
    {
        float left = x - width / 2;
        float right = left + width;
        float top = y - height / 2;
        float bottom = top + height;
        return new Rect(left, right, top, bottom);
    }
    
    public boolean intersect(float left, float right, float top, float bottom)
    {
        if(this.left > right) return false;
        if(this.right < left) return false;
        if(this.top > bottom) return false;
        if(this.bottom < top) return false;
        return true;
    }

    public void offset(float dx, float dy)
    {
        this.left += dx;
        this.right += dx;
        this.top += dy;
        this.bottom += dy;
    }
    
    public static boolean intersect(Rect a, Rect b)
    {
        return a.intersect(b.left, b.right, b.top, b.bottom);
    }
       
    public void set(Rect rect)
    {
        this.left = rect.left;
        this.right = rect.right;
        this.top = rect.top;
        this.bottom = rect.bottom;
    }
    
    public float centerX()
    {
        return (left+right) / 2;
    }
    
    public float centerY()
    {
        return (top + bottom) / 2;
    }

    public float exactCenterX()
    {
        return (left + right) / 2f;
    }
    
    public float exactCenterY()
    {
        return (top + bottom) / 2f;
    }
    
    public float getLeft()
    {
        return left;
    }
    
    public void setLeft(int left)
    {
        this.left = left;
    }
    
    public float getRight()
    {
        return right;
    }
    
    public void setRight(int right)
    {
        this.right = right;
    }
    
    public float getTop()
    {
        return this.top;
    }
    
    public void setTop(int top)
    {
        this.top = top;
    }
    
    public float getBottom()
    {
        return this.bottom;
    }
    
    public void setBottom(int bottom)
    {
        this.bottom = bottom;
    }
    
    public float getWidth()
    {
        return this.right - this.left;
    }
    
    public float getHeight()
    {
        return this.bottom - this.top;
    }  
    
    @Override
    public String toString(){
        return "t: " + this.getTop() + " l: " + this.getLeft() +
                " r: " + this.getRight() + " b: " + this.getBottom();
    }
}
