package Lesson5;

import java.util.Arrays;

public class TestTread {
    private static final int SIZE = 10_000_000;
    private static final int HALF = SIZE / 2;


    public static void main(String[] args) {
        float[] arr1 = createArr();
        Runnable longMethod = () -> formulaResult(arr1, 0);
        float[] arr2 = createArr();
        Runnable shotMethod = () -> parallelMethod(arr2);
        measureTime(longMethod, "longMethod");
        measureTime(shotMethod, "parallelMethod");
        System.out.println("Преобразованные массивы равны? Ответ: " + Arrays.equals(arr1, arr2));


    }

    private static void measureTime(Runnable r, String message) {
        Thread tread = new Thread(r);
        long startTime = System.currentTimeMillis();
        tread.start();
        try {
            tread.join();
        } catch (InterruptedException e) {
            System.out.println("Ошибка");
            e.printStackTrace();
        }
        System.out.printf("Метод %s занял времени в миллисекундах %d \n", message, System.currentTimeMillis() - startTime);

    }

    private static void parallelMethod(float[] arr) {
        float[] a1 = new float[HALF];
        float[] a2 = new float[HALF];
        System.arraycopy(arr, 0, a1, 0, HALF);
        System.arraycopy(arr, HALF, a2, 0, HALF);
        Thread thread1 = new Thread(() -> formulaResult(a1, 0));
        Thread thread2 = new Thread(() -> formulaResult(a2, HALF));
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            System.out.println("Ошибка внури корткой обработки");
            e.printStackTrace();
        }
        System.arraycopy(a1, 0, arr, 0, HALF);
        System.arraycopy(a2, 0, arr, HALF, HALF);


    }


    private static float[] createArr() {
        float[] arr = new float[SIZE];
        Arrays.fill(arr, 1.0f);
        return arr;
    }

    private static float[] formulaResult(float[] arr, int offset) {
        for (int i = 0; i < arr.length; i++)
            arr[i] = (float) (arr[i] * Math.sin(0.2f + (i + offset) / 5) * Math.cos(0.2f + (i + offset) / 5) * Math.cos(0.4f + (i + offset) / 2));
        return arr;
    }

}



