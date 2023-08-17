import java.util.Arrays;

public class Utilities {
    String utilitiesReport = "";

    /**
     * It is to receive the values from CLI and than it do the necessary changes
     * like replace the 'w=' for a empty space to be verified by conversion if
     * the value is valid
     */
    public String[] argsTreater(String[] values) {
        String[] treatedValues = new String[5];
        boolean[] presentValues = {false, false, false, false, false};
        String[] filterValues = {"w=", "h=", "g=", "s=", "p="};

        for(int valuetoVerify = 0; valuetoVerify < values.length; valuetoVerify++){
            for(int modeltoCompare = 0; modeltoCompare < filterValues.length; modeltoCompare++){
                if(values[valuetoVerify].charAt(0) == filterValues[modeltoCompare].charAt(0)){
                    presentValues[modeltoCompare] = true;
                    break;
                }
            }
        }

        for(int isPresent = 0; isPresent < presentValues.length; isPresent++){
            if(!presentValues[isPresent]){
                treatedValues[isPresent] = "Não presente";
                utilitiesReport += "\n|| "+filterValues[isPresent]+" Not inserted (will be used Default)";
            }
        }
        boolean pInvalido = false;
        for (int verifier = 0; verifier < filterValues.length; verifier++) {
            for (int chosedValue = 0; chosedValue < values.length; chosedValue++) {
                if (values[chosedValue].charAt(0) == 'p') {
                    for (int letters = 2; letters < values[chosedValue].length(); letters++) {
                        if (values[chosedValue].charAt(letters) != '0' &&
                                values[chosedValue].charAt(letters) != '1' &&
                                values[chosedValue].charAt(letters) != '#') {
                            treatedValues[chosedValue] = "Inválido";
                            pInvalido = true;
                            utilitiesReport += "\n|| p = Invalid (will be used Random_GRID)";
                            break;
                        }
                    }
                    if(!pInvalido){
                        treatedValues[chosedValue] = values[chosedValue].replace(filterValues[4], "");
                    }
                }

                if (values[chosedValue].charAt(0) == filterValues[verifier].charAt(0)) {
                    if (values[chosedValue].charAt(0) != 'p') {
                        values[chosedValue] = values[chosedValue].replace(filterValues[verifier], "");
                        try {
                            int conversor = Integer.parseInt(values[chosedValue]);
                            if(conversor == 0){
                                treatedValues[verifier] = "Inválido";
                            }else{
                                treatedValues[verifier] = values[chosedValue];
                            }
                            break;
                        } catch (NumberFormatException e) {
                            treatedValues[verifier] = "Inválido";
                            utilitiesReport +="\n|| "+ filterValues[verifier] +" Invalid (will be used Default)";
                        }
                    }
                    values[chosedValue] = values[chosedValue].replace(filterValues[verifier], "");

                }
            }
        }
        System.out.println(Arrays.toString(treatedValues));
        return treatedValues;
    }

    /**
     * if values doesnt have any of pre-definition infos defined it will
     * generate a default value for those dont have value or have an invalid one
     * but the generation who will be random
     * @param values - Receive the values
     * @return return values with necessary changes
     */
    public String[] defaultIfNecessary(String[] values) {
        System.out.println(Arrays.toString(values));
        String[] defaultValues = {"10", "10", "100", "300", "x"};
        for (int i = 0; i < values.length; i++) {
            if (values[i] == "Inválido" || values[i] == "Não presente") {
                values[i] = defaultValues[i];
            }
            if (values[i] == "x") {
                values[i] = randomGridGenerator(values[0], values[1]);
            }
        }
        return values;
    }

    /**
     * if population is not defined it will generate a random one wwith Math.Random
     * to have less mistakes than possible and have more cells defined, all of then
     * will be generated with values of 0 or 1 and not #
     * @return return a string of generation generated randomly
     */
    public String randomGridGenerator(String x, String y) {
        String grid = "";
        System.out.println(x + y);
        for (int lines = 0; lines < Integer.parseInt(x); lines++) {
            for (int columns = 0; columns < Integer.parseInt(y); columns++) {
                int randomInt = (int) Math.floor(Math.random() * (1 - 0 + 1) + 0);
                String newchar = "";
                if (randomInt == 0) {
                    newchar = "0";
                } else {
                    newchar = "1";
                }
                grid += newchar;
            }
        }
        return grid;
    }

    /**
     * It is a generator of formated GRID to show in final Report
     */
    public String gridGenerator (Cell[][] gen){
        String GRID = "";
        for(int y = 0; y < gen.length; y++){
            GRID += "|| ";
            for (int x = 0; x < gen[0].length; x++){
                if(gen[y][x].ifisAlive()){
                    GRID += gen[y][x].cellGridReturn();
                }else {
                    GRID += gen[y][x].cellGridReturn();
                }
            }
            GRID += "\n";
        }
        return GRID;
    }

    /**
     * Copylines of GRIDs generator
     */
    public String copyLineGen(Cell[][] gen){
        String GRID = "";
        for(int y = 0; y < gen.length; y++){
            for (int x = 0; x < gen[0].length; x++){
                if(gen[y][x].ifisAlive()){
                    GRID += "1";
                }else {
                    GRID += "0";
                }
            }
        }
        return GRID;
    }
}
