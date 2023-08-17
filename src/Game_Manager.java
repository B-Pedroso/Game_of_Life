public class Game_Manager {
    String gameManagerReport = "";
    Utilities util = new Utilities();
    /**
     * It will create the first generation with a double for to create the Cells
     * and then will look the population and change the cells in position 1 for true
     * @param lines is the width 'w='
     * @param columns is the height 'h='
     * @param population is the 'p='
     * @return retorna a primeira tabela de celulas
     */
    public Cell[][] initialGenGenerator(String lines, String columns, String population) {
        int intlines = Integer.parseInt(lines);
        int intcolumns = Integer.parseInt(columns);
        Cell[][] initialGen = new Cell[intlines][intcolumns];
        int populationposition = 0;
        for (int line = 0; line < intlines; line++) {
            for (int column = 0; column < intcolumns; column++) {
                Cell cell = new Cell(false);
                initialGen[line][column] = cell;
            }
        }
        for (int line = 0; line < intlines; line++) {
            for (int column = 0; column < intcolumns; column++) {
                if(populationposition > population.length()-1){
                    break;
                }
                if(population.charAt(populationposition) == '1'){
                    initialGen[line][column].setAlive(true);
                } else if (population.charAt(populationposition) == '#') {
                    populationposition += 1;
                    break;
                }
                populationposition += 1;
            }
            if(populationposition > population.length()){
                break;
            }
        }
        String gen = util.gridGenerator(initialGen);
        String genCL = util.copyLineGen(initialGen);
        gameManagerReport += "\n|| Lines = "+lines+"\n|| Columns = "+columns;
        gameManagerReport += "\n|| Initial GRID = \n"+gen+"\n Initial Grid Copyline: "+genCL+"\n";
        return initialGen;
    }

    /**
     * This method count neighbors and call the method to verify the rules
     * then it return a new cell for the next generation
     * @return return a new cell for the next generation
     */
    public Cell nextGenCellGenerator(Cell[][] gen, int line, int column){
        //Neighboor counter
        int neighbors = 0;
        for(int y = -1; y <= 1; y++){
            for(int x = -1; x <= 1; x++){
                if(y != 0 || x != 0){
                    try{
                        boolean compare = gen[line+y][column+x].ifisAlive();
                        if(compare)neighbors += 1;
                    }
                    catch(IndexOutOfBoundsException ignored){}
                }
            }
        }
        gen[line][column].setAliveNeighbors(neighbors);
        return gen[line][column].verifyingRules(gen[line][column]); //Next Gen Cell
    }
}