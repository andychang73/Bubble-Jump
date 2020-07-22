/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg20200326.gameproject.util;

/**
 *
 * @author salesengineer4891
 */
public class Delay {
    protected int delayFrame;
    protected int counter;
    protected boolean isPause;
    
    public Delay(int delayFrame)
    {
        this.delayFrame = delayFrame;
        this.counter = delayFrame;
        this.isPause = false;
    }
    
    public void start()
    {
        this.isPause = false;
    }
    
    public void pause()
    {
        this.isPause = true;
    }
    
    public void stop()
    {
        pause();
        this.counter = delayFrame;
    }
    
    public void restart()
    {
        stop();
        start();
    }
    
    public boolean isTrig()
    {
        if(!this.isPause && this.counter++ == delayFrame)
        {
            this.counter = 0;
            return true;
        }
        return false;
    }
}
