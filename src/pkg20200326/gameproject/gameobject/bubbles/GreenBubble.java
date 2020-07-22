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
public class GreenBubble extends Bubble{
    public static final String TYPE = "GreenBubble";
    public static final int INDEX = 2;

    public GreenBubble(float x, float y,int leftFrame, int rightFrame,
                       float dx, float dy, int bubbleIndex) 
    {
        super(x, y, leftFrame, rightFrame, dx, dy, bubbleIndex);
    }
    
    @Override
    public String getType()
    {
        return TYPE;
    }
}
