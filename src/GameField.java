import java.util.Random;

/**
 * Created by Dmitriy Mubarakshin on 08.10.14.
 */
public class GameField {

    protected int[][] field;
    private static Random rnd;
    private static final int FIELD_SIZE = 4, POS_COUNT = 16;
    public GameField() {
        field = shuffle();
    }

    public static int[][] shuffle() {
        rnd = new Random();
        int [][] res = new int[FIELD_SIZE][FIELD_SIZE];
        int [] sequence = new int[POS_COUNT];  //вспомогательный массив для инициализации случайного поля
        for (int i = 0; i < POS_COUNT; i++)
            sequence[i] = i;
        for (int i = POS_COUNT-1; i >0; i--) {
            int j = rnd.nextInt(i);
            int temp = sequence[j];
            sequence[j] = sequence[i];
            sequence[i] = temp;         //перемешиваем массив простой перестановкой
        }
        for (int i = 0; i < FIELD_SIZE; i++)
            for (int j = 0; j < FIELD_SIZE; j++)
                res[i][j] = sequence[FIELD_SIZE*i + j];
        return res;
    }

    public void clickProcessing (int row, int col) {  //обработка клика
        //int dir = -1; // 0 - вверх, 1 - вниз, 2 - влево, 3 - вправо
        EDirection dir = EDirection.NONE;
        if (possibleToMakeMove(row, col, dir))
            makeMove(row, col, dir);
        if (isGameFinished())
            System.out.print("Поздравляем, вы победили!");
    }

    private boolean possibleToMakeMove(int row, int col, EDirection dir) {
        if (field[row-1][col] == 0) {
            dir = EDirection.UP;
            return true;
        }

        if (field[row+1][col] == 0) {
            dir = EDirection.DOWN;
            return true;
        }

        if (field[row][col-1] == 0) {
            dir = EDirection.LEFT;
            return true;
        }

        if (field[row][col+1] == 0) {
            dir = EDirection.RIGHT;
            return true;
        }

        return false;
    }

    private void makeMove(int row, int col, EDirection dir) {
        int tmp;
        switch (dir) {
            case UP:
                tmp = field[row][col];
                field[row][col] = field[row-1][col];
                field[row-1][col] = tmp;
                break;
            case DOWN:
                tmp = field[row][col];
                field[row][col] = field[row+1][col];
                field[row+1][col] = tmp;
                break;
            case LEFT:
                tmp = field[row][col];
                field[row][col] = field[row][col-1];
                field[row][col-1] = tmp;
                break;
            case RIGHT:
                tmp = field[row][col];
                field[row][col] = field[row][col+1];
                field[row][col+1] = tmp;
                break;
        }
    }

    public boolean isGameFinished() {
        for (int i = 0; i < FIELD_SIZE; i++)
            for (int j = 0; j < FIELD_SIZE; j++)
                if ((field[i][j] != FIELD_SIZE*i + j + 1)
                        && (i < FIELD_SIZE - 1) && (j < FIELD_SIZE - 1)) // не правая нижняя клетка
                    return false;
        return true;
    }


}
