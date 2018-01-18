package reversi;

/**
 * Counter.
 */
public class Counter {
    private int count;

    /**
     * Counter.
     *
     * constructor.
     */
    public Counter() {
        this.count = 2;
    }

    /**
     * increase.
     *
     * increase the counter.
     *
     * @param number    the number to add to the counter.
     */
    public void increase(int number) {
        this.count += number;
    }

    /**
     * decrease.
     *
     * decrease the counter.
     *
     * @param number    the number to sub from the counter.
     */
    public void decrease(int number) {
        this.count -= number;
        if(this.count < 0) {
            this.count = 0;
        }
    }

    /**
     * get.
     *
     * @return      the value of counter.
     */
    public int get() {
        return this.count;
    }
}