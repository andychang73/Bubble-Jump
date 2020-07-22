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
public class RainPool {
    private Rain [] rainpool;
    private int alive;
    private int total;
    
    public RainPool()
    {
        this.rainpool = new Rain[2];
        this.alive = 0;
        this.total = 0;
    }
    
    private void doubleArray()
    {
        Rain [] newArray = new Rain[this.rainpool.length*2];
        for(int i = 0; i < total; i++)
        {
            newArray[i] = this.rainpool[i];
        }
        this.rainpool = newArray;
    }
    
    public void add(Rain rain)
    {
        if(this.total == rainpool.length)
        {
            doubleArray();
        }
        this.rainpool[total++] = rain;
        this.alive++;
    }
    
    public void swap(int index)
    {
        Rain tmp = rainpool[index];
        rainpool[index] = rainpool[alive-1];
        rainpool[alive-1] = tmp;
        this.alive--;
    }

    public void reset(float xRange, float yRange, float dy)
    {
        rainpool[alive].reset(xRange, yRange, dy);
        this.alive++;
    }
    
    public Rain get(int index)
    {
        if(!isOutOfBound(index))
        {
            return rainpool[index];
        }
        return null;
    }
    
    public boolean isOutOfBound(int index)
    {
        if(index >= 0 && index <= total)
        {
            return false;
        }
        return true;
    }
    
    public int getTotal()
    {
        return this.total;
    }
    
    public int getAlive()
    {
        return this.alive;
    }
}
