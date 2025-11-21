import java.lang.Thread;

public class ToneList extends Thread {

    private static int counter = 0;
    private static String[] song;
    private static final String SHARED_TONE = "do-octave";
    private String[] tones;


    public ToneList(String[] t) {
        this.tones = t;
    }

    public void run() {
        while (counter <= song.length) {
            String tone = song[counter];
            if (tone == "do-octave") {
                this.wait();
                this.notify();
                playTone(tone);
                this.notify();
            }
            else if (isValidTone(tone)) {
                playTone(tone);
                counter++;
            } else {
                this.wait();
                this.notify();
            }
        }
    }

    public boolean isValidTone(String tone) {
        boolean found = false;
        for (String s : tones) {
            if (s == tone) {
                found = true;
                break;
            }
        }
        return found;
    }

    public void playTone(String tone) {
        System.out.println(tone);
    }

    public static void resetCounter() {
        counter = 0;
    }

    public static void setSong(String[] songNotes) {
        song = songNotes;
    }
}