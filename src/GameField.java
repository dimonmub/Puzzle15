import java.util.Random;

/**
 * Created by Dmitriy Mubarakshin on 08.10.14.
 */
public class GameField {

    public int[][] field;
    public GameField() {
        field = shuffle();
    }

    public static int[][] shuffle() {
        Random rnd = new Random();
        int [][] res = new int[4][4];
        int [] sequence = new int[16];  //вспомогательный массив для инициализации случайного поля
        for (int i = 0; i < 16; i++)
            sequence[i] = i;
        for (int i = 15; i >0; i--) {
            int j = rnd.nextInt(i);
            int temp = sequence[j];
            sequence[j] = sequence[i];
            sequence[i] = temp;         //перемешиваем массив простой перестановкой
        }
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
                res[i][j] = sequence[4*i + j];
        return res;
    }

    public void clickProcessing (int row, int col) {  //обработка клика
        int dir = -1; // 0 - вверх, 1 - вниз, 2 - влево, 3 - вправо
        if (possibleToMakeMove(row, col, dir))
            makeMove(row, col, dir);
        if (isGameFinished())
            System.out.print("Поздравляем, вы победили!");
    }

    private boolean possibleToMakeMove(int row, int col, int dir) {
        if (field[row-1][col] == 0) {
            dir = 0;
            return true;
        }

        if (field[row+1][col] == 0) {
            dir = 1;
            return true;
        }

        if (field[row][col-1] == 0) {
            dir = 2;
            return true;
        }

        if (field[row][col+1] == 0) {
            dir = 3;
            return true;
        }

        return false;
    }

    private void makeMove(int row, int col, int dir) {
        int tmp;
        switch (dir) {
            case 0:
                tmp = field[row][col];
                field[row][col] = field[row-1][col];
                field[row-1][col] = tmp;
                break;
            case 1:
                tmp = field[row][col];
                field[row][col] = field[row+1][col];
                field[row+1][col] = tmp;
                break;
            case 2:
                tmp = field[row][col];
                field[row][col] = field[row][col-1];
                field[row][col-1] = tmp;
                break;
            case 3:
                tmp = field[row][col];
                field[row][col] = field[row][col+1];
                field[row][col+1] = tmp;
                break;
        }
    }

    public boolean isGameFinished() {
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
                if (field[i][j] != 4*i + j + 1)
                    return false;
        return true;
    }


}
