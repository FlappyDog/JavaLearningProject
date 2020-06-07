package print_problem;

import java.util.concurrent.Exchanger;

public class ExchangerSolution {
    static Exchanger<String> exchanger = new Exchanger<>();

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                String val = "X";
                for (int i = 0; i < 26; i++) {
                    if ((val = exchanger.exchange(val)).equals("T")) {
                        System.out.print((char)('A' + i));
                    } else {
                        i --;
                    }
                }
                exchanger.exchange("T");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();


        new Thread(() -> {
            try {
                String val = "T";
                for (int i = 0; i < 26; i++) {
                    if ((val = exchanger.exchange(val)).equals("T")) {
                        System.out.print(i + 1);
                    } else {
                        i --;
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

}
