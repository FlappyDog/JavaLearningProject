package threadpool;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureTest {

    public static void main(String[] args) {
        CompletableFuture<?> fTM =
                CompletableFuture.supplyAsync(() -> getPriceTM())
                        .thenApply(String::valueOf)
                        .thenApply((str) -> "TM price : " + str)
                        .thenAccept(System.out::println);
        CompletableFuture<?> fTB = CompletableFuture.supplyAsync(() -> getPriceTB())
                        .thenApply(String::valueOf)
                        .thenApply((str) -> "TB price : " + str)
                        .thenAccept(System.out::println);
        CompletableFuture<?> fJD = CompletableFuture.supplyAsync(() -> getPriceJD())
                        .thenApply(String::valueOf)
                        .thenApply((str) -> "JD price : " + str)
                        .thenAccept(System.out::println);

        CompletableFuture.allOf(fTM, fTB, fJD).join();

        CompletableFuture<Integer> priceTM =
                CompletableFuture.supplyAsync(() -> getPriceTM());
        CompletableFuture<Integer> priceTB =
                CompletableFuture.supplyAsync(() -> getPriceTB());
        CompletableFuture<Integer> priceJD =
                CompletableFuture.supplyAsync(() -> getPriceJD());

        priceTM.thenCombine(priceTB, (x, y) -> Math.min(x, y))
                .thenCombine(priceJD, (x, y) -> Math.min(x, y))
                .thenApply((i) -> "Min Price: " + i)
                .thenAccept(System.out::println)
                .join();

    }

    private static int getPriceTB() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 1;
    }

    private static int getPriceJD() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 5;
    }

    private static int getPriceTM() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 10;
    }

}
