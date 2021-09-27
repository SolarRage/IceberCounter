package MYarulin;

/**
 * Класс Utils [01_iceberg_array]
 * @autor Mikhail Yarulin
 * JDK 16
 * @version 1.2
 */
import java.util.*;

public class Utils {
    /**
     * Метод создает мапу и запускает поиск в шириу(breadth-first search)
     * @return возвращает перебраную мапу в calculateAmountOfIces.
     */
    public Map<Integer, Integer> calculateAmountOfIces(int[][] array) {
        int rowsCount = array.length;
        int columnsCount = array[0].length;

        //ключ - размерность, значение - количество
        Map<Integer, Integer> mapOfIces = new HashMap<>();

        for (int row = 0; row < rowsCount; row++) {
            for (int col = 0; col < columnsCount; col++) {
                if (array[row][col] != 0) {
                    //запускаем поиск в ширину
                    bfs(mapOfIces, array, row, col);
                }
            }
        }
        return mapOfIces;
    }
    /**
     * Метод находит первую вершину в массиве и записывает ее в очередь queue.
     * Помечает проверенную область, находит следующую вершину и записывает ее в очеред, а предидущую удаляет.
     * В переменную counter записываем все точки, пока не изучим весь айсберг.
     */
    private void bfs(Map<Integer, Integer> mapOfIces, int[][] array, int i, int j) {
        //очередь необработанных вершин
        Queue<Point> queue = new ArrayDeque<>();
        //начальную добавляем в очередь
        queue.offer(new Point(i, j));
        //начальную помечаем как проверенную
        array[i][j] = 0;

        //количество ячеек в одном айсберге
        int counter = 0;
        //работаем пока айберг полностью не изучен
        while (queue.size() != 0) {
            //достаем один из необработанных вершин айсберга и удаляем
            Point current = queue.poll();
            //найдем соседей айсберга
            List<Point> neighbours = getNeighbours(array, current.x, current.y);
            for (Point neighbour : neighbours) {
                //добавляем всех необработанных соседей в очередь
                queue.offer(neighbour);
            }
            counter++;
        }
        if (mapOfIces.containsKey(counter)) {
            //если уже есть такая размерность - увеличить на один
            mapOfIces.put(counter, mapOfIces.get(counter) + 1);
        } else {
            //если нет, то это первый айсберг с такой размерностью
            mapOfIces.put(counter, 1);
        }
    }
    /**
     * Метод проверяет соседей вершины по вертикали и горизонтали.
     * @return возвращает выявленых соседей в очередь bfs.
     */
    private List<Point> getNeighbours(int[][] array, int i, int j) {
        List<Point> neighbours = new ArrayList<>();
        //право
        if (array.length != i + 1 && array[i + 1][j] != 0) {
            array[i + 1][j] = 0;
            neighbours.add(new Point(i + 1, j));
        }
        //низ
        if (array[i].length != j + 1 && array[i][j + 1] != 0) {
            array[i][j + 1] = 0;
            neighbours.add(new Point(i, j + 1));
        }
        //верх
        if (j - 1 >= 0 && array[i][j - 1] != 0) {
            array[i][j - 1] = 0;
            neighbours.add(new Point(i, j - 1));
        }
        //лево
        if (i - 1 >= 0 && array[i - 1][j] != 0) {
            array[i - 1][j] = 0;
            neighbours.add(new Point(i - 1, j));
        }
        return neighbours;
    }
}
