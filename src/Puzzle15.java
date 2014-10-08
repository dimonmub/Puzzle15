/**
 * Created by Dmitriy Mubarakshin on 08.10.14.
 */
public class Puzzle15 {
    public static void main(String[] args) {
        System.out.println("Welcome to Puzzle15!");
        GameField gameField = new GameField();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++)
                if (gameField.field[i][j] >= 10)
                    System.out.print(gameField.field[i][j] + " ");
                else
                    System.out.print(gameField.field[i][j] + "  ");
            System.out.println("");
        }
    }
}
