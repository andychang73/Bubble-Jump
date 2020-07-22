/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg20200326.gameproject.util;

/**
 *
 * @author salesengineer4891
 */
public class Camera {

    private float cameraTopY;//上
    private float cameraBottomY;

    public Camera() {
        this.cameraTopY = Global.FRAME_Y / 2 + Global.MAP_BOTTOM_Y - Global.SCREEN_Y;
        this.cameraBottomY = cameraTopY + Global.FRAME_Y;
    }

    public float getCameraTopY() {
        return this.cameraTopY;
    }

    public float getCameraBottomY() {
        return this.cameraBottomY;
    }

    public void setCameraTopY(float targetY) {//中心點
        targetY -= Global.FRAME_Y / 2;//鏡頭在人物的上方
        this.cameraTopY += (targetY - this.cameraTopY - 220) / 10;

    }

    public void setCameraBottomY() {
        this.cameraBottomY  = this.cameraTopY + Global.FRAME_Y;
    }

    public void update(float targetY) {
        setCameraTopY(targetY);
        setCameraBottomY();
    }
}
