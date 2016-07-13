package net.stefanovigotti.gameoflife;

/**
 * Created by io on 12/07/16.
 */
public class Mondo {

    Cellula[][] griglia;

    public Mondo(int x, int y) {
        griglia = creaGriglia(x, y);

    }

    private Cellula[][] creaGriglia(int x, int y) {
        Cellula[][] gri=new Cellula[x][y];
        int k,z;
        for(k=0;k<gri.length;k++){
            for(z=0;z<gri[k].length;z++){
                Cellula c=new Cellula();
                gri[k][z]=c;
            }

        }
        return gri;
    }

    public void storeCell(Cellula c) {

    }

    public Cellula cellAt(int x, int y) {

        return griglia[x][y];
    }

    public boolean allCellsAreDead() {
        for(int i=0;i<griglia.length;i++ ){
            for(int j=0;j<griglia[i].length;j++){
                if(griglia[i][j].isAlive())
                    return false;
            }

        }
        return true;
    }

    public void avanti() {

        Cellula[][] nuovaGriglia = creaGriglia(griglia.length, griglia[0].length);

        for(int i=0;i<griglia.length;i++ ) {
            for (int j = 0; j < griglia[i].length; j++) {

                if(getCountAliveVicine(i,j)==3){
                   nuovaGriglia[i][j].resurrect();
                }

                RULE3_more_than_3_vicine_die(nuovaGriglia, i, j);
            }
        }
        griglia = nuovaGriglia;
    }

    private void RULE3_more_than_3_vicine_die(Cellula[][] nuovaGriglia, int i, int j) {
        if(getCountAliveVicine(i,j)>3){
            nuovaGriglia[i][j].die();
        }
    }


    public int getCountAliveVicine(int x, int y) {
        int countVicine=0;
        int xStart = x-1;
        int xEnd = x+1;
        for(int i=xStart;i<=xEnd;i++){
            if( i < 0 || i >= griglia.length)
                continue;

            for(int j=y-1;j<=y+1;j++){
                if( j < 0 || j >= griglia[i].length)
                    continue;

                if(i==x && j==y){
                    continue;
                }
                if(cellAt(i,j).isAlive()){
                    countVicine++;
                }

            }
        }
        return countVicine;
    }
}
