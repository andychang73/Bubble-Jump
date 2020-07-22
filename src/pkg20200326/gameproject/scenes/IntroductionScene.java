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
import pkg20200326.gameproject.util.CommandSolver;
import pkg20200326.gameproject.util.Delay;
import pkg20200326.gameproject.util.Global;

/**
 *
 * @author chant
 */
public class IntroductionScene extends Scene {

    private BufferedImage img;
    private BufferedImage imgChoose;
    private int choose;
    private Delay delay;
    private int waitTime;

    public IntroductionScene(SceneController sceneController) {
        super(sceneController);
    }

    @Override
    public void sceneBegin() {
        this.img = ImageResourceController.getInstance().tryGetImage(ImagePath.INTRO);
        this.imgChoose = ImageResourceController.getInstance().tryGetImage(ImagePath.INTROCHOOSE);
        this.choose = 0;
        this.delay = new Delay(60);
        this.delay.start();
        this.waitTime = 2;
    }

    @Override
    public void sceneUpdate() {
        if (this.choose < 0) {
            this.choose = 2;
        }
        if (this.choose > 2) {
            this.choose = 0;
        }
        if (this.delay.isTrig()) {
            this.waitTime--;
        }
    }

    @Override
    public void sceneEnd() {
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(img, 0, 0, 1680, 900, null);
        g.drawImage(imgChoose, 600, 400 + 100 * choose, 1077, 500 + 100 * choose, 0, 0 + 100 * choose, 480, 100 + 100 * choose, null);
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
            switch (commandCode) {

            }
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
                            sceneController.changeScene(new SecondScene(sceneController));
                        }
                        if (choose == 1) {
                            sceneController.changeScene(new IntroSinEasyScene(sceneController));
                        }
                        if (choose == 2) {
                            sceneController.changeScene(new IntroBinEasyScene(sceneController));
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
