import java.util.Arrays;

/**
 * Created by Denis on 27.03.2017.
 */
public class SpeedTest {

    public static void main(String[] args) {
        long time1 = System.currentTimeMillis();
        long is = 0;
        //double[] a = new double[10];
        for (long i = 0; i < 1000000000L; i++) {
            //double[] a = new double[10];
            //Arrays.fill(a, 1.0);
            is = i;
            //System.out.println(is);
        }
        long time2 = System.currentTimeMillis();
        System.out.println(time2 - time1);
    }

}
