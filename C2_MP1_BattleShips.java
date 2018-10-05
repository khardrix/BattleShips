/***************************************************************************************************
 ***************************************************************************************************
 *****                                  *| Battle Ships |*                                     *****
 *****-----------------------------------------------------------------------------------------*****
 *****                 This project will help you get more familiar with arrays.               *****
 *****                      You will be recreating the game of battleships.                    *****
 *****                 A player will place 5 of their ships on a 10 by 10 grid.                *****
 *****               The computer player will deploy five ships on the same grid.              *****
 *****                 Once the game starts the player and computer take turns,                *****
 *****       trying to sink each other's ships by guessing the coordinates to "attack".        *****
 *****           The game ends when either the player or computer has no ships left.           *****
 ***************************************************************************************************
 **************************************************************************************************/

//IMPORTS of needed tools and plug-ins.
import java.util.Scanner;
import java.util.Random;


public class C2_MP1_BattleShips {

    //CLASS VARIABLE(s) declaration(s).
    static Scanner input = new Scanner(System.in);
    static Random dice = new Random();
    static String [][] playerMap = new String[10][10];
    static int playerX, playerY, compX, compY;
    static String [][] oceanMap = new String[10][10];
    static String [][] compMap = new String[10][10];
    static int playerShips, compShips;


    public static void main(String[] args){

        System.out.println("\n**** Welcome to Battle Ships game **** \n\nRight now, " +
                "the sea is empty. \n");
        printMap(oceanMap);

        System.out.println("Deploy your ships: \n");
        placePlayerShips(oceanMap);
        placeCompShips(oceanMap);

        System.out.println("\nHere is the current map with all ships placed. \n");
        printMap(oceanMap);

        System.out.println("Get ready! It's time for ... Battle Ships! \n");

        playGame();
    }


    public static void printMap(String[][] map){

        String s;
        System.out.println("  0123456789");
        for(int row = 0; row < map.length; row++){
            System.out.print(row + "|");
            for(int col = 0; col < map[row].length; col++){
                s = map[row][col];
                System.out.print(translateDataToMapVisible(s));
            }
            System.out.println("|" + row);
        }
        System.out.println("  0123456789 \n");
    }


    public static String translateDataToMapVisible(String str) {

        if (str == null)
            return " ";
        else if (str.equals("1"))
            return "@";
        else if (str.equals("9"))
            return "x";
        else if (str.equals("0"))
            return "-";
        else if (str.equals("8"))
            return "!";
        return " ";
    }


    public static int validateInputRangeForPlacing(String cor, int ship){

        System.out.print("Enter " + cor + " coordinate for your ship #" + ship + ": ");
        String x = input.nextLine();
        while(!x.matches("[0123456789]")){
            System.out.print("Invalid Input(numbers 0 - 9). Enter "
                    + cor + " coordinate for your ship #" + ship + ": ");
            x = input.nextLine();
        }
        System.out.println("Valid Input. Thank you! \n");

        return Integer.parseInt(x);
    }


    public static boolean isEmptySpot(int x, int y, String [][] map){

        if(map[x][y] == null){
            return true;
        }else{
            return false;
        }
    }


    public static void placePlayerShips(String[][] map){

        for(int i = 1; i <= 5; i++) {
            int x, y;
            do {
                x = validateInputRangeForPlacing("X", i);
                y = validateInputRangeForPlacing("Y", i);
                if(!isEmptySpot(x, y, map)){
                    System.out.println("Space is occupied. Choose again.");
                }
            }while(!isEmptySpot(x, y, map));
            map[x][y] = "1";
        }
    }


    public static void placeCompShips(String[][] map){

        System.out.println("Computer is deploying ships: ");
        for(int i = 1; i <= 5; i++) {
            int compCorChoiceX, compCorChoiceY;
            do {
                compCorChoiceX = dice.nextInt(10);
                compCorChoiceY = dice.nextInt(10);
                if(isEmptySpot(compCorChoiceX, compCorChoiceY, map)){
                    System.out.println(i + ". ship DEPLOYED.");
                }
            }while(!isEmptySpot(compCorChoiceX, compCorChoiceY, map));
            map[compCorChoiceX][compCorChoiceY] = "2";
        }
    }


    public static int validateInputRangeForAttacking(String cor){

        System.out.print("Enter " + cor + " coordinate to attack: ");
        String x = input.nextLine();
        while(!x.matches("[0123456789]")){
            System.out.print("Invalid Input(numbers 0 - 9). Enter "
                    + cor + " coordinate to attack: ");
            x = input.nextLine();
        }
        System.out.println("Valid Input. Thank you! \n");

        return Integer.parseInt(x);
    }


    public static void validateNewChoicePlayer(String[][] map){

        System.out.println("Your ships: " + playerShips +
                " | Computer ships: " + compShips);
        do {
            playerX = validateInputRangeForAttacking("X");
            playerY = validateInputRangeForAttacking("Y");
            if(!isEmptySpot(playerX, playerY, map)){
                System.out.println("Already guessed this spot. Choose again.");
            }
        }while(!isEmptySpot(playerX, playerY, map));
        map[playerX][playerY] = "0";
    }


    public static void validateNewChoiceComp(String[][] map){

        do {
            compX = dice.nextInt(10);
            compY = dice.nextInt(10);
        }while(!isEmptySpot(compX, compY, map));
        map[compX][compY] = "0";
    }


    public static void attackByPlayer(String[][] map){

        System.out.println("YOUR TURN: ");
        printMap(oceanMap);
        validateNewChoicePlayer(playerMap);
        if(oceanMap[playerX][playerY] == null){
            oceanMap[playerX][playerY] = "0";
            System.out.println("You missed!\n");
        }else if(oceanMap[playerX][playerY].equals("1")){
            oceanMap[playerX][playerY] = "9";
            playerShips--;
            System.out.println("Oh no, you sunk one of your own ships :(\n");
        }else if(oceanMap[playerX][playerY].equals("2")){
            oceanMap[playerX][playerY] = "8";
            compShips--;
            System.out.println("Boom! You sunk a computer ship!\n");
        }
        printMap(oceanMap);
    }


    public static void attackByComp(String[][] map){

        System.out.println("COMPUTER'S TURN: ");
        validateNewChoiceComp(compMap);
        if(oceanMap[compX][compY] == null){
            System.out.println("Computer missed!\n");
        }else if(oceanMap[compX][compY].equals("1")){
            oceanMap[compX][compY] = "9";
            playerShips--;
            System.out.println("Oh no, the computer sunk one of your ships :(\n");
        }else if(oceanMap[compX][compY].equals("2")){
            oceanMap[compX][compY] = "8";
            compShips--;
            System.out.println("Haha! The computer sunk one of its own ships!\n");
        }
    }


    public static boolean isGameOver(int player, int comp){

        if(player == 0){
            System.out.println("Boo! You lost Battle Ships!");
            return true;
        }else if(comp == 0){
            System.out.println("Hooray! You won Battle Ships!");
            return true;
        }else{
            return false;
        }
    }


    public static void playGame(){

        playerShips = 5;
        compShips = 5;
        while(!isGameOver(playerShips, compShips)){
            attackByPlayer(oceanMap);
            attackByComp(oceanMap);
        }
        System.out.println("Game over!");
    }
}
