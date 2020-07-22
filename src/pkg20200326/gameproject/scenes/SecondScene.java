/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg20200326.gameproject.scenes;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import pkg20200326.gameproject.controller.AudioPath;
import pkg20200326.gameproject.controller.AudioResourceController;
import pkg20200326.gameproject.controller.ImagePath;
import pkg20200326.gameproject.controller.ImageResourceController;
import pkg20200326.gameproject.util.CommandSolver;

/**
 *
 * @author chant
 */
public class SecondScene extends Scene {

    private BufferedImage img;
    private BufferedImage imgchoose;
    private int choose;

    public SecondScene(SceneController sceneController) {
        super(sceneController);
    }

    @Override
    public void sceneBegin() {
        this.img = ImageResourceController.getInstance().tryGetImage(ImagePath.MAIN1);
        this.imgchoose = ImageResourceController.getInstance().tryGetImage(ImagePath.CHOOSE1);
        this.choose = 0;
        AudioResourceController.getInstance().play(AudioPath.SPRING);
    }

    @Override
    public void sceneUpdate() {
        if (this.choose < 0) {
            this.choose = 3;
        }
        if (this.choose > 3) {
            this.choose = 0;
        }
    }

    @Override
    public void sceneEnd() {
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(img, 0, 0, 1680, 900, null);
        g.drawImage(imgchoose, 543, 379 + 98 * choose, 1090, 477 + 98 * choose, 0, 98 * choose, 545, 98 + 98 * choose, null);
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
                case 42://Enter
                    if (choose == 0) {
                        sceneController.changeScene(new ThirdEasyScene(sceneController));
                    }
                    if (choose == 1) {
                        sceneController.changeScene(new IntroductionScene(sceneController));
                    }
                    if (choose == 2) {
                        sceneController.changeScene(new NameScene(sceneController));
                    }
                    if (choose == 3) {
                        sceneController.changeScene(new StartScene(sceneController));
                    }
                    break;
            }
        }

        @Override
        public void keyReleased(int commandCode, long trigTime) {
            switch (commandCode) {
                case 20://up
                    choose--;
                    break;
                case 41://down
                    choose++;
                    break;
            }
        }

        @Override
        public void keyTyped(char w, long trigTime) {

        }
    }
}
