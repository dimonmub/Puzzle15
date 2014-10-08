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

}
