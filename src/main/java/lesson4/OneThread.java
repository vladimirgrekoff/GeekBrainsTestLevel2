//Домашнее задание,уровень 2, урок 4: Владимир Греков
package lesson4;

import java.util.Arrays;

public class OneThread {

    public static void runOneThread() {
        int size = 10_000_000;
        float[] arrBig = new float[size];
        Arrays.fill(arrBig, 1.0f);
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < arrBig.length; i++) {
            arrBig[i] = (float) (arrBig[i] * Math.sin(0.2f + (float) i / 5) * Math.cos(0.2f + (float) i / 5) * Math.cos(0.4f + (float) i / 2));
        }
//        System.out.println("Индекс в левой части большого " + arrBig[4999999]);//для проверки
//        System.out.println("Индекс в павой части большого " + arrBig[5000001]);
        System.out.println("Время для одного потока: " + (System.currentTimeMillis() - startTime) + " ms.");
    }
}