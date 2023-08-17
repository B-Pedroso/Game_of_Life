public class Cell {
    String alive_icon = "\uD83D\uDE03";
    /* Legendas dos unicodes:
    \uD83D\uDE03 Sorriso 😃
    \uD83D\uDE80 Foguete 🚀
    \uD83D\uDE07 Anjinho 😇
    */
    String dead_icon = "\uD83D\uDC80";
    /* Legendas dos unicodes:
    \uD83D\uDC80 Caveira 💀
    \uD83D\uDC7E E.T.     👾
    \uD83D\uDE08 Demoninho 😈
    */

    Cell(boolean isAlive){
        this.isAlive = isAlive;
    }
    private boolean isAlive;

    public boolean ifisAlive() {
        return isAlive;
    }
    private int aliveNeighbors = 0;
    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public int getAliveNeighbors() {
        return aliveNeighbors;
    }

    public void setAliveNeighbors(int alive) {
        this.aliveNeighbors = alive;
    }

    /**
     * Method to show if cell is alive or dead
     */
    public void cellShow(){
        if (isAlive){
            System.out.print(alive_icon);
        }else{
            System.out.print(dead_icon);
        }
    }

    public String cellGridReturn(){
        if (isAlive){
            return alive_icon;
        }else{
            return dead_icon;
        }
    }

    /**
     * This method will create a new cell verifying the four rules of the game
     * RULES ##

     1. Any live cell with fewer than two live neighbours dies, as if caused by underpopulation.

     2.Any live cell with two or three live neighbours lives on to the next generation.

     3.Any live cell with more than three live neighbours dies, as if by overpopulation.

     4.Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.

     * @param cell the old cell
     * @return the new cell
     */
    public Cell verifyingRules(Cell cell){
        Cell returnCell = new Cell(true);
        if (cell.ifisAlive()){
            if(cell.getAliveNeighbors() < 2 || cell.getAliveNeighbors() > 3){
                returnCell.setAlive(false);
            }
        }else{
            if(cell.getAliveNeighbors() != 3){
                returnCell.setAlive(false);
            }
        }
        return returnCell;
    }
}
