/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg20200326.gameproject.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import pkg20200326.gameproject.controller.ImagePath;
import pkg20200326.gameproject.controller.ImageResourceController;

/**
 *
 * @author chant
 */
public class Clock {

    private int playTime = 120;
    private String clock;
    private BufferedImage img;
    private BufferedImage img2;
    private BufferedImage img4;
    private Delay delay;

    public Clock() {
        this.delay = new Delay(60);
        delay.start();
        this.img = ImageResourceController.getInstance().tryGetImage(ImagePath.TIME);
        this.img2 = ImageResourceController.getInstance().tryGetImage(ImagePath.TIME1);
        this.img4 = ImageResourceController.getInstance().tryGetImage(ImagePath.STAR);
    }

    public int getPlayTime() {
        return this.playTime;
    }

    public void update() {
        if (this.delay.isTrig()) {
            this.playTime--;
            this.clock = "剩餘時間time： 0" + playTime / 60 + "：" + playTime % 60;
        }
    }

    public void paint(Graphics g) {
        Font time = new Font(Font.MONOSPACED, Font.BOLD, 35);
        g.setColor(Color.ORANGE);
        g.setFont(time);
        g.drawImage(img, 500, -155, null);
        g.drawImage(img2, 620, 24, 468, 32, null);//黃
        g.setColor(Color.BLUE);
        if (this.playTime <= 0) {
            playTime = 0;
        }
        g.fill3DRect(1088 - 4 * (120 - this.playTime), 24, 0 + 4 * (120 - this.playTime), 32, true);
        g.drawImage(img4, 537, 0, null);//星
    }
}
