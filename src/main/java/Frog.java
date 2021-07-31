/**
 * @author Igor Khristiuk
 */
public class Frog {
    public static final int MIN_POSITION = 0;
    public static final int MAX_POSITION = 10;

    protected int position;

    public Frog() {
        position = 5;
    }

    public boolean jump(int steps) {
        int newPosition = position + steps;
        if (newPosition < MIN_POSITION || newPosition > MAX_POSITION) {
            return false;
        } else {
            position = newPosition;
            return true;
        }
    }

    public void print() {
        int[] pic = new int[11];
        for (int i = 0; i < MAX_POSITION + 1; i++) {
            pic[i] = 0;
        }
        pic[this.position] = 1;

        for (int i = 0; i < MAX_POSITION + 1; i++) {
            System.out.print(pic[i] + " ");
        }
    }

}
