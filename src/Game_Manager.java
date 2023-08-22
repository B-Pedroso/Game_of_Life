/**
 * It cares about generations and call the necessary Classes and methods.
 * It also calls the method playing
 */
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
        boolean wasItaBreakpoint = false;
        for (int line = 0; line < intlines; line++) {
            wasItaBreakpoint = false;
            for (int column = 0; column < intcolumns; column++) {
                if(populationposition > population.length()-1){
                    break;
                }
                if(population.charAt(populationposition) == '1'){
                    initialGen[line][column].setAlive(true);
                    populationposition += 1;
                    wasItaBreakpoint = true;
                } else if (population.charAt(populationposition) == '#') {
                    if(column != 0 || wasItaBreakpoint) {
                        populationposition += 1;
                        break;
                    }else{
                        populationposition += 1;
                        wasItaBreakpoint = true;
                        column -= 1;
                    }
                }else{
                    populationposition += 1;
                    wasItaBreakpoint = true;
                }

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

    /**
     * Start the game of life
     * @param finalGen the number of generations will be printed
     * @param speedTime the time of pause beetwen the generations
     * @param initialGen The first grid to start the game
     * @return the final gen to be printed in the report
     * @throws InterruptedException //Pause between the generations
     */
    public static Cell[][] playing(int finalGen, int speedTime, Cell[][] initialGen) throws InterruptedException {
        Game_Manager manager = new Game_Manager();
        int lines = initialGen.length;
        int columns = initialGen[0].length;
        if(finalGen != 0){
            for(int thisGeneration = 0; thisGeneration < finalGen; thisGeneration++){
                System.out.println("\033[H\033[2J");
                Cell[][] nextGen = new Cell[lines][columns];
                for(int line = 0; line < lines; line++){
                    for(int column = 0; column < columns; column++){
                        initialGen[line][column].cellShow();
                        nextGen[line][column] = manager.nextGenCellGenerator(initialGen, line, column);
                    }
                    System.out.println();
                }
                Thread.sleep(speedTime);
                if((thisGeneration-1) != finalGen) initialGen = nextGen;
            }
        }else{
            while(true){
                System.out.println("\033[H\033[2J");
                Cell[][] nextGen = new Cell[lines][columns];
                for(int line = 0; line < lines; line++){
                    for(int column = 0; column < columns; column++){
                        initialGen[line][column].cellShow();
                        nextGen[line][column] = manager.nextGenCellGenerator(initialGen, line, column);
                    }
                    System.out.println();
                }
                System.out.println("Aperte ctrl+c no terminal, ou ctrl+F2 no terminal intellij");
                Thread.sleep(speedTime);
                initialGen = nextGen;
            }
        }
        return initialGen;
    }
}