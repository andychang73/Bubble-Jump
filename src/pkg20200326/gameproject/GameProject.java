/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg20200326.gameproject;

import javax.swing.JFrame;
import pkg20200326.gameproject.util.Global;

/**
 *
 * @author salesengineer4891
 */
public class GameProject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame j = new JFrame();
        GI gi = new GI();
        GameKernel gk = new GameKernel.Builder(gi, Global.NANO_PER_UPDATE,
                Global.LIMIT_DELTA_TIME)
                .initListener(Global.COMMANDS)
                .enableKeyboardTrack(gi)
                .keyCleanMode()
                .gen();

        j.setTitle("BUBBLE JUMP");
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setSize(Global.FRAME_X, Global.FRAME_Y);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.add(gk);
        j.setVisible(true);
        gk.run(true);
    }

}
