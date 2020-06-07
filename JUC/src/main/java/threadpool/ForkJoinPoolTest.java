package threadpool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

public class ForkJoinPoolTest {

    static class AddTask extends RecursiveAction {
        static int max = 10;

        int start;
        int end;

        public AddTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected void compute() {
            if (end - start < max) {
                for (int i = start; i <= end; i++) {
                    // Do sth
                }
            } else {
                int mid = start + ((end - start) >> 1);
                AddTask sub1 = new AddTask(start, mid);
                AddTask sub2 = new AddTask(mid + 1, end);
                sub1.fork();
                sub2.fork();
            }
        }
    }

    static class AddTaskRet extends RecursiveTask<Integer> {

        static int max = 10;

        int start;
        int end;

        public AddTaskRet(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Integer compute() {
            if (end - start < max) {
                int sum = 0;
                for (int i = start; i <= end ; i++) {
                    sum += i;
                }
                return sum;
            } else {
                int mid = start + ((end - start) >> 1);
                AddTaskRet sub1 = new AddTaskRet(start, mid);
                AddTaskRet sub2 = new AddTaskRet(mid + 1, end);

                sub1.fork();
                sub2.fork();

                return sub1.join() + sub2.join();
            }
        }

        static int addCorrect(int start, int end) {
            int sum = 0;
            for (int i = start; i <= end; i ++) {
                sum += i;
            }
            return sum;
        }

        public static void main(String[] args) {
            ForkJoinPool fjp1 = new ForkJoinPool();
            AddTask t1 = new AddTask(1, 100);
            AddTaskRet t2 = new AddTaskRet(1, 100);

            fjp1.execute(t1);
            fjp1.execute(t2);

            System.out.println(addCorrect(1, 100));
            System.out.println("T1: " + t1.join());
            System.out.println("T2: " + t2.join());



        }
    }

}
