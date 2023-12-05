package race;

// Создать приложение-игру «TarakansRace»: Таракан - поток, выполняющий цикл итераций.
// На каждой итерации поток выводит свой собственный номер и
// переходит в режим сна на случайное количество миллисекунд [2-5]. Диапазон времени сна должен быть одинаковым для всех тараканов.
// Таракан, закончивший круг первым, считается победителем.

// Приложение должно принимать количество тараканов и расстояние (количество итераций) из консольного ввода/вывода.

// В конце игры должно быть распечатано следующее сообщение “Congratulations to tarakan #X (winner)” где Х - номер таракана-победителя

import race.tarakan.Tarakan;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TarakanRaceAppl {
    static String winner = null;

    public static void main(String[] args) throws InterruptedException {
        int min = 2;
        int max = 5;
        int runCycles = 10;

        Random random = new Random();
        int sleepTime = min + random.nextInt((max + 1) - min);

        System.out.println("Tarakans Race START!");

        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            threads.add(new Thread(new Tarakan("Vasya-" + i, runCycles, sleepTime)));        // input: (qtyTar, runCycles, sleepTime)
        }

        for (Thread thread : threads) {
            thread.start();
//            thread.join();

        }
        for (Thread thread : threads) {
//            thread.run();
            thread.join();

        }

        System.out.println("Tarakans Race FINISH!");
        System.out.println("Congratulations to tarakan #" + winner + "!  It's a winner!");
    }

    public static void setWinner(String tarakanNumber) {
        winner = winner == null ? tarakanNumber : winner;
    }

}