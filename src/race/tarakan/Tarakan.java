package race.tarakan;

import race.TarakanRaceAppl;

public class Tarakan implements Runnable {
    String tarakanName;
    int runCycles;
    int sleepTime;

    public Tarakan(String tarakanName, int runCycles, int sleepTime) {
        this.tarakanName = tarakanName;
        this.runCycles = runCycles;
        this.sleepTime = sleepTime;
    }

    @Override
    public void run() {
        System.out.println("Tarakan #" + tarakanName + " is running. In Thread #" + Thread.currentThread().getName());

        for (int i = 0; i < runCycles; i++) {
            System.out.println("Tarakan #" + tarakanName + " is running round number " + (i + 1));

            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Tarakan #" + tarakanName + " finished");

        TarakanRaceAppl.setWinner(tarakanName);
    }
}