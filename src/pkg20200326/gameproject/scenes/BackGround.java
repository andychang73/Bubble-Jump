/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg20200326.gameproject.scenes;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import pkg20200326.gameproject.controller.ImagePath;
import pkg20200326.gameproject.controller.ImageResourceController;
import pkg20200326.gameproject.util.Global;

/**
 *
 * @author salesengineer4891
 */
public class BackGround {

    private BufferedImage img;
    private BufferedImage imgEnd;
    private BufferedImage img2;
    private BufferedImage imgSky;
    private BufferedImage imgGround;
    private float y;
    private float x;
    private float width;
    private float height;

    public BackGround(String imgPath1, String imgPath2, String imgPath3, float x, float y, float width, float height) {
        this.img = ImageResourceController.getInstance().tryGetImage(imgPath1);
        this.imgEnd = ImageResourceController.getInstance().tryGetImage(imgPath2);
        this.img2 = ImageResourceController.getInstance().tryGetImage(imgPath3);
        this.imgSky = ImageResourceController.getInstance().tryGetImage(ImagePath.SKY);
        this.imgGround = ImageResourceController.getInstance().tryGetImage(ImagePath.T1);
        this.y = y;
        this.x = x;
        this.width = width;
        this.height = height;
    }

    public float getY() {
        return this.y;
    }

    public void update() {

    }

    public void paint(Graphics g, float cameraY) {
        if (cameraY > 4300) {
            g.drawImage(imgGround, (int) x, (int) (4900 - cameraY), (int) width, (int) 500, null);
        }
        g.drawImage(img, (int) x, (int) (this.y - cameraY), (int) width, (int) height+10, null);
        if (cameraY > 4500) {
            g.drawImage(img2, (int) x, (int) (4000 - cameraY), (int) width, (int) 500, null);
        }
        if (cameraY <= 4000) {
            g.drawImage(img2, (int) x, (int) (3000 - cameraY), (int) width, (int) 1000, null);
        }
        if (cameraY <= 3000) {
            g.drawImage(img2, (int) x, (int) (2000 - cameraY), (int) width, (int) 1000, null);
        }
        if (cameraY <= 2000) {
            g.drawImage(img2, (int) x, (int) (1000 - cameraY), (int) width, (int) 1000, null);
        }
        if (cameraY <= 1400) {
            g.drawImage(imgEnd, (int) x, (int) (-200 - cameraY), (int) width, (int) 1200, null);///
        }
        if (cameraY <= 0) {
            g.drawImage(imgSky, (int) x, (int) (-1000 - cameraY), (int) width, (int) 800, null);
        }
    }
}