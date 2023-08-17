import java.util.Objects;

public class gameOfLife {
    public static String reportCollection = "";
    public static void main(String[] args) throws InterruptedException {
        Utilities utils = new Utilities();
        Game_Manager manager = new Game_Manager();
        String[] values = utils.argsTreater(args);
        for(int verify = 0; verify < values.length; verify++){
            if(Objects.equals(values[verify], "Inválido") || Objects.equals(values[verify], "Não presente")){
                values = utils.defaultIfNecessary(values);
            }
        }
        Cell[][] initialGen = manager.initialGenGenerator(values[0], values[1], values[4]);
        reportCollection += utils.utilitiesReport;
        reportCollection += manager.gameManagerReport;
        Cell[][] finalCell = playing(Integer.parseInt(values[2]), Integer.parseInt(values[3]), initialGen);
        generateFinalReport(finalCell, reportCollection);
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
            if((thisGeneration+1) == finalGen)break;
            initialGen = nextGen;
            Thread.sleep(speedTime);
        }
        reportCollection += "\n|| Gens: "+finalGen;
        reportCollection += manager.gameManagerReport;
        return initialGen;
    }

    /**
     * Generate de final Print of Report to explain the experience
     * @param finalGen last grid to be printed
     * @param report data to print in report
     */
    public static void generateFinalReport(Cell[][] finalGen, String report){
        Utilities util = new Utilities();
        String finalGRID = util.gridGenerator(finalGen);
        String initGRID = util.copyLineGen(finalGen);
        System.out.println("\n\n\n\n" +
                "||=======================================||"+
                "\n||/////////////// REPORT ////////////////||"+
                report+"||=======================================||"+
                "\n\n|| Final GRID: \n"+finalGRID+
                "||=======================================||"+
                "\nFinal GRID Copyline: "+initGRID);
    }
}
