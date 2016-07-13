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
        two_distant_cells_resurrect(mondo);

        assertEquals(2,mondo.getCountAliveVicine(1,2));

    }

    @Test
    public void RULE1_mondo_with_two_cell_near_after_1_step_is_not_dead(){
        Mondo mondo = mondoMorto_5x5();
        two_near_cell_resurrect(mondo);
        assertFalse(mondo.allCellsAreDead()); // ensure is not dead world

        mondo.avanti();

        mondo.cellAt(1,1).resurrect();
        two_near_cell_resurrect(mondo);
        if(mondo.getCountAliveVicine(1,1)==2)
            mondo.cellAt(1,1).isAlive();
        assertFalse(mondo.allCellsAreDead());
    }


    @Test
    public void invalidCellsOnVicineCounter(){
        Mondo mondo = mondoMorto_5x5();
        assertEquals(0,mondo.getCountAliveVicine(0,0));
    }

    @Test
    public void invalidCellsOnVicineCounter_2(){
        Mondo mondo = mondoMorto_5x5();
        assertEquals(0,mondo.getCountAliveVicine(4,4));
    }

    @Test
    public void RULE2_mondo_with_three_cell_near_after_1_step_is_not_dead(){
        Mondo mondo = mondoMorto_5x5();

        int x = 1;
        int y = 1;
        three_near_cell_resurrect(mondo, x, y);
        assertFalse(mondo.allCellsAreDead()); // ensure is not dead world

        mondo.avanti();

        assertTrue(mondo.cellAt(x,y).isAlive());

    }

    @Test
    public void RULE3_cell_with_more_that_trhee_cell_near_dead_for_overpopulation(){
        Mondo mondo = mondoMorto_5x5();
        int x = 1;
        int y = 1;
        resurrect_four_near_cells_(mondo,x,y);
        assertFalse(mondo.allCellsAreDead()); // ensure is not dead world

        mondo.avanti();

        assertFalse(mondo.cellAt(x,y).isAlive());
    }

    @Test
    public void RULE4_dead_cell_with_exactly_three_live_cell_near_resurrect(){
        Mondo mondo = mondoMorto_5x5();
        two_near_cell_resurrect(mondo);
        assertFalse(mondo.allCellsAreDead()); // ensure is not dead world

        mondo.avanti();

        two_near_cell_resurrect(mondo);
        mondo.cellAt(1,0).resurrect();
        if(mondo.getCountAliveVicine(1,1)==3)
            mondo.cellAt(1,1).resurrect();
        assertTrue(mondo.cellAt(1,1).isAlive());

    }

    private void resurrect_four_near_cells_(Mondo mondo,int x , int y) {
        mondo.cellAt(x,y).resurrect();

        mondo.cellAt(x-1,y-1).resurrect();
        mondo.cellAt(x-1,y).resurrect();

        mondo.cellAt(x,y-1).resurrect();
        mondo.cellAt(x-1,y+1).resurrect();
    }


    private void two_distant_cells_resurrect(Mondo mondo) {
        mondo.cellAt(1,1).resurrect();
        mondo.cellAt(1,3).resurrect();
    }


    private void two_near_cell_resurrect(Mondo mondo) {
        mondo.cellAt(0,0).resurrect();
        mondo.cellAt(0,1).resurrect();
    }


    private void three_near_cell_resurrect(Mondo mondo,int x,int y) {
        mondo.cellAt(x-1,y-1).resurrect();
        mondo.cellAt(x-1,y).resurrect();
        mondo.cellAt(x,y-1).resurrect();
    }


    private Mondo mondoMorto_5x5() {
        int x = 5;
        int y = 5;
        Mondo mondo = new Mondo(x,y);
        return mondo;
    }

}