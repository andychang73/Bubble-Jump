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
public abstract class Scene {
    protected SceneController sceneController;
    
    public Scene(SceneController sceneController){
        this.sceneController = sceneController;
    }
    public abstract void sceneBegin();
    public abstract void sceneUpdate();
    public abstract void sceneEnd();
    public abstract void paint(Graphics g);
    public abstract CommandSolver.KeyListener getKeyListener();
    public abstract CommandSolver.MouseCommandListener getMouseListener();
}
