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
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import pkg20200326.gameproject.Rank.Rank;
import pkg20200326.gameproject.Rank.RankHardArray;
import pkg20200326.gameproject.controller.ImagePath;
import pkg20200326.gameproject.controller.ImageResourceController;
import pkg20200326.gameproject.util.CommandSolver;

/**
 *
 * @author chant
 */
public class ThirdHardScene extends Scene {

    private BufferedImage img;
    private RankHardArray ranks;
    private boolean level;

    public ThirdHardScene(SceneController sceneController) {
        super(sceneController);
    }

    @Override
    public void sceneBegin() {
        this.img = ImageResourceController.getInstance().tryGetImage(ImagePath.RANKH);
        this.level = true;
    }

    @Override
    public void sceneUpdate() {

    }

    @Override
    public void sceneEnd() {

    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(img, 0, 0, 1680, 900, null);
        Font rank = new Font(Font.MONOSPACED, Font.BOLD, 35);
        g.setColor(Color.BLACK);
        g.setFont(rank);
        ranks = read("src/rankH.txt");
        if (this.ranks.length() >= 1) {
            for (int i = 0; i < this.ranks.length(); i++) {
                g.drawString(String.valueOf(120 - Integer.parseInt(ranks.get(i).toString().split(",")[0])), 900, 400 + (100 * i));
                g.drawString(ranks.get(i).toString().split(",")[1], 700, 400 + (100 * i));
                g.drawString(ranks.get(i).toString().split(",")[2], 1070, 400 + (100 * i));
            }
        }
    }

    public RankHardArray read(String fileName) {
        BufferedReader br;
        RankHardArray arr = new RankHardArray();
        try {
            br = new BufferedReader(new FileReader(fileName));
            while (br.ready()) {
                String str = br.readLine();
                String[] tmp = str.split(",");
                Rank ra = new Rank(Integer.parseInt(tmp[0]),
                        (tmp[1]),
                        Integer.parseInt(tmp[2]));
                arr.add(ra);
            }
            br.close();
        } catch (IOException ex) {
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
                bw.flush();
                bw.close();
            } catch (IOException e) {
            }
        }
        return arr;
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
            switch (commandCode) {
                case 42://Enter
                    sceneController.changeScene(new SecondScene(sceneController));
                    break;
                case 20://上
                    level = true;
                    sceneController.changeScene(new ThirdEasyScene(sceneController));
                    break;
                case 41://下
                    level = false;
                    sceneController.changeScene(new ThirdHardScene(sceneController));
                    break;
            }
        }

        @Override
        public void keyTyped(char w, long trigTime) {

        }
    }
}
