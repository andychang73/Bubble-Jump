/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg20200326.gameproject.scenes;

import java.awt.Graphics;
import pkg20200326.gameproject.util.CommandSolver;


/**
 *
 * @author salesengineer4891
 */
public class SceneController {
    private Scene currentScene;
    private CommandSolver.KeyListener kl;
    private CommandSolver.MouseCommandListener ml;

    public void changeScene(Scene scene) {
        if (currentScene != null) {
            currentScene.sceneEnd();
        }
        currentScene = scene;
        kl = currentScene.getKeyListener();
        ml = currentScene.getMouseListener();
        currentScene.sceneBegin();
    }

    public CommandSolver.KeyListener getKL() {
        return this.kl;
    }

    public CommandSolver.MouseCommandListener getML() {
        return this.ml;
    }

    public void sceneUpdate() {
        currentScene.sceneUpdate();
    }

    public void paint(Graphics g) {
        currentScene.paint(g);
    }
}
