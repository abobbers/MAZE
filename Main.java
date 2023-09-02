import java.io.*;
import java.util.*;

public class Main {
    static char[][] maze;
    static int startX, startY;
    public static void movement(int y, int x){
        if((y > 0 && x < maze[y].length-1 && y < maze.length-1 && x > 0) &&
                (maze[y-1][x] == '-' || maze[y][x+1] == '-' || maze[y+1][x] == '-' || maze[y][x-1] == '-')){
            System.out.print("Here is the SOLUTION! \n");
        } else if(y > 0 && maze[y-1][x] == ' '){
            //North-FIXED
            maze[y][x] = '+';
            maze[y-1][x] = '+';
            movement(y-1, x);
        } else if(x < maze[y].length-1 && maze[y][x+1] == ' '){
            //East-FIXED
            maze[y][x] = '+';
            maze[y][x+1] = '+';
            movement(y, x+1);
        } else if(y < maze.length-1 && maze[y+1][x] == ' '){
            //South-FIXED
            maze[y][x] = '+';
            maze[y+1][x] = '+';
            movement(y+1, x);
        } else if(x > 0 && maze[y][x-1] == ' '){
            //West-FIXED
            maze[y][x] = '+';
            maze[y][x-1] = '+';
            movement(y, x-1);
        } else {
            maze[y][x] = '.';
            backtracking(y, x);
        }
    }
    public static void backtracking(int y, int x){
        if(y > 0 && maze[y-1][x] == '+'){
            //North-FIXED
            maze[y-1][x] = '.';
            movement(y-1, x);
        } else if(x < maze[y].length-1 && maze[y][x+1] == '+'){
            //East-FIXED
            maze[y][x+1] = '.';
            movement(y, x+1);
        } else if(y < maze.length-1 && maze[y+1][x] == '+'){
            //South-FIXED
            maze[y+1][x] = '.';
            movement(y+1, x);
        } else if(x > 0 && maze[y][x-1] == '+'){
            //West-FIXED
            maze[y][x-1] = '.';
            movement(y, x-1);
        } else {
            System.out.println("There is no Solution :( \n");
        }
    }
    public static void main(String[] args) {

        int counter = 0;

        try {
            Scanner scanner = new Scanner(new File("maze.dat.txt"));

            String[] dimensions = scanner.nextLine().split(" ");
            maze = new char[Integer.parseInt(dimensions[0])][Integer.parseInt(dimensions[1])];

            while (scanner.hasNextLine()) {
                String vals = scanner.nextLine();
                for (int i = 0; i < vals.length(); i++) {
                    maze[counter][i] = vals.charAt(i);
                    if (vals.charAt(i) == '+') {
                        startY = counter;
                        startX = i;

                    }
                }
                counter++;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        movement(startY, startX);

        for (char[] chars : maze) {
            for (char aChar : chars) {
                System.out.print(aChar);
            }
            System.out.println();
        }
    }
}
