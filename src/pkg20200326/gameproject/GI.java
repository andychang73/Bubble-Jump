/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg20200326.gameproject;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import pkg20200326.gameproject.GameKernel.GameInterface;
import pkg20200326.gameproject.scenes.MainScene;
import pkg20200326.gameproject.scenes.SceneController;
import pkg20200326.gameproject.scenes.SecondScene;
import pkg20200326.gameproject.util.CommandSolver;
import pkg20200326.gameproject.util.CommandSolver.KeyListener;
import pkg20200326.gameproject.util.CommandSolver.MouseCommandListener;

/**
 *
 * @author salesengineer4891
 */
public class GI implements KeyListener, MouseCommandListener,GameInterface {

    private SceneController sceneController;
    
    public GI() {
        this.sceneController = new SceneController();
        this.sceneController.changeScene(new SecondScene(this.sceneController));
    }
    
    public void update() {
        this.sceneController.sceneUpdate();
    }

    @Override
    public void paint(Graphics g) {
        this.sceneController.paint(g);
    }
     @Override
    public void keyPressed(int commandCode, long trigTime) {
        if (this.sceneController.getKL() != null) {
            this.sceneController.getKL().keyPressed(commandCode, trigTime);
        }
    }

    @Override
    public void keyReleased(int commandCode, long trigTime) {
        if (this.sceneController.getKL() != null) {
            this.sceneController.getKL().keyReleased(commandCode, trigTime);
        }
    }

    @Override
    public void keyTyped(char c, long trigTime) {
        if (this.sceneController.getKL() != null) {
            this.sceneController.getKL().keyTyped(c, trigTime);
        }
    }

    @Override
    public void mouseTrig(MouseEvent e, CommandSolver.MouseState state, long trigTime) {
        if (state != null && this.sceneController.getML() != null) {
            this.sceneController.getML().mouseTrig(e, state, trigTime);
        }
    }

}
