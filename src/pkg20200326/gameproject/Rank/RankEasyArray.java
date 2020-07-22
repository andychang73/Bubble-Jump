/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg20200326.gameproject.Rank;

import java.util.ArrayList;

/**
 *
 * @author chant
 */
public class RankEasyArray {

    private Rank[] ranks;
    private int count;

    public RankEasyArray() {
        this.ranks = new Rank[5];
        this.count = 0;
    }

    public boolean add(Rank rank) {
        if (this.count == this.ranks.length) {
            if (rank.getHeightDataA() < ranks[count - 1].getHeightDataA()) {//高度較低
                return false;
            } else if (rank.getHeightDataA() == ranks[count - 1].getHeightDataA() && rank.getSec() < ranks[count - 1].getSec()) {
//高度相同且剩餘秒數
                return false;
            } else {
                this.ranks[count - 1] = rank;
                sortByHeight();
                sortBySec();
            }
        } else {
            this.ranks[count++] = rank;//較小
            sortByHeight();
            sortBySec();
        }
        return true;
    }

    public Rank get(int index) {
        if (index < 0 || index >= this.count) {
            return null;
        }
        return this.ranks[index];
    }

    public void sortByHeight() {
        for (int i = 1; i < this.count; i++) {
            for (int k = 0; k < this.count - i; k++) {
                if (this.ranks[k].getHeightDataA() < this.ranks[k + 1].getHeightDataA()) {
                    swap(k, k + 1);
                }
            }
        }
    }

    public void sortBySec() {
        for (int i = 1; i < this.count; i++) {
            for (int k = 0; k < this.count - i; k++) {
                if (this.ranks[k].getHeightDataA() == this.ranks[k + 1].getHeightDataA()) {
                    if (this.ranks[k].getSec() < this.ranks[k + 1].getSec()) {
                        swap(k, k + 1);
                    }
                }
            }
        }
    }

    public Rank swap(int i1, int i2) {
        Rank tmp = this.ranks[i1];
        this.ranks[i1] = this.ranks[i2];
        this.ranks[i2] = tmp;
        return tmp;
    }

    public int length() {
        return this.count;
    }
}
