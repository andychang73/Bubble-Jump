package pkg20200326.gameproject.scenes;

import pkg20200326.gameproject.gameobject.bubbles.*;
import java.util.ArrayList;
import java.awt.Graphics;
import pkg20200326.gameproject.controller.ImagePath;
import pkg20200326.gameproject.gameobject.*;
import pkg20200326.gameproject.util.*;
import pkg20200326.gameproject.Rank.Mark;
import pkg20200326.gameproject.controller.AudioPath;
import pkg20200326.gameproject.controller.AudioResourceController;
import pkg20200326.gameproject.gameobject.BubbleInterface.CreateBonusBubble;
import pkg20200326.gameproject.gameobject.BubbleInterface.CreateGreenBubble;
import pkg20200326.gameproject.gameobject.BubbleInterface.CreatePinkBubble;
import pkg20200326.gameproject.gameobject.BubbleInterface.CreateRedBubble;
import pkg20200326.gameproject.gameobject.BubbleInterface.CreateYellowBubble;

/**
 *
 * @author salesengineer4891
 */
public class MainScene extends Scene {

    private Clock clock;
    private BackGround backGround1;
    private BackGround backGround2;
    private Actor actor1;
    private Actor actor2;
    private Camera camera1;
    private Camera camera2;
    private RainPool actor1_rainPool;
    private RainPool actor2_rainPool;
    private ArrayList<Snowflake> actor1_snowflakeList;
    private ArrayList<Snowflake> actor2_snowflakeList;
    private ArrayList<Bubble> actor1_bubbleList;
    private ArrayList<Bubble> actor2_bubbleList;
    private Mark mark1;
    private Mark mark2;
    private int actor1_rainDelayFrame;
    private int actor2_rainDelayFrame;
    private int actor1_bubbleDelayFrame;
    private int actor2_bubbleDelayFrame;
    private int rainLimit;
    private ArrayList<Fruit> fruits1;
    private ArrayList<Fruit> fruits2;

    public MainScene(SceneController sceneController) {
        super(sceneController);
    }

    @Override
    public void sceneBegin() {
        this.clock = new Clock();
        this.camera1 = new Camera();
        this.camera2 = new Camera();
        this.backGround1 = new BackGround(ImagePath.BACKA1, ImagePath.ENDA, ImagePath.BACKA2, 0, 4000, 840, 1160);
        this.backGround2 = new BackGround(ImagePath.BACKB1, ImagePath.ENDB, ImagePath.BACKB2, 840, 4000, 840, 1160);
        this.actor1 = new Actor(0, 300, Global.MAP_BOTTOM_Y - Actor.HEIGHT / 2, Actor.HEIGHT, Actor.WIDTH, Global.A_LEFT, 10, Actor.ACTOR1_LEFT_FRAME, Actor.ACTOR1_RIGHT_FRAME);
        this.actor2 = new Actor(1, 1000, Global.MAP_BOTTOM_Y - Actor.HEIGHT / 2, Actor.HEIGHT, Actor.WIDTH, Global.A_LEFT, 10, Actor.ACTOR2_LEFT_FRAME, Actor.ACTOR2_RIGHT_FRAME);
        this.actor1_rainPool = new RainPool();
        this.actor2_rainPool = new RainPool();
        this.actor1_bubbleList = new ArrayList<>();
        this.actor2_bubbleList = new ArrayList<>();
        this.actor1_snowflakeList = new ArrayList<>();
        this.actor2_snowflakeList = new ArrayList<>();
        this.mark1 = new Mark(250, 50);
        this.mark2 = new Mark(1150, 50);
        this.actor1_rainDelayFrame = 90;
        this.actor2_rainDelayFrame = 90;
        this.actor1_bubbleDelayFrame = 60;
        this.actor2_bubbleDelayFrame = 60;
        this.rainLimit = 30;
        this.fruits1 = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            this.fruits1.add(new Fruit(i, Global.random(100, 700), (Global.random(100, 5000)), 50f, 50f));
        }
        this.fruits2 = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            this.fruits2.add(new Fruit(i + 4, Global.random(900, 1500), (Global.random(100, 5000)), 50f, 50f));
        }
    }

    @Override
    public void sceneUpdate() {
        camera1.update(actor1.getY());
        camera2.update(actor2.getY());
        mark1.update(actor1.getY());
        mark2.update(actor2.getY());
        backGround1.update();
        backGround2.update();
        actor1.update();
        actor2.update();
        for (int i = 0; i < this.fruits1.size(); i++) {
            this.fruits1.get(i).update();
        }
        for (int i = 0; i < this.fruits1.size(); i++) {
            if (actor1.isCollided(this.fruits1.get(i)) != 0) {
                actor1.setYVel(Actor.JUMP_BONUS);
                AudioResourceController.getInstance().play(AudioPath.JUMP);
                this.fruits1.remove(i);
                Bubble bubble = Bubble.createBubble(new CreateBonusBubble(), actor1);
                actor1_bubbleList.add(bubble);

            }
        }
        for (int i = 0; i < this.fruits2.size(); i++) {
            this.fruits2.get(i).update();
        }
        for (int i = 0; i < this.fruits2.size(); i++) {
            if (actor2.isCollided(this.fruits2.get(i)) != 0) {
                actor2.setYVel(Actor.JUMP_BONUS);
                AudioResourceController.getInstance().play(AudioPath.JUMP);
                this.fruits2.remove(i);
                Bubble bubble = Bubble.createBubble(new CreateBonusBubble(), actor2);
                actor2_bubbleList.add(bubble);
            }
        }
        ////////////////////Time stop////////////////////
        if (actor1.getTimeStopCounter() != -1) {
            actor1.startTimeCounter();
        }
        if (actor1.getTimeStopCounter() >= 300) {
            endTimeStop(actor1_rainPool);
            actor1.setTimeStopCounter(-1);
        }
        if (actor2.getTimeStopCounter() != -1) {
            actor2.startTimeCounter();
        }
        if (actor2.getTimeStopCounter() >= 300) {
            endTimeStop(actor2_rainPool);
            actor2.setTimeStopCounter(-1);
        }

        ////////////////////Rain List////////////////////
        for (int i = 0; i < this.actor1_rainPool.getTotal(); i++) {
            if (actor1_rainPool.get(i).getCounter() >= Rain.endCount) {
                actor1_rainPool.get(i).recycle();
                actor1_rainPool.swap(i);
                AudioResourceController.getInstance().play(AudioPath.WATER);
            }
            this.actor1_rainPool.get(i).update();
        }
        for (int i = 0; i < this.actor2_rainPool.getTotal(); i++) {
            if (actor2_rainPool.get(i).getCounter() >= Rain.endCount) {
                actor2_rainPool.get(i).recycle();
                actor2_rainPool.swap(i);
                AudioResourceController.getInstance().play(AudioPath.WATER);
            }
            this.actor2_rainPool.get(i).update();
        }

        ////////////////////Snowflake List////////////////////
        for (int i = 0; i < actor1_snowflakeList.size(); i++) {
            if (actor1_snowflakeList.get(i).getCounter() >= Snowflake.endCount) {
                this.actor1_snowflakeList.remove(i);
                i--;
                AudioResourceController.getInstance().play(AudioPath.ICE);
                continue;
            }
            actor1_snowflakeList.get(i).update();
        }
        for (int i = 0; i < actor2_snowflakeList.size(); i++) {
            if (actor2_snowflakeList.get(i).getCounter() >= Snowflake.endCount) {
                this.actor2_snowflakeList.remove(i);
                i--;
                AudioResourceController.getInstance().play(AudioPath.ICE);
                continue;
            }
            actor2_snowflakeList.get(i).update();
        }

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
            for (int m = 0; m < actor2_snowflakeList.size(); m++) {
                if (actor1_bubbleList.get(i).getCounter() < Bubble.POP_COUNT
                        && actor2_snowflakeList.get(m).ObjCollision(actor1_bubbleList.get(i))) {
                    actor1_bubbleList.get(i).setCounter(Bubble.POP_COUNT);
                }
            }
            for (int k = 0; k < actor1_rainPool.getTotal(); k++) {
                if (actor1_bubbleList.get(i).getCounter() < Bubble.POP_COUNT
                        && !actor1_bubbleList.get(i).getType().equals("YellowBubble")
                        && actor1_rainPool.get(k).ObjCollision(actor1_bubbleList.get(i))) {
                    actor1_bubbleList.get(i).setCounter(Bubble.POP_COUNT);
                }
            }
            actor1_bubbleList.get(i).update();
        }
        for (int i = 0; i < actor2_bubbleList.size(); i++) {
            if (actor2_bubbleList.get(i).getCounter() >= Bubble.REMOVE_COUNT) {
                actor2_bubbleList.remove(i);
                i--;
                AudioResourceController.getInstance().play(AudioPath.BLOP);
                continue;
            }
            if(actor2_bubbleList.get(i) != actor2.getCurrentBubble())
            {
                actor2_bubbleList.get(i).setIsStretched(false);
            }
            for (int m = 0; m < actor1_snowflakeList.size(); m++) {
                if (actor2_bubbleList.get(i).getCounter() < Bubble.POP_COUNT
                        && actor1_snowflakeList.get(m).ObjCollision(actor2_bubbleList.get(i))) {
                    actor2_bubbleList.get(i).setCounter(Bubble.POP_COUNT);
                }
            }
            for (int k = 0; k < actor2_rainPool.getTotal(); k++) {
                if (actor2_bubbleList.get(i).getCounter() < Bubble.POP_COUNT
                        && !actor2_bubbleList.get(i).getType().equals("YellowBubble")
                        && actor2_rainPool.get(k).ObjCollision(actor2_bubbleList.get(i))) {
                    actor2_bubbleList.get(i).setCounter(Bubble.POP_COUNT);
                }
            }
            actor2_bubbleList.get(i).update();
        }

        ////////////////////Actor Bubble Collision////////////////////
        for (int i = 0; i < actor1_bubbleList.size(); i++) {
            actorCollision(actor1, actor2, actor1_bubbleList.get(i),
                    actor1_snowflakeList, actor1_rainPool);
        }
        for (int i = 0; i < actor2_bubbleList.size(); i++) {
            actorCollision(actor2, actor1, actor2_bubbleList.get(i),
                    actor2_snowflakeList, actor2_rainPool);
        }
        //////////////////Create Bubble////////////////////
        if (actor1_bubbleDelayFrame++ >= 60 && !actor1.getIsJump()) {
            createBubble(actor1, actor1_bubbleList);
            actor1_bubbleDelayFrame = 0;
        }
        if (actor2_bubbleDelayFrame++ >= 60 && !actor2.getIsJump()) {
            createBubble(actor2, actor2_bubbleList);
            actor2_bubbleDelayFrame = 0;
        }
        ////////////////////Create Rain////////////////////
        if (actor1_rainDelayFrame++ >= 90 && actor1.getTimeStopCounter() == -1) {
            if (actor1_rainPool.getAlive() == actor1_rainPool.getTotal()
                    && actor1_rainPool.getTotal() < rainLimit) {
                actor1_rainPool.add(new Rain(Global.random(40, 750), 0, Global.random(20, 40), Global.random(50, 100), Global.random(2, 4)));
            }
            if (actor1_rainPool.getAlive() < actor1_rainPool.getTotal()) {
                actor1_rainPool.reset(Global.random(40, 750), 0, Global.random(2, 4));
            }
            actor1_rainDelayFrame = 0;
        }
        if (actor2_rainDelayFrame++ >= 90 && actor2.getTimeStopCounter() == -1) {
            if (actor2_rainPool.getAlive() == actor2_rainPool.getTotal()
                    && actor2_rainPool.getTotal() < rainLimit) {
                actor2_rainPool.add(new Rain(Global.random(900, 1600), 0, Global.random(20, 40), Global.random(50, 100), Global.random(2, 4)));
            }
            if (actor2_rainPool.getAlive() < actor2_rainPool.getTotal()) {
                actor2_rainPool.reset(Global.random(900, 1600), 0, Global.random(2, 4));
            }
            actor2_rainDelayFrame = 0;
        }

        if (this.mark1.getHeightData() >= 5000) {
            sceneController.changeScene(new TreasureScene(sceneController, 120 - this.clock.getPlayTime(), this.mark1.getHeightData(), this.mark2.getHeightData()));
        }
        if (this.mark2.getHeightData() >= 5000) {
            sceneController.changeScene(new TreasureScene(sceneController, 120 - this.clock.getPlayTime(), this.mark1.getHeightData(), this.mark2.getHeightData()));
        }
        clock.update();
        if (this.clock.getPlayTime() == 0) {
            sceneController.changeScene(new TreasureScene(sceneController, 120 - this.clock.getPlayTime(), this.mark1.getHeightData(), this.mark2.getHeightData()));
        }
        actor1_bubbleDelayFrame++;
        actor2_bubbleDelayFrame++;
    }

    @Override
    public void sceneEnd() {

    }

    @Override
    public void paint(Graphics g) {
        backGround1.paint(g, camera1.getCameraTopY());
        backGround2.paint(g, camera2.getCameraTopY());
        for (int i = 0; i < this.fruits1.size(); i++) {
            this.fruits1.get(i).paint(g, camera1.getCameraTopY(), camera1.getCameraBottomY());
        }
        for (int i = 0; i < this.fruits2.size(); i++) {
            this.fruits2.get(i).paint(g, camera2.getCameraTopY(), camera2.getCameraBottomY());
        }
        ////////////////////Bubble List////////////////////
        for (int i = 0; i < actor1_bubbleList.size(); i++) {
            actor1_bubbleList.get(i).paint(g, camera1.getCameraTopY(), camera1.getCameraBottomY());
        }
        for (int i = 0; i < actor2_bubbleList.size(); i++) {
            actor2_bubbleList.get(i).paint(g, camera2.getCameraTopY(), camera2.getCameraBottomY());
        }

        ////////////////////Rain List////////////////////
        for (int i = 0; i < this.actor1_rainPool.getTotal(); i++) {
            this.actor1_rainPool.get(i).paint(g, camera1.getCameraTopY(), camera1.getCameraBottomY());
        }
        for (int i = 0; i < this.actor2_rainPool.getTotal(); i++) {
            this.actor2_rainPool.get(i).paint(g, camera2.getCameraTopY(), camera2.getCameraBottomY());
        }

        ////////////////////Snowflake List////////////////////
        for (int i = 0; i < this.actor1_snowflakeList.size(); i++) {
            this.actor1_snowflakeList.get(i).paint(g, camera2.getCameraTopY(), camera2.getCameraBottomY());
        }
        for (int i = 0; i < this.actor2_snowflakeList.size(); i++) {
            this.actor2_snowflakeList.get(i).paint(g, camera1.getCameraTopY(), camera1.getCameraBottomY());
        }
        clock.paint(g);
        mark1.paint(g);
        mark2.paint(g);
        actor1.paint(g, camera1.getCameraTopY(), camera1.getCameraBottomY());
        actor2.paint(g, camera2.getCameraTopY(), camera2.getCameraBottomY());
    }

    private void actorCollision(Actor actor, Actor target, Bubble bubble,
            ArrayList<Snowflake> snowflakeList, RainPool rainPool) {
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
            if (actor.getCurrentBubble().getType().equals("RedBubble")
                    && !actor.getCurrentBubble().getIsMeteorSummoned()) {
                actor.getCurrentBubble().setIsMeteorSummoned(true);
                snowflakeList.add(new Snowflake(target.getX(), 0, 100, 100, 4f));
            }
            if (actor.getCurrentBubble().getType().equals("PinkBubble")
                    && actor.getTimeStopCounter() == -1) {
                timeStop(rainPool);
                actor.setTimeStopCounter(0);
            }
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

    private boolean checkPinkBubble(ArrayList<Bubble> bubbleList) {
        for (int i = 0; i < bubbleList.size(); i++) {
            if (bubbleList.get(i).getType().equals("PinkBubble")) {
                return true;
            }
        }
        return false;
    }

    private void timeStop(RainPool rainPool) {
        for (int i = 0; i < rainPool.getTotal(); i++) {
            rainPool.get(i).timeStop();
        }
    }

    private void endTimeStop(RainPool rainPool) {
        for (int i = 0; i < rainPool.getTotal(); i++) {
            rainPool.get(i).endTimeStop();
        }
    }

    private void createBubble(Actor actor, ArrayList<Bubble> bubbleList) {
        int bubbleType = (int) Global.random(1, 100);
        if (!checkPinkBubble(bubbleList) && actor.getTimeStopCounter() == -1) {
            if (bubbleType <= 10) {
                Bubble bubble = Bubble.createBubble(new CreateRedBubble(), actor);
                bubbleList.add(bubble);
                return;
            }
            if (bubbleType > 10 && bubbleType <= 35) {
                Bubble bubble = Bubble.createBubble(new CreatePinkBubble(), actor);
                bubbleList.add(bubble);
                return;
            }
            if (bubbleType > 35 && bubbleType <= 60) {
                Bubble bubble = Bubble.createBubble(new CreateYellowBubble(), actor);
                bubbleList.add(bubble);
                return;
            }
            if (bubbleType > 60) {
                Bubble bubble = Bubble.createBubble(new CreateGreenBubble(), actor);
                bubbleList.add(bubble);
            }
        } else {
            if (bubbleType <= 10) {
                Bubble bubble = Bubble.createBubble(new CreateRedBubble(), actor);
                bubbleList.add(bubble);
                return;
            }
            if (bubbleType > 10 && bubbleType <= 35) {
                Bubble bubble = Bubble.createBubble(new CreateYellowBubble(), actor);
                bubbleList.add(bubble);
                return;
            }
            if (bubbleType > 35) {
                Bubble bubble = Bubble.createBubble(new CreateGreenBubble(), actor);
                bubbleList.add(bubble);
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

    private boolean actor1_isJumpPressed = false;
    private boolean actor2_isJumpPressed = false;

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

                //actor 2  
                case Global.B_LEFT:
                    actor2.setXVel(Actor.LEFT_SPEED);
                    break;
                case Global.B_RIGHT:
                    actor2.setXVel(Actor.RIGHT_SPEED);
                    break;
                case Global.B_JUMP:
                    if (!actor2.getIsJump() && !actor2_isJumpPressed) {
                        actor2_isJumpPressed = true;
                        actor2.setYVel(Actor.JUMP_STRENGTH);
                        AudioResourceController.getInstance().play(AudioPath.JUMP);
                    }
                    break;
                case Global.B_BUBBLES:
                    if (actor2_bubbleDelayFrame >= 30) {
                        createBubble(actor2, actor2_bubbleList);
                        actor2_bubbleDelayFrame = 0;
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

                //actor 2
                case Global.B_LEFT:
                    actor2.setXVel(0);
                    break;
                case Global.B_RIGHT:
                    actor2.setXVel(0);
                    break;
                case Global.B_JUMP:
                    actor2_isJumpPressed = false;
                    break;
            }
        }

        @Override
        public void keyTyped(char c, long trigTime) {

        }
    }
}
