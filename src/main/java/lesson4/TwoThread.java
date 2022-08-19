//Домашнее задание,уровень 2, урок 4: Владимир Греков
package lesson4;

import java.util.Arrays;


public class TwoThread {
    static final int size = 10000000;
    static final int half = size / 2;
    static float[] arrLeft;
    static float[] arrRight;
    static float[] arrBig = new float[size];
    private static int leftCount;
    private static int rightCount;
    private static long startTime;



    public static void runTwoThread() {
        Arrays.fill(arrBig, 1.0f);
        //начало отсчета
        startTime = System.currentTimeMillis();
        arrLeft = new float[half];
        arrRight = new float[half];
        // Копируем значения из большого массива в два малых
        System.arraycopy(arrBig, 0, arrLeft, 0, half);
        System.arraycopy(arrBig, half, arrRight, 0, half);
    }

    public static void joinArrayAndPrintTime() {
        //соединение массивов
        while (leftCount != half - 1 && rightCount != half - 1) { //ждем завершения подсчета в массивах
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if ((leftCount == half - 1) && (rightCount == half - 1)) {
                break;
            }
        }
        System.arraycopy(arrLeft, 0, arrBig, 0, half);
        System.arraycopy(arrRight, 0, arrBig, half, half);
//        System.out.println("Индекс в левой части большого " + arrBig[4999999]); //для проверки
//        System.out.println("Индекс в павой части большого " + arrBig[5000001]);
        System.out.println("Время для двух потоков: " + (System.currentTimeMillis() - startTime) + " ms.");
    }



    public  void calculateForArrLeft() {
        //вычисление значений для левой части
        for (int i = 0; i < arrLeft.length; i++) {
            arrLeft[i] = (float) (arrLeft[i] * Math.sin(0.2f + (float) i / 5) * Math.cos(0.2f + (float) i / 5) * Math.cos(0.4f + (float) i / 2));
            leftCount = i;
        }
//        System.out.println(arrLeft[half - 1]); //контрольное значение
//        System.out.println("Поток " + Thread.currentThread().getName() + " отработал");
    }

    public  void calculateForArrRight() {
        //вычисление значений для правой части
        for (int i = 0; i < arrRight.length; i++) {
            int j = i + half;
            arrRight[i] = (float) (arrRight[i] * Math.sin(0.2f + (float) j / 5) * Math.cos(0.2f + (float) j / 5) * Math.cos(0.4f + (float) j / 2));
            rightCount = i;
        }
//        System.out.println(arrRight[1]); //контрольное значение
//        System.out.println("Поток " + Thread.currentThread().getName() + " отработал");
    }
}
