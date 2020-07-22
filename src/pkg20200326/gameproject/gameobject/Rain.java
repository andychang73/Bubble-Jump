/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg20200326.gameproject.gameobject;

import java.awt.Graphics;
import pkg20200326.gameproject.state.rain_state.newpackage.*;
import pkg20200326.gameproject.util.Global;

/**
 *
 * @author chant
 */
public class Rain extends GameObject {

    public static final int endCount = 27;
    private int counter;
    private float dy;
    private float dyTmp;
    private Splash splash;
    private pkg20200326.gameproject.state.rain_state.newpackage.Raining rain;
    private RainState currentState;

    public Rain(float x, float y, float width, float height, float dy) {
        super(x, y, width, height);
        this.counter = 0;
        this.dy = dy;
        this.dyTmp = 0;
        splash = new Splash(this);
        rain = new Raining(this);
        currentState = rain;
    }

    public void dropping() {
        this.current.offset(0, dy);
    }

    public void timeStop() {
        this.dyTmp = dy;
        this.dy = 0;
    }

    public void endTimeStop() {
        this.dy = dyTmp;
    }

    public int getCounter() {
        return this.counter;
    }

    public void recycle() {
        currentState = rain;
        this.counter = 0;
        this.current.setX(-100);
        this.current.setY(4900);
    }

    public void reset(float xRange, float yRange, float dy) {
        this.current.setX(xRange);
        this.current.setY(yRange);
        this.dy = dy;
    }

    @Override
    public void actionUpdate() {
        currentState.update();
        dropping();
        if (this.current.rectBottom.getBottom() >= Global.MAP_BOTTOM_Y) {
            this.dy = 0;
            currentState = splash;
        }
        if (currentState.getType().equals("Splash")) {
            counter++;
        }
    }

    @Override
    public void paintComponent(Graphics g, float cameraTopY, float cameraBottomY) {
        if (this.getY() >= cameraTopY && this.getY() <= cameraBottomY) {
            currentState.paint(g, cameraTopY, cameraBottomY);
        }
    }
}
