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
import pkg20200326.gameproject.util.Delay;

/**
 *
 * @author chant
 */
public class Fruit extends GameObject {

    private BufferedImage imgFruit;
    private int state;
    private Delay delay;
    private int index;

    public Fruit(int index, float x, float y, float width, float height) {
        super(x, y, width, height);
        this.imgFruit = ImageResourceController.getInstance().tryGetImage(ImagePath.FRUIT);
        this.delay = new Delay(5);
        this.delay.start();
        this.state = 0;
        this.index = index;
    }

    @Override
    public void actionUpdate() {
        if (this.delay.isTrig()) {
            this.state++;
        }
        if (this.state >= 17) {
            this.state = 0;
        }
    }

    @Override
    public void paintComponent(Graphics g, float cameraTopY, float cameraBottomY) {
        if (this.getY() >= cameraTopY && this.getY() <= cameraBottomY) {
            g.drawImage(imgFruit, (int) (this.getX() - 48),
                    (int) (this.getY() - 48 - cameraTopY),
                    (int) (this.getX() + 48),
                    (int) (this.getY() + 48 - cameraTopY),
                    32 * state, 32 * index, 32 + 32 * state, 32 + 32 * index, null);
        }

    }
}
