package net.stefanovigotti.gameoflife;

/**
 * Created by io on 12/07/16.
 */
public class Cellula {
    private boolean alive;

    public boolean isAlive() {
        return alive;
    }

    public void resurrect() {
        alive=true;
    }

    public void die() {
        alive=false;
    }
}
