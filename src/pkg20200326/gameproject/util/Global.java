/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg20200326.gameproject.util;

import java.awt.event.KeyEvent;

/**
 *
 * @author salesengineer4891
 */
public class Global {

    public static final boolean IS_DEBUG = false;

    public static final int UNIT_X = 34;
    public static final int UNIT_Y = 34;
    public static final int FRAME_X = 1680;
    public static final int FRAME_Y = 900;
    public static final int SCREEN_X = FRAME_X;
    public static final int SCREEN_Y = FRAME_Y - 23;
    public static final int MAP_BOTTOM_Y = 5000;
    //邏輯更新時間
    public static final int UPDATE_PER_SEC = 60;
    public static final int NANO_PER_UPDATE = 1000000000 / UPDATE_PER_SEC;
    //動畫更新時間   
    public static final int FRAME_PER_SEC = 60;
    public static final int LIMIT_DELTA_TIME = 1000000000 / FRAME_PER_SEC;

    public static final int BINARYPLAY = 9;
    public static final int INTRODUCTION = 10;
    public static final int SINGLEPLAY = 11;

    public static final int A_LEFT = 1;
    public static final int A_RIGHT = 2;
    public static final int A_JUMP = 3;
    public static final int A_BUBBLES = 4;
    public static final int A_YELLOW_BUBBLE = 5;
    public static final int B_LEFT = 6;
    public static final int B_RIGHT = 7;
    public static final int B_JUMP = 8;
    public static final int B_BUBBLES = 9;
    public static final int B_YELLOW_BUBBLE = 10;

    public static final int[][] COMMANDS = new int[][]{
        //actor 1
        {KeyEvent.VK_A, Global.A_LEFT},//1
        {KeyEvent.VK_D, Global.A_RIGHT},//2
        {KeyEvent.VK_SPACE, Global.A_JUMP},//3
        {KeyEvent.VK_W, Global.A_JUMP},//3
        {KeyEvent.VK_G, Global.A_BUBBLES},//4
        {KeyEvent.VK_H, Global.A_YELLOW_BUBBLE},//5
        {KeyEvent.VK_1, Global.BINARYPLAY},
        {KeyEvent.VK_2, Global.INTRODUCTION},
        {KeyEvent.VK_3, Global.SINGLEPLAY},
        {KeyEvent.VK_DELETE, 12},
        {KeyEvent.VK_BACK_SPACE, 12},
        //actor 2
        {KeyEvent.VK_J, B_LEFT},//6
        {KeyEvent.VK_L, B_RIGHT},//7
        {KeyEvent.VK_ALT, B_JUMP},//8
        {KeyEvent.VK_I, B_JUMP},//8
        {KeyEvent.VK_NUMPAD4, B_JUMP},//8
        {KeyEvent.VK_CONTROL, B_BUBBLES},//9
        {KeyEvent.VK_NUMPAD5, B_BUBBLES},//9
        {KeyEvent.VK_LEFT, Global.B_YELLOW_BUBBLE},//10
        {KeyEvent.VK_NUMPAD6, Global.B_YELLOW_BUBBLE},//10

        {KeyEvent.VK_DOWN, 41},
        {KeyEvent.VK_ENTER, 42},
        {KeyEvent.VK_UP, 20},
        {KeyEvent.VK_RIGHT, 43},
        //A 1
        //D 2
        //G 4

        {KeyEvent.VK_B, 13},
        {KeyEvent.VK_C, 14},
        {KeyEvent.VK_E, 15},
        {KeyEvent.VK_F, 16},
        {KeyEvent.VK_K, 19},
        {KeyEvent.VK_M, 22},
        {KeyEvent.VK_N, 23},
        {KeyEvent.VK_O, 24},
        {KeyEvent.VK_P, 25},
        {KeyEvent.VK_Q, 26},
        {KeyEvent.VK_R, 27},
        {KeyEvent.VK_S, 28},
        {KeyEvent.VK_T, 29},
        {KeyEvent.VK_U, 30},
        {KeyEvent.VK_V, 31},
        {KeyEvent.VK_X, 33},
        {KeyEvent.VK_Y, 34},
        {KeyEvent.VK_Z, 35}
    };

    public static float random(float min, float max) {
        return (float) (Math.random() * (max - min + 1) + min);
    }
}
