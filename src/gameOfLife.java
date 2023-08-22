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
        Cell[][] finalCell = manager.playing(Integer.parseInt(values[2]), Integer.parseInt(values[3]), initialGen);
        reportCollection += utils.utilitiesReport;
        reportCollection += manager.gameManagerReport;
        generateFinalReport(finalCell, reportCollection);
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
                "\n\n|| Next GRID after the Final one: \n"+finalGRID+
                "||=======================================||"+
                "\nNext GRID after final one Copyline: "+initGRID);
    }
}
