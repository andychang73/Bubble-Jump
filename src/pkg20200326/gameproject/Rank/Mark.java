/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg20200326.gameproject.Rank;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import pkg20200326.gameproject.controller.ImagePath;
import pkg20200326.gameproject.controller.ImageResourceController;
import pkg20200326.gameproject.util.Global;

/**
 *
 * @author chant
 */
public class Mark {

    private int x;
    private int y;
    private int heightData;
    private BufferedImage img;

    public Mark(int x, int y) {
        this.img = ImageResourceController.getInstance().tryGetImage(ImagePath.HEI);
        this.x = x;
        this.y = y;
        this.heightData = 0;
    }

    public void setHeightData(float actorY) {
        if (actorY == 4966) {
            actorY = 4973;
        }
        this.heightData = (int) ((5000 - Global.UNIT_Y + 7) - actorY);
    }

    public int getHeightData() {
        return this.heightData;
    }

    public void update(float actorY) {
        setHeightData(actorY);
    }

    public void paint(Graphics g) {
        Font time = new Font(Font.MONOSPACED, Font.BOLD, 35);
        g.setColor(Color.BLACK);
        g.setFont(time);
        g.drawImage(img, x, 30, null);
        g.drawString(String.valueOf(this.heightData), x + 150, 97);
    }
}
