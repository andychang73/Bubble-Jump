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
import pkg20200326.gameproject.gameobject.Renderer;
import pkg20200326.gameproject.util.CommandSolver;

/**
 *
 * @author chant
 */
public class IntroBinHardScene extends Scene {

    private BufferedImage img;
    private BufferedImage imgChoose;
    private BufferedImage imgActor;
    private int choose;
    private boolean level;

    public IntroBinHardScene(SceneController sceneController) {
        super(sceneController);
    }

    @Override
    public void sceneBegin() {
        this.img = ImageResourceController.getInstance().tryGetImage(ImagePath.INTROBINH);
        this.imgChoose = ImageResourceController.getInstance().tryGetImage(ImagePath.INTROCHOOSE1);
        this.imgActor = ImageResourceController.getInstance().tryGetImage(ImagePath.ACTOR);
        this.choose = 0;
        this.level = false;
    }

    @Override
    public void sceneUpdate() {
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
        g.drawImage(img, 0, 0, 1680, 900, null);
        g.drawImage(imgChoose, 55, 682 + 86 * choose, 526, 781 + 86 * choose, 0, 0 + 86 * choose, 480, 100 + 86 * choose, null);
        g.drawImage(imgActor, 150, 450, // generate picture with center
                250, 550,
                (24 * 0),
                (48 * 0 + 24 * (2 - 2)),
                (24 + 24 * 0),
                (48 * 0 + 24 + 24 * (2 - 2)), null);
        g.drawImage(imgActor, 1400, 430, // generate picture with center
                1500, 530,
                (24 * 0),
                (48 * 1 + 24 * (2 - 1)),
                (24 + 24 * 0),
                (48 * 1 + 24 + 24 * (2 - 1)), null);
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
            switch (commandCode) {
                case 20://up
                    choose--;
                    break;
                case 41://down
                    choose++;
                    break;
                case 42://Enter
                    if (choose == 0) {
                        sceneController.changeScene(new IntroductionScene(sceneController));
                    }
                    if (choose == 1) {
                        sceneController.changeScene(new StartScene(sceneController));
                    }
                    break;
                case 10://左
                    level = true;
                    sceneController.changeScene(new IntroBinEasyScene(sceneController));
                    break;
                case 43://右
                    level = false;
                    sceneController.changeScene(new IntroBinHardScene(sceneController));
                    break;
            }
        }

        @Override
        public void keyTyped(char c, long trigTime) {
        }
    }
}
