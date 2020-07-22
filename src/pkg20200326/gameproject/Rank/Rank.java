/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg20200326.gameproject.Rank;


/**
 *
 * @author chant
 */
public class Rank {

    private int sec;
    private String nameA;
    private int heightDataA;

    public Rank(int playTime, String nameA, int heightDataA) {
        this.sec = playTime;
        this.nameA = nameA;
        if (heightDataA >= 5000) {
            this.heightDataA = 5000;
        } else {
            this.heightDataA = heightDataA;
        }
    }

    public int getSec() {
        return this.sec;
    }

    public String getNameA() {
        return this.nameA;
    }

    public int getHeightDataA() {
        return this.heightDataA;
    }

    @Override
    public String toString() {
        return this.sec + "," + this.nameA + "," + this.heightDataA;
    }
}
