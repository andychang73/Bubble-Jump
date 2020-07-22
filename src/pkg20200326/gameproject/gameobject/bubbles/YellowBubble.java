/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg20200326.gameproject.gameobject.bubbles;


/**
 *
 * @author salesengineer4891
 */
public class YellowBubble extends Bubble{
    public static final String TYPE = "YellowBubble";
    public static final int INDEX = 3;

    public YellowBubble(float x, float y, int leftFrame, int rightFrame,
                        float dx, float dy, int bubbleIndex)
    {
        super(x, y, leftFrame, rightFrame, dx, dy, bubbleIndex);;
    }
    
    @Override
    public String getType()
    {
        return TYPE;
    }
    
    @Override
    public void actionUpdate() //no counter, only explode when hit by meteor
    {
        currentState.update();
        //floating();
        if(isOutOfBound())
        {
            setCounter(Bubble.REMOVE_COUNT);
        }
        if(this.isStretched && !currentState.getType().equals("Stretched"))
        {
            currentState = stretched;
        }
        if(!this.isStretched && !currentState.getType().equals("Floating"))
        {
            currentState = floating;
        }
        if(this.counter == Bubble.POP_COUNT)
        {
            currentState = explode;
        }
        if(this.counter >= Bubble.POP_COUNT)
        {
            this.counter++;
        }
    }
}
