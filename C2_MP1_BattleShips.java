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

public class C2_MP1_BattleShips {

    //CLASS VARIABLE(s) declaration(s).
    static Scanner input = new Scanner(System.in);



    public static void main(String[] args){

        String [][] oceanMap = new String[10][10];

        System.out.println("\n**** Welcome to Battle Ships game **** \n\nRight now, " +
                "the sea is empty. \n");
        printMap(oceanMap);

        System.out.println("Deploy your ships: \n");

        int x1 = checkChoice("X", 1);
        int y1 = checkChoice("Y", 1);
        oceanMap[x1][y1] = "1";

        int x2 = checkChoice("X", 2);
        int y2 = checkChoice("Y", 2);
        oceanMap[x2][y2] = "1";

        int x3 = checkChoice("X", 3);
        int y3 = checkChoice("Y", 3);
        oceanMap[x3][y3] = "1";

        int x4 = checkChoice("X", 4);
        int y4 = checkChoice("Y", 4);
        oceanMap[x4][y4] = "1";

        int x5 = checkChoice("X", 5);
        int y5 = checkChoice("Y", 5);
        oceanMap[x5][y5] = "1";

        System.out.println("Here is the current map: ");
        printMap(oceanMap);
    }

    public static void printMap(String[][] map){

        System.out.println("  0123456789  ");
        for(int row = 0; row < map.length; row++){
            System.out.print(row + "|");
            for(int col = 0; col < map[row].length; col++){
                if(map[row][col] == null){
                    System.out.print(" ");
                }else if(map[row][col].equals("1")){
                    System.out.print("@");
                } else{
                    System.out.print(map[row][col]);
                }
            }
            System.out.println("|" + row);
            }
        System.out.println("  0123456789  ");
        System.out.println();
    }


    public static int checkChoice(String cor, int ship){

        System.out.print("Enter " + cor + " coordinate for your ship #" + ship + ": ");
        String x = input.nextLine();
        while(!x.matches("[0123456789]")){
            System.out.print("Invalid Input(numbers 0 - 9 and empty locations only). Enter "
                    + cor + " coordinate for your ship #" + ship + ": ");
            x = input.nextLine();
        }
        System.out.println("Valid Input. Thank you! \n");

        return Integer.parseInt(x);
    }


    public static boolean isEmptySpot(int x, int y, String [][] map){

        if(map[x][y].equals("1")){
            return false;
        }else{
            return true;
        }
    }
}

