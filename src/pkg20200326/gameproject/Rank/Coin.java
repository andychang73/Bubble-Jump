/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg20200326.gameproject.Rank;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import pkg20200326.gameproject.controller.AudioPath;
import pkg20200326.gameproject.controller.AudioResourceController;
import pkg20200326.gameproject.controller.ImagePath;
import pkg20200326.gameproject.controller.ImageResourceController;
import pkg20200326.gameproject.util.Global;

/**
 *
 * @author chant
 */
public class Coin {

    private BufferedImage imgWin;
    private float x;
    private float y;
    private float weight;
    private float speed;

    public Coin(float x) {
        this.imgWin = ImageResourceController.getInstance().tryGetImage(ImagePath.WIN);
        this.x = 530 + x;
        this.y = 450;
        this.weight = 0.5f;
        this.speed = 20;
    }

    public void update(float x) {
        if (this.y < 650) {
            if (this.x < 534) {
                this.x -= x * 0.4;
            }
            if (this.x >= 534) {
                this.x += (x - 3.2) * 0.4;
            }
        }
        this.speed -= this.weight;
        this.y -= this.speed;
        if (this.y < 650 && this.y >= 641) {
            AudioResourceController.getInstance().play(AudioPath.TING);
        }
        if (this.y >= 650) {
            this.y = 650;
        }
    }

    public void paint(Graphics g) {
        g.drawImage(imgWin, (int) x, (int) y, 100, 100, null);
    }
}
