package MYarulin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
/**
 * Класс Main [01_iceberg_array]
 * @autor Mikhail Yarulin
 * JDK 16
 * @version 1.0
 */

public class Main {
    //создаем логгер.
    private static final Logger LOG = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        //создаем многомерный массив с начальными данными
        int[][] array = new int[][]{
                {1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 1},
                {1, 0, 1, 1, 5, 0},
                {1, 1, 0, 5, 5, 1}};
        Utils utils = new Utils();
        //получаем обработанную мапу и прогоняем через for each
        Map<Integer, Integer> mapOfIces = utils.calculateAmountOfIces(array);
        for (Map.Entry<Integer, Integer> entry : mapOfIces.entrySet()) {
            LOG.info(entry.getKey() + " ячеек - " + entry.getValue());
        }
    }
}