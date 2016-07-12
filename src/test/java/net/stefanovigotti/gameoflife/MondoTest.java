package net.stefanovigotti.gameoflife;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by io on 12/07/16.
 */
public class MondoTest {



    @Test public void mondo_can_be_initialized_with_x_y_size(){
        mondoMorto_5x5();
    }

    @Test public void new_mondo_is_not_null(){
        Mondo mondo = mondoMorto_5x5();
        int x = 3;
        int y = 3;
        Cellula c = mondo.cellAt(x,y);
        assertNotNull(c);
    }

    @Test public void new_mondo_contain_all_dead_cells(){
        Mondo mondo = mondoMorto_5x5();
        int x = 3;
        int y = 3;
        Cellula c = mondo.cellAt(x,y);
        assertFalse(c.isAlive());

    }

    @Test public void new_mondo_contain_all_dead_cells_allCellisdead(){
        Mondo mondo = mondoMorto_5x5();
        assertTrue(mondo.allCellsAreDead());
    }

    @Test public void allCells_are_not_dead(){
        Mondo mondo = mondoMorto_5x5();
        mondo.cellAt(3,3).resurrect();
        assertFalse(mondo.allCellsAreDead());
    }


    @Test
    public void deadMondo_remainDead_after_1_step(){
        Mondo mondoMorto = mondoMorto_5x5();
        assertTrue(mondoMorto.allCellsAreDead());
        mondoMorto.avanti();
        assertTrue(mondoMorto.allCellsAreDead());
    }


    @Test
    public void mondo_with_1_cell_die_for_lonliness(){
        Mondo mondo = mondoMorto_5x5();
        mondo.cellAt(3,3).resurrect();
        assertFalse(mondo.allCellsAreDead()); // ensure is not dead world

        mondo.avanti();

        assertTrue(mondo.allCellsAreDead());
    }


    @Test
    public void test_CellAreNear_1(){
        Mondo mondo = mondoMorto_5x5();
        mondo.cellAt(1,1).resurrect();

        assertEquals(1,mondo.getCountAliveVicine(1,2));
        assertEquals(0,mondo.getCountAliveVicine(3,3));

    }

    @Test
    public void test_CellAreNear_2(){
        Mondo mondo = mondoMorto_5x5();
        mondo.cellAt(1,1).resurrect();
        mondo.cellAt(1,3).resurrect();

        assertEquals(2,mondo.getCountAliveVicine(1,2));

    }

    @Test
    @Ignore
    public void mondo_with_two_cell_near_after_1_step_is_not_dead(){
        Mondo mondo = mondoMorto_5x5();
        mondo.cellAt(0,0).resurrect();
        mondo.cellAt(0,1).resurrect();
        assertFalse(mondo.allCellsAreDead()); // ensure is not dead world

        mondo.avanti();

        assertFalse(mondo.allCellsAreDead());
    }



//
//    @Test public void mondo_stores_cells_at_given_cordinates(){
//        Mondo mondo = mondoMorto_5x5();
//        Cellula c = new Cellula();
//        c.resurrect();
//        int x = 3;
//        int y = 3;
//        mondo.storeCell(c,x,y);
//    }


    private Mondo mondoMorto_5x5() {
        int x = 5;
        int y = 5;
        Mondo mondo = new Mondo(x,y);
        return mondo;
    }

}