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


    public static void main(String[] args){

        //INSTANCE VARIABLE(s) declaration(s).
        String [][] oceanMap = new String[10][10];
        int userXAttack, userYAttack;


        System.out.println("\n**** Welcome to Battle Ships game **** \n\nRight now, " +
                "the sea is empty. \n");
        printMap(oceanMap);

        System.out.println("Deploy your ships: \n");
        placePlayerShips(oceanMap);
        placeCompShips(oceanMap);

        System.out.println("\nHere is the current map with all ships placed. \n");
        printMap(oceanMap);

        System.out.println("Get ready! It's time for ... Battle Ships! \n");

        //Player guesses recorded as "0"s (zeros).
        System.out.println("YOUR TURN: ");
        attackCompShips(oceanMap);
        attackCompShips(oceanMap);


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


    public static String translateDataToMapVisible(String str){

        if(str == null){
            return " ";
        }else if(str.equals("1")){
            return "@";
        }else{
            return " ";
        }

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
                    + cor + " coordinate for your ship to attack: ");
            x = input.nextLine();
        }
        System.out.println("Valid Input. Thank you! \n");

        return Integer.parseInt(x);
    }


    public static boolean checkPlayerHasNotGuessed(int x, int y, String [][] map){

        if(map[x][y].equals("0")){
            return true;
        }else{
            return false;
        }
    }


    public static void attackCompShips(String[][] map){

        int x, y;
        do {
            x = validateInputRangeForAttacking("X");
            y = validateInputRangeForAttacking("Y");
            if(!checkPlayerHasNotGuessed(x, y, map)){
                System.out.println("You have guess this spot before. Choose again.");
            }
        }while(!checkPlayerHasNotGuessed(x, y, map));
        map[x][y] = "0";
    }
}
