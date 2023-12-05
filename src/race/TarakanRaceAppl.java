package race;

// Создать приложение-игру «TarakansRace»: Таракан - поток, выполняющий цикл итераций.
// На каждой итерации поток выводит свой собственный номер и
// переходит в режим сна на случайное количество миллисекунд [2-5]. Диапазон времени сна должен быть одинаковым для всех тараканов.
// Таракан, закончивший круг первым, считается победителем.

// Приложение должно принимать количество тараканов и расстояние (количество итераций) из консольного ввода/вывода.

// В конце игры должно быть распечатано следующее сообщение “Congratulations to tarakan #X (winner)” где Х - номер таракана-победителя

import race.tarakan.Tarakan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TarakanRaceAppl {
    static String winner = null;

    public static void main(String[] args) throws InterruptedException, IOException {
        int min = 2;
        int max = 5;
        Random random = new Random();
        int sleepTime = min + random.nextInt((max + 1) - min);
//        int runCycles = 10;
//        int tarakansQuantity = 5;
        System.out.println();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the number of tarakans, which participate in the Race:");
        int tarakansQuantity = Integer.parseInt(br.readLine());
        System.out.println("Enter the number of cycles to run:");
        int runCycles = Integer.parseInt(br.readLine());

        System.out.println("\nSo,");
        System.out.println("The number of tarakans: " + tarakansQuantity);
        System.out.println("The tarakans need to run cycles: " + runCycles);

        System.out.println("\nPress 'S' to start the Race!");
        String start = br.readLine();

        while (!"s".equalsIgnoreCase(start)) {
            System.out.println("\nPress 'S' to start the Race!");
            start = br.readLine();
        }

        System.out.println("\n--------- Tarakans Race START! ---------");

        List<Thread> threads = new ArrayList<>();

        for (int i = 1; i < tarakansQuantity + 1; i++) {
            threads.add(new Thread(new Tarakan("Vasya-" + i, runCycles, sleepTime)));        // input: (qtyTar, runCycles, sleepTime)
        }

        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("\n--------- Tarakans Race FINISH! ---------");
        System.out.println("\nCongratulations to tarakan #" + winner + "!  It's a winner!");
    }

    public static void setWinner(String tarakanName) {
        if (winner == null) winner = tarakanName;
    }
}