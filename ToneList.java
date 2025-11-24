import java.lang.Thread;
import java.io.File;

public class ToneList extends Thread {

    private static Object LOCK = new Object(); // Generic object used to .wait() and .notify() threads to synchronize them
    private static int COUNTER = 0; // Counter of the song
    private static FilePlayer filePlayer = new FilePlayer(); // File player to play the tone
    private static int MODE = 2; // Decides whether we log the tone or play the tone. 0 = log, 1 = play 2 = both;

    private String name; // was mainly used for debugging

    private static String[] song; // Static variable = shared song among all threads.
    private String[] tones; // The tones allowed for the thread


    public ToneList(String[] t, String n) {
        this.tones = t;
        this.name = n;
    }

    public void run() {
        synchronized (LOCK) {
            int lastCounter = 0;
        while (COUNTER <= song.length && lastCounter != song.length) {
            int c = getCounter();
            String tone = song[c];
            if (tone == "do-octave") {
                try
                {
                    LOCK.notify();
                    LOCK.wait();
                    
                    if (MODE == 2) {logTone(tone); playTone(tone);}
                    else if (MODE == 0) {logTone(tone);}
                    else {playTone(tone);}
                    lastCounter = song.length;

                    // Small delay when playing so we can actually hear the tone before the program terminates.
                    // the two do-octaves still play at functionally the same time
                    if (MODE != 0 && name == "thread1") {sleep(500);} 

                    LOCK.notify();
                } catch (Exception e) {
                    System.out.println("Exception occurred");
                    System.out.println(e.getMessage());
                }
                
            }
            else if (isValidTone(tone)) {
                if (MODE == 2) {logTone(tone); playTone(tone);}
                else if (MODE == 0) {logTone(tone);}
                else {playTone(tone);}
                incrementCounter();
                try {
                    LOCK.notify();
                    LOCK.wait();
                } catch (Exception e) {
                    System.out.println("Wait Exception occurred");
                }
            } else {
                try {
                    LOCK.notify();
                    LOCK.wait();
                } catch (Exception e) {
                    System.out.println("Wait exception occurred");
                }
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

    public void logTone(String tone) {
        System.out.println(tone);
    }

    public void playTone(String tone) {
        try {
        String filePath = System.getProperty("user.dir") + 
                            File.separator + "Sounds"+
                            File.separator+tone+".wav";
        filePlayer.play(filePath);
        if (tone != "do-octave") {sleep(500);} // do-octave has its own delay
        } catch (Exception e) {
            System.out.println("CAN'T SLEEP");
        }
    }

    public static void resetCounter() {
        COUNTER = 0;
    }

    public static void setSong(String[] songNotes) {
        song = songNotes;
    }

    private synchronized int getCounter() {
        return COUNTER;
    }
    private synchronized void incrementCounter() {
        COUNTER++;
    }

    public static void switchMode() {
        if (MODE == 0) {MODE = 1;}
        else if (MODE == 1) {MODE = 2;}
        else {MODE = 0;}
    }
}