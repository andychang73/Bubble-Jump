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
public class NameScene extends Scene {

    private BufferedImage img1;
    private BufferedImage img2;
    private BufferedImage img3;
    private BufferedImage img4;
    private BufferedImage img5;
    private BufferedImage img22;
    private BufferedImage img32;
    private BufferedImage img42;
    private BufferedImage img52;
    private BufferedImage imgHE1;
    private BufferedImage imgHE2;
    private int x1;
    private int x2;
    private int x3;
    private int x4;
    private int x5;
    private int x22;
    private int x32;
    private int x42;
    private int x52;
    private String nameA;
    private Delay delay;
    private int waitTime;
    private int choose;

    public NameScene(SceneController sceneController) {
        super(sceneController);
    }

    @Override
    public void sceneBegin() {
        this.delay = new Delay(60);
        this.delay.start();
        this.waitTime = 2;
        this.img1 = ImageResourceController.getInstance().tryGetImage(ImagePath.T1);
        this.img2 = ImageResourceController.getInstance().tryGetImage(ImagePath.T2);
        this.img3 = ImageResourceController.getInstance().tryGetImage(ImagePath.T3);
        this.img4 = ImageResourceController.getInstance().tryGetImage(ImagePath.T4);
        this.img5 = ImageResourceController.getInstance().tryGetImage(ImagePath.T5);
        this.img22 = ImageResourceController.getInstance().tryGetImage(ImagePath.T2);
        this.img32 = ImageResourceController.getInstance().tryGetImage(ImagePath.T3);
        this.img42 = ImageResourceController.getInstance().tryGetImage(ImagePath.T4);
        this.img52 = ImageResourceController.getInstance().tryGetImage(ImagePath.T5);
        this.imgHE1 = ImageResourceController.getInstance().tryGetImage(ImagePath.HE1);
        this.imgHE2 = ImageResourceController.getInstance().tryGetImage(ImagePath.HE2);
        this.x1 = 0;
        this.x2 = -1680;
        this.x3 = -1680;
        this.x4 = -1680;
        this.x5 = -1680;
        this.x22 = 0;
        this.x32 = 0;
        this.x42 = 0;
        this.x52 = 0;
        this.nameA = "";
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

        Font rank = new Font("微軟正黑體", Font.BOLD, 35);
        g.setColor(Color.BLACK);
        g.setFont(rank);
        g.drawString("進入森林前請先輸入10字以內的英文名稱：" + String.valueOf(this.nameA), 400, 500);
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
                    case 1:
                        if (nameA.length() < 10) {
                            nameA += String.valueOf('A');
                        }
                        break;
                    case 2:
                        if (nameA.length() < 10) {
                            nameA += String.valueOf('D');
                        }
                        break;
                    case 4:
                        if (nameA.length() < 10) {
                            nameA += String.valueOf('G');
                        }
                        break;
                    case 13:
                        if (nameA.length() < 10) {
                            nameA += String.valueOf('B');
                        }
                        break;
                    case 14:
                        if (nameA.length() < 10) {
                            nameA += String.valueOf('C');
                        }
                        break;
                    case 15:
                        if (nameA.length() < 10) {
                            nameA += String.valueOf('E');
                        }
                        break;
                    case 16:
                        if (nameA.length() < 10) {
                            nameA += String.valueOf('F');
                        }
                        break;
                    case 8:
                        if (nameA.length() < 10) {
                            nameA += String.valueOf('I');
                        }
                        break;
                    case 19:
                        if (nameA.length() < 10) {
                            nameA += String.valueOf('k');
                        }
                        break;
                    case 6:
                        if (nameA.length() < 10) {
                            nameA += String.valueOf('J');
                        }
                        break;
                    case 7:
                        if (nameA.length() < 10) {
                            nameA += String.valueOf('L');
                        }
                        break;
                    case 22:
                        if (nameA.length() < 10) {
                            nameA += String.valueOf('M');
                        }
                        break;
                    case 23:
                        if (nameA.length() < 10) {
                            nameA += String.valueOf('N');
                        }
                        break;
                    case 24:
                        if (nameA.length() < 10) {
                            nameA += String.valueOf('O');
                        }
                        break;
                    case 25:
                        if (nameA.length() < 10) {
                            nameA += String.valueOf('P');
                        }
                        break;
                    case 26:
                        if (nameA.length() < 10) {
                            nameA += String.valueOf('Q');
                        }
                        break;
                    case 27:
                        if (nameA.length() < 10) {
                            nameA += String.valueOf('R');
                        }
                        break;
                    case 28:
                        if (nameA.length() < 10) {
                            nameA += String.valueOf('S');
                        }
                        break;
                    case 29:
                        if (nameA.length() < 10) {
                            nameA += String.valueOf('T');
                        }
                        break;
                    case 30:
                        if (nameA.length() < 10) {
                            nameA += String.valueOf('U');
                        }
                        break;
                    case 31:
                        if (nameA.length() < 10) {
                            nameA += String.valueOf('V');
                        }
                        break;
                    case 3:
                        if (nameA.length() < 10) {
                            nameA += String.valueOf('W');
                        }
                        break;
                    case 33:
                        if (nameA.length() < 10) {
                            nameA += String.valueOf('X');
                        }
                        break;
                    case 34:
                        if (nameA.length() < 10) {
                            nameA += String.valueOf('Y');
                        }
                        break;
                    case 35:
                        if (nameA.length() < 10) {
                            nameA += String.valueOf('Z');
                        }
                        break;
                    case 12://delete鍵//去除最後一字元
                        if (nameA.length() != 0) {
                            nameA = nameA.substring(0, nameA.length() - 1);
                        }
                        break;
                    case 20://up
                        choose--;
                        break;
                    case 41://down
                        choose++;
                        break;
                    case 42://Enter
                        if (nameA.length() >= 1) {
                            if (choose == 0) {
                                sceneController.changeScene(new SingleEasyScene(sceneController, nameA));
                            }
                            if (choose == 1) {
                                sceneController.changeScene(new SingleHardScene(sceneController, nameA));
                            }
                            break;
                        }
                }
            }
        }

        @Override
        public void keyTyped(char c, long trigTime) {
        }
    }
}
