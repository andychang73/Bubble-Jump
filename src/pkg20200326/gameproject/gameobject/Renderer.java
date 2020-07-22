/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg20200326.gameproject.gameobject;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import pkg20200326.gameproject.controller.ImagePath;
import pkg20200326.gameproject.controller.ImageResourceController;
import pkg20200326.gameproject.util.*;

/**
 *
 * @author salesengineer4891
 */
public class Renderer {
    public static final int[] WALK = {4, 5, 6, 7, 8, 9};
    public static final int[] JUMP = {11, 912, 13, 14};
    public static final int[] HEAD = {16, 17, 18, 19, 20, 21, 22, 23};
    public static final int[] STAND = {0, 1, 2, 3};
    private BufferedImage img;
    private int dir;
    private int currentStep;
    private int stepIndex;
    private int[] steps;
    private Delay delay;
    private int index;

    public Renderer(int index, int[] steps, int delayFrame,int dir) {
        this.img = ImageResourceController.getInstance().tryGetImage(ImagePath.ACTOR);
        this.dir = dir;
        this.currentStep = 0;
        this.stepIndex = 0;
        this.steps = steps;
        this.delay = new Delay(delayFrame);
        this.delay.start();
        this.index = index;
    }

    public void setDir(int dir) {
        this.dir = dir;
    }

    public void update() {
        if (this.delay.isTrig()) {
            stepIndex = (stepIndex + 1) % steps.length;
            currentStep = steps[stepIndex];
        }
    }

    public void paint(Graphics g, int x, int y, int w, int h) {
                x + 55, y + 55,
                (24 * currentStep),
                (48 * index + 24 * (2 - dir)),
                (24 + 24 * currentStep),
                (48 * index + 24 + 24 * (2 - dir)), null);
    }
}
