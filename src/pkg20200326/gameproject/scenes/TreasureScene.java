/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg20200326.gameproject.scenes;

import pkg20200326.gameproject.Rank.Coin;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import pkg20200326.gameproject.controller.ImagePath;
import pkg20200326.gameproject.controller.ImageResourceController;
import pkg20200326.gameproject.gameobject.Renderer;
import pkg20200326.gameproject.util.CommandSolver;
import pkg20200326.gameproject.util.Delay;

/**
 *
 * @author chant
 */
public class TreasureScene extends Scene {

    private BufferedImage imgHouse;
    private BufferedImage imgTreasure;
    private BufferedImage imgWin;
    private BufferedImage imgHei;
    private BufferedImage imgTrea;
    private int treasureOpen;
    private Delay treasureDelay;
    private Delay delay;
    private int waitTime;
    private int sec;
    private int heightA;
    private int heightB;
    private ArrayList<Coin> coins;
    private boolean win;
    private Delay treaDelay;
    private int treaTime;
    private Delay winDelay;
    private int wins;
    private Renderer ren1;
    private Renderer ren2;

    public TreasureScene(SceneController sceneController, int sec, int heightA, int heightB) {
        super(sceneController);
        this.sec = sec;
        if (heightA >= 5000) {
            this.heightA = 5000;
        } else {
            this.heightA = heightA;
        }
        if (heightB >= 5000) {
            this.heightB = 5000;
        } else {
            this.heightB = heightB;
        }
    }

    @Override
    public void sceneBegin() {
        this.delay = new Delay(60);
        this.delay.start();
        this.waitTime = 2;
        this.imgHouse = ImageResourceController.getInstance().tryGetImage(ImagePath.HOUSE);
        this.imgTreasure = ImageResourceController.getInstance().tryGetImage(ImagePath.TREASURE);
        this.imgWin = ImageResourceController.getInstance().tryGetImage(ImagePath.WIN);
        this.imgHei = ImageResourceController.getInstance().tryGetImage(ImagePath.HEI);
        this.imgTrea = ImageResourceController.getInstance().tryGetImage(ImagePath.TREA);
        this.treaDelay = new Delay(10);
        this.treaTime = 0;
        this.treasureDelay = new Delay(5);
        this.treasureOpen = 0;
        this.coins = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            this.coins.add(new Coin(i));
        }
        this.win = false;
        this.winDelay = new Delay(15);
        this.wins = 0;
        if (this.heightA > this.heightB) {
            this.ren1 = new Renderer(-1, Renderer.HEAD, 10, 0);
            this.ren2 = new Renderer(1, Renderer.STAND, 10, 1);
        }
        if (this.heightB > this.heightA) {
            this.ren1 = new Renderer(-1, Renderer.STAND, 10, 0);
            this.ren2 = new Renderer(1, Renderer.HEAD, 10, 1);
        }
        if (this.heightA == this.heightB) {
            this.ren1 = new Renderer(-1, Renderer.HEAD, 10, 0);
            this.ren2 = new Renderer(1, Renderer.HEAD, 10, 1);
        }
    }

    @Override
    public void sceneUpdate() {
        if (this.delay.isTrig()) {
            this.waitTime--;
        }
        this.treaDelay.start();
        if (this.treaDelay.isTrig()) {
            this.treaTime++;
        }
        if (this.treaTime > 5) {
            this.treaDelay.stop();
            this.treasureDelay.start();
            if (this.treasureDelay.isTrig()) {
                this.treasureOpen++;
                if (this.treasureOpen >= 5) {
                    this.treasureOpen = 5;
                    this.treasureDelay.stop();
                }
            }
        }
        if (this.treasureOpen == 5) {
            this.win = true;
            for (int i = 0; i < this.coins.size(); i++) {
                if (this.wins >= i + 1) {
                      this.coins.get(i).update(i);
                }
            }
        }
        this.ren1.update();
        this.ren2.update();
    }

    @Override
    public void sceneEnd() {

    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(imgHouse, 0, 0, 1680, 900, null);
        Font score = new Font(Font.MONOSPACED, Font.BOLD, 35);
        g.setColor(Color.BLACK);
        g.setFont(score);
        g.drawImage(imgHei, 150, 30, null);
        g.drawString(String.valueOf(this.heightA), 300, 97);
        g.drawImage(imgHei, 1300, 30, null);
        g.drawString(String.valueOf(this.heightB), 1450, 97);
        if (this.heightA > this.heightB) {
            this.ren1.paint(g, 530, 650, 68, 68);
            this.ren2.paint(g, 1220, 850, 68, 68);
        }
        if (this.heightB > this.heightA) {
            this.ren1.paint(g, 380, 850, 68, 68);
            this.ren2.paint(g, 1000, 650, 68, 68);
        }
        if (this.heightA == this.heightB) {
            this.ren1.paint(g, 530, 650, 68, 68);
            this.ren2.paint(g, 1000, 650, 68, 68);
        }
        g.drawImage(imgTrea, 550, 450, 700, 600,
                16 * treaTime, 0, 16 + 16 * treaTime, 16, null);
        if (this.treaTime > 5) {
            g.drawImage(imgTreasure, 550, 450, 700, 600,
                    16 * treasureOpen, 0, 16 + 16 * treasureOpen, 16, null);
        }
        if (this.win == true) {
            this.winDelay.start();
            if (this.winDelay.isTrig()) {
                this.wins++;
            }
            for (int i = 0; i < this.coins.size(); i++) {
                if (this.wins >= i + 1) {
                    this.coins.get(i).paint(g);
                }
            }
        }
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
                    case 42://Enter
                        sceneController.changeScene(new SecondScene(sceneController));
                        break;
                }
            }
        }

        @Override
        public void keyTyped(char c, long trigTime) {
        }
    }
}
