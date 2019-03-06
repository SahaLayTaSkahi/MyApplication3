package Constants;

public class Constants {
    /*
    The basic formula for the frequencies of the notes of the equal tempered scale is given by
    fn = f0 * (a)n
    where
    f0 = the frequency of one fixed note which must be defined. A common choice is setting the A above middle C (A4) at f0 = 440 Hz.
    n = the number of half steps away from the fixed note you are. If you are at a higher note, n is positive. If you are on a lower note, n is negative.
    fn = the frequency of the note n half steps away.
    a = (2)1/12 = the twelth root of 2 = the number which when multiplied by itself 12 times equals 2 = 1.059463094359...
     */




    public class Pitches{
        public static final double A4 = 440.0;
        public static final double A5 = 880.0;
    }
}
