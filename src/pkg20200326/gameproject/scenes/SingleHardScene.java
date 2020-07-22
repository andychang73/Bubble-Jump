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
import java.util.ArrayList;
import pkg20200326.gameproject.Rank.*;
import pkg20200326.gameproject.controller.AudioPath;
import pkg20200326.gameproject.controller.AudioResourceController;
import pkg20200326.gameproject.controller.ImagePath;
import pkg20200326.gameproject.controller.ImageResourceController;
import pkg20200326.gameproject.gameobject.AI;
import pkg20200326.gameproject.gameobject.Actor;
import pkg20200326.gameproject.gameobject.BubbleInterface.CreateGreenBubble;
import pkg20200326.gameproject.gameobject.BubbleInterface.CreateYellowBubble;
import pkg20200326.gameproject.gameobject.bubbles.*;
import pkg20200326.gameproject.util.*;

/**
 *
 * @author chant
 */
public class SingleHardScene extends Scene {

    private BufferedImage img;
    private Clock clock;
    private BackGround backGround1;
    private BackGround backGround2;
    private Actor actor1;
    private AI ai;
    private Camera camera1;
    private Camera camera2;
    private ArrayList<Bubble> actor1_bubbleList;
    private Mark mark1;
    private Mark mark2;
    private int actor1_bubbleDelayFrame;
    private int actor1_yellowDelayFrame;
    private int rainLimit;
    private Delay delay;
    private int winDelay;
    private String nameA;
    private RankHardArray ranks;

    public SingleHardScene(SceneController sceneController, String nameA) {
        super(sceneController);
        this.nameA = nameA;
    }

    @Override
    public void sceneBegin() {
        this.img = ImageResourceController.getInstance().tryGetImage(ImagePath.BUBBLE);
        this.clock = new Clock();
        this.camera1 = new Camera();
        this.camera2 = new Camera();
        this.backGround1 = new BackGround(ImagePath.BACKA1, ImagePath.ENDA, ImagePath.BACKA2, 0, 4000, 840, 1160);
        this.backGround2 = new BackGround(ImagePath.BACKB1, ImagePath.ENDB, ImagePath.BACKB2, 840, 4000, 840, 1160);
        this.actor1 = new Actor(0, 300, Global.MAP_BOTTOM_Y - Actor.HEIGHT / 2, Actor.HEIGHT, Actor.WIDTH, Global.A_LEFT, 10, Actor.ACTOR1_LEFT_FRAME, Actor.ACTOR1_RIGHT_FRAME);
        this.ai = new AI(1, 1000, Global.MAP_BOTTOM_Y - Actor.HEIGHT / 2, Actor.HEIGHT, Actor.WIDTH, Global.A_LEFT, 10, Actor.ACTOR2_LEFT_FRAME, Actor.ACTOR2_RIGHT_FRAME);
        this.actor1_bubbleList = new ArrayList<>();
        this.mark1 = new Mark(250, 50);
        this.mark2 = new Mark(1150, 50);
        this.actor1_bubbleDelayFrame = 60;
        this.actor1_yellowDelayFrame = 240;
        this.delay = new Delay(60);
        this.winDelay = 2;
        this.ranks = new RankHardArray();
    }

    @Override
    public void sceneUpdate() {
        camera1.update(actor1.getY());
        camera2.update(ai.getY());
        mark1.update(actor1.getY());
        mark2.update(ai.getY());
        backGround1.update();
        backGround2.update();
        actor1.update();
        ai.update();

        ////////////////////Bubble List////////////////////
        for (int i = 0; i < actor1_bubbleList.size(); i++) {
            if (actor1_bubbleList.get(i).getCounter() >= Bubble.REMOVE_COUNT) {
                actor1_bubbleList.remove(i);
                i--;
                AudioResourceController.getInstance().play(AudioPath.BLOP);
                continue;
            }
            if(actor1_bubbleList.get(i) != actor1.getCurrentBubble())
            {
                actor1_bubbleList.get(i).setIsStretched(false);
            }
            actor1_bubbleList.get(i).update();
        }
        for (int i = 0; i < ai.getBubbleList().size(); i++) {

            if (ai.getBubbleList().get(i).getCounter() >= Bubble.REMOVE_COUNT) {
                ai.getBubbleList().remove(i);
                i--;
                AudioResourceController.getInstance().play(AudioPath.BLOP);
                continue;
            }
            if(ai.getBubbleList().get(i) != ai.getCurrentBubble())
            {
                ai.getBubbleList().get(i).setIsStretched(false);
            }
            ai.getBubbleList().get(i).update();
        }

        ////////////////////Actor Bubble Collision////////////////////
        for (int i = 0; i < actor1_bubbleList.size(); i++) {
            actorCollision(actor1, actor1_bubbleList.get(i));
        }
        for (int i = 0; i < ai.getBubbleList().size(); i++) {
            actorCollision(ai, ai.getBubbleList().get(i));
        }
        if (this.mark1.getHeightData() >= 5000) {
            ranks = read("src/rankH.txt");
            ranks.add(new Rank(this.clock.getPlayTime(), this.nameA, this.mark1.getHeightData()));
            write(ranks, "src/rankH.txt");
            sceneController.changeScene(new SinTreasureScene(sceneController, 120 - this.clock.getPlayTime(), this.nameA, this.mark1.getHeightData(), this.mark2.getHeightData()));
        }

        if (this.mark2.getHeightData() >= 5000) {
            ranks = read("src/rankH.txt");
            ranks.add(new Rank(this.clock.getPlayTime(), this.nameA, this.mark1.getHeightData()));
            write(ranks, "src/rankH.txt");
            sceneController.changeScene(new SinTreasureScene(sceneController, 120 - this.clock.getPlayTime(), this.nameA, this.mark1.getHeightData(), this.mark2.getHeightData()));
        }
        clock.update();
        if (this.clock.getPlayTime() == 0) {
            ranks = read("src/rankH.txt");
            ranks.add(new Rank(this.clock.getPlayTime(), this.nameA, this.mark1.getHeightData()));
            write(ranks, "src/rankH.txt");
            sceneController.changeScene(new SinTreasureScene(sceneController, 120 - this.clock.getPlayTime(), this.nameA, this.mark1.getHeightData(), this.mark2.getHeightData()));
        }
        actor1_bubbleDelayFrame++;
        actor1_yellowDelayFrame++;
    }

    @Override
    public void sceneEnd() {
    }

    @Override
    public void paint(Graphics g) {
        backGround1.paint(g, camera1.getCameraTopY());
        backGround2.paint(g, camera2.getCameraTopY());
        if (actor1_yellowDelayFrame >= 240) {
            g.drawImage(img, 0, 0, 25, 25, 400, 385, 800, 770, null);
        }
        ////////////////////Bubble List////////////////////
        for (int i = 0; i < actor1_bubbleList.size(); i++) {
            actor1_bubbleList.get(i).paint(g, camera1.getCameraTopY(), camera1.getCameraBottomY());
        }
        for (int i = 0; i < ai.getBubbleList().size(); i++) {
            ai.getBubbleList().get(i).paint(g, camera2.getCameraTopY(), camera2.getCameraBottomY());
        }

        clock.paint(g);
        mark1.paint(g);
        mark2.paint(g);
        actor1.paint(g, camera1.getCameraTopY(), camera1.getCameraBottomY());
        ai.paint(g, camera2.getCameraTopY(), camera2.getCameraBottomY());
        Font name = new Font(Font.MONOSPACED, Font.BOLD, 35);
        g.setColor(Color.BLACK);
        g.setFont(name);
        g.drawString(this.nameA, 270, 50);
    }

    private void actorCollision(Actor actor, Bubble bubble) {
        int isCollided = actor.isCollided(bubble);
        if (isCollided == 1) //collide left
        {
            actor.revertMoveLeft(-1 * actor.cCounter.offsetX);
        }
        if (isCollided == 2) // collide right
        {
            actor.revertMoveRight(-1 * actor.cCounter.offsetX);
        }
        if (isCollided == 3) // collide on top of bubble
        {
            actor.setCurrentBubble(bubble);
            actor.getCurrentBubble().setIsStretched(true);
            actor.setCurrentGround(actor.getCurrentBubble().getTop());
            if (!actor.getCurrentBubble().getType().equals("YellowBubble")
                    && actor.stayInFrame()) {
                actor.offset(bubble.getDx(), bubble.getDy());
            }
        }
        if (actor.getCurrentGround() != Global.MAP_BOTTOM_Y && //fall when bottom not collided with top of current bubble
                !isStandOnCurrentBubble(actor)) {
            actor.getCurrentBubble().setIsStretched(false);
            actor.setCurrentGroundToMapBottom(Global.MAP_BOTTOM_Y);
            actor.setFalling(true);
        }
        if (actor.getCurrentGround() != Global.MAP_BOTTOM_Y
                && actor.getCurrentBubble().getCounter() > Bubble.POP_COUNT) //fall to the ground when current bubble popped
        {
            actor.setCurrentGroundToMapBottom(Global.MAP_BOTTOM_Y);
            actor.setFalling(true);
        }
    }
    private boolean isStandOnCurrentBubble(Actor actor) {
        int isCollided = actor.isCollided(actor.getCurrentBubble());
        if (isCollided == 3) {
            return true;
        }
        return false;
    }
    private void createBubble(Actor actor, ArrayList<Bubble> bubbleList) {
        Bubble bubble = Bubble.createBubble(new CreateGreenBubble(), actor);
        bubbleList.add(bubble);
    }
    public void createYellowBubble(Actor actor, ArrayList<Bubble> bubbleList) {
        Bubble bubble = Bubble.createBubble(new CreateYellowBubble(), actor);
        bubbleList.add(bubble);
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

    public void write(RankHardArray ranks, String fileName) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
            for (int i = 0; i < ranks.length(); i++) {
                Rank ra = ranks.get(i);
                String tmp = ra.getSec() + "," + ra.getNameA() + "," + ra.getHeightDataA();
                bw.append(tmp);
                bw.newLine();
            }
            bw.flush();
            bw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
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

    private boolean actor1_isJumpPressed = false;

    public class MyKeyListener implements CommandSolver.KeyListener {

        @Override
        public void keyPressed(int commandCode, long trigTime) {
            switch (commandCode) {
                //actor 1
                case Global.A_LEFT:
                    actor1.setXVel(Actor.LEFT_SPEED);
                    break;
                case Global.A_RIGHT:
                    actor1.setXVel(Actor.RIGHT_SPEED);
                    break;
                case Global.A_JUMP:
                    if (!actor1.getIsJump() && !actor1_isJumpPressed) {
                        actor1_isJumpPressed = true;
                        actor1.setYVel(Actor.JUMP_STRENGTH);
                        AudioResourceController.getInstance().play(AudioPath.JUMP);
                    }
                    break;
                case Global.A_BUBBLES:
                    if (actor1_bubbleDelayFrame >= 30) {
                        createBubble(actor1, actor1_bubbleList);
                        actor1_bubbleDelayFrame = 0;
                    }
                    break;
                case Global.A_YELLOW_BUBBLE:
                    if (actor1_yellowDelayFrame >= 240) {
                        createYellowBubble(actor1, actor1_bubbleList);
                        actor1_yellowDelayFrame = 0;
                    }
                    break;
            }
        }

        @Override
        public void keyReleased(int commandCode, long trigTime) {
            switch (commandCode) {
                //actor 1
                case Global.A_LEFT:
                    actor1.setXVel(0);
                    break;
                case Global.A_RIGHT:
                    actor1.setXVel(0);
                    break;
                case Global.A_JUMP:
                    actor1_isJumpPressed = false;
                    break;
            }
        }

        @Override
        public void keyTyped(char w, long trigTime) {
        }
    }
}
