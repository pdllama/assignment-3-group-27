import java.lang.Thread;

public class ToneList extends Thread {

    private static int counter = 0;
    private String name;
    private static String[] song;
    // private static final String SHARED_TONE = "do-octave";
    private String[] tones;


    public ToneList(String[] t, String n) {
        this.tones = t;
        this.name = n;
    }

    public synchronized void run() {
        while (counter <= song.length) {
            System.out.println(name);
            int c = getCounter();
            String tone = song[c];
            if (tone == "do-octave") {
                System.out.println("DO-OCTAVE");
                try
                {
                    this.notifyAll();
                    this.wait();
                    
                    playTone(tone);
                    counter++;
                    this.notifyAll();
                } catch (Exception e) {
                    System.out.println("Exception occurred");
                    System.out.println(e.getMessage());
                }
                
            }
            else if (isValidTone(tone)) {
                playTone(tone);
                incrementCounter();
                try {
                    this.notifyAll();
                    this.wait();
                } catch (Exception e) {
                    System.out.println("Wait Exception occurred");
                }
            } else {
                try {
                    this.notifyAll();
                    this.wait();
                } catch (Exception e) {
                    System.out.println("Wait exception occurred");
                }
            }
        }
        return;
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

    private synchronized int getCounter() {
        return counter;
    }
    private synchronized void incrementCounter() {
        counter++;
    }
}