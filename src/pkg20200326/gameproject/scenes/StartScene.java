/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg20200326.gameproject.scenes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import pkg20200326.gameproject.controller.ImagePath;
import pkg20200326.gameproject.controller.ImageResourceController;
import pkg20200326.gameproject.util.CommandSolver;
import pkg20200326.gameproject.util.Delay;

/**
 *
 * @author chant
 */
public class StartScene extends Scene {

    private BufferedImage img1;
    private BufferedImage img2;
    private BufferedImage img3;
    private BufferedImage img4;
    private BufferedImage img5;
    private BufferedImage img6;
    private BufferedImage img22;
    private BufferedImage img32;
    private BufferedImage img42;
    private BufferedImage img52;
    private BufferedImage img62;
    private BufferedImage imgHE1;
    private BufferedImage imgHE2;
    private int x1;
    private int x2;
    private int x3;
    private int x4;
    private int x5;
    private int x6;
    private int x22;
    private int x32;
    private int x42;
    private int x52;
    private int x62;
    private Delay delay;
    private int waitTime;
    private int choose;

    public StartScene(SceneController sceneController) {
        super(sceneController);
    }

    @Override
    public void sceneBegin() {
        this.img1 = ImageResourceController.getInstance().tryGetImage(ImagePath.F1);
        this.img2 = ImageResourceController.getInstance().tryGetImage(ImagePath.F2);
        this.img3 = ImageResourceController.getInstance().tryGetImage(ImagePath.F3);
        this.img4 = ImageResourceController.getInstance().tryGetImage(ImagePath.F4);
        this.img5 = ImageResourceController.getInstance().tryGetImage(ImagePath.F5);
        this.img6 = ImageResourceController.getInstance().tryGetImage(ImagePath.F6);
        this.img22 = ImageResourceController.getInstance().tryGetImage(ImagePath.F2);
        this.img32 = ImageResourceController.getInstance().tryGetImage(ImagePath.F3);
        this.img42 = ImageResourceController.getInstance().tryGetImage(ImagePath.F4);
        this.img52 = ImageResourceController.getInstance().tryGetImage(ImagePath.F5);
        this.img62 = ImageResourceController.getInstance().tryGetImage(ImagePath.F6);
        this.imgHE1 = ImageResourceController.getInstance().tryGetImage(ImagePath.HE1);
        this.imgHE2 = ImageResourceController.getInstance().tryGetImage(ImagePath.HE2);
        this.x1 = 0;
        this.x2 = -1680;
        this.x3 = -1680;
        this.x4 = -1680;
        this.x5 = -1680;
        this.x6 = -1680;
        this.x22 = 0;
        this.x32 = 0;
        this.x42 = 0;
        this.x52 = 0;
        this.x62 = 0;
        this.delay = new Delay(60);
        this.delay.start();
        this.waitTime = 2;
        this.choose = 0;
    }

    @Override
    public void sceneUpdate() {
        if (this.delay.isTrig()) {
            this.waitTime--;
        }
        this.x2 += 1;
        this.x22 += 1;
        if (this.x2 == 1680) {
            this.x2 = -1680;
        }
        if (this.x22 == 1680) {
            this.x22 = -1680;
        }
        this.x3 += 2;
        this.x32 += 2;
        if (this.x3 == 1680) {
            this.x3 = -1680;
        }
        if (this.x32 == 1680) {
            this.x32 = -1680;
        }
        this.x4 += 3;
        this.x42 += 3;
        if (this.x4 == 1680) {
            this.x4 = -1680;
        }
        if (this.x42 == 1680) {
            this.x42 = -1680;
        }
        this.x5 += 4;
        this.x52 += 4;
        if (this.x5 == 1680) {
            this.x5 = -1680;
        }
        if (this.x52 == 1680) {
            this.x52 = -1680;
        }
        this.x6 += 5;
        this.x62 += 5;
        if (this.x6 == 1680) {
            this.x6 = -1680;
        }
        if (this.x62 == 1680) {
            this.x62 = -1680;
        }
        if (this.choose < 0) {
            this.choose = 1;
        }
        if (this.choose > 1) {
            this.choose = 0;
        }
    }

    @Override
    public void sceneEnd() {

    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(img1, x1, 0, 1680, 900, null);
        g.drawImage(img2, x2, 0, 1680, 900, null);
        g.drawImage(img22, x22, 0, 1680, 900, null);
        g.drawImage(img3, x3, 0, 1680, 900, null);
        g.drawImage(img32, x32, 0, 1680, 900, null);
        g.drawImage(img4, x4, 0, 1680, 900, null);
        g.drawImage(img42, x42, 0, 1680, 900, null);
        g.drawImage(img5, x5, 0, 1680, 900, null);
        g.drawImage(img52, x52, 0, 1680, 900, null);
        g.drawImage(img6, x6, 0, 1680, 900, null);
        g.drawImage(img62, x62, 0, 1680, 900, null);
        Font rank = new Font("微軟正黑體", Font.BOLD, 35);
        g.setColor(Color.BLACK);
        g.setFont(rank);
        g.drawString("進入森林", 700, 500);
        g.drawImage(imgHE1, 1400, 700, 200, 150, null);
        g.drawImage(imgHE2, 1400, 700 + 75 * choose, 1600, 775 + 75 * choose, 0, 236 * choose, 690, 236 + 236 * choose, null);
    }

    @Override
    public CommandSolver.KeyListener getKeyListener() {
        return new MyKeyListener();
    }

    @Override
    public CommandSolver.MouseCommandListener getMouseListener() {
        return null;
    }

    public class MyKeyListener implements CommandSolver.KeyListener {

        @Override
        public void keyPressed(int commandCode, long trigTime) {

        }

        @Override
        public void keyReleased(int commandCode, long trigTime) {
            if (waitTime <= 0) {
                switch (commandCode) {
                    case 20://up
                        choose--;
                        break;
                    case 41://down
                        choose++;
                        break;
                    case 42://Enter
                        if (choose == 0) {
                            sceneController.changeScene(new MainScene(sceneController));
                        }
                        if (choose == 1) {
                            sceneController.changeScene(new BinHardScene(sceneController));
                        }
                        break;
                }
            }
        }

        @Override
        public void keyTyped(char c, long trigTime) {
        }
    }
}
