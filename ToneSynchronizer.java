import java.lang.Thread;
import java.lang.Runnable;

public class ToneSynchronizer {
    private ToneList tones1;
    private ToneList tones2;
    //private String[] song;

    public ToneSynchronizer(String[] song) {
        this.song = song;
        String[] tl1 = {"do", "mi", "sol", "si", "do-octave"};
        String[] tl2 = {"re", "fa", "la", "do-octave"};
        this.tones1 = new ToneList(tl1);
        this.tones2 = new ToneList(tl2);
        ToneList.setSong(song);
    }

    public void playSong() {
        tones1.start();
        tones2.start();

        tones1.join();
        tones2.join();
    }

    public static void main(String[] args) {

    }
}
