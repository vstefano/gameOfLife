package net.stefanovigotti.gameoflife;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by io on 12/07/16.
 */
public class CellulaTest {


    @Test
    public void new_cells_born_dead(){
        Cellula c = createDeadCell();
        assertFalse(c.isAlive());
    }


    @Test
    public void when_set_alive_cell_remain_alive(){
        Cellula c = createAliveCell();
        assertTrue(c.isAlive());
    }

    @Test
    public void when_i_say_die_cell_must_die(){
        Cellula c = createAliveCell();
        assertTrue(c.isAlive());
        c.die();
        assertFalse(c.isAlive());
    }

    private Cellula createDeadCell() {
        return new Cellula();
    }

    private Cellula createAliveCell() {
        Cellula c = createDeadCell();
        c.resurrect();
        return c;
    }
}