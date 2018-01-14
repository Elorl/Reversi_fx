package reversi;

/**
 * Counter.
 */
public class Counter {
    private int count;

    public Counter() {
        this.count = 2;
    }

    public void increase(int number) {
        this.count += number;
    }

    public void decrease(int number) {
        this.count -= number;
        if(this.count < 0) {
            this.count = 0;
        }
    }

    public int get() {
        return this.count;
    }
}