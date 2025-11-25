public class ToneSynchronizer {
    private ToneList tones1;
    private ToneList tones2;

    public ToneSynchronizer(String[] song) {
        String[] tl1 = {"do", "mi", "sol", "si", "do-octave"};
        String[] tl2 = {"re", "fa", "la", "do-octave"};
        this.tones1 = new ToneList(tl1, "thread1");
        this.tones2 = new ToneList(tl2, "thread2");
        ToneList.setSong(song);
    }

    public void playSong() {
        tones1.start();
        tones2.start();

        try {
            tones1.join();
            tones2.join();
        } catch (Exception e) {
            System.out.println("Couldn't join song:");
            System.out.println(e.getMessage());
        }
        
    }

    public static void main(String[] args) {
        String[] toneSequence = {"do", "re", "mi", "fa", "sol", "la", "si", "do-octave"};
        ToneSynchronizer toneList = new ToneSynchronizer(toneSequence);
        toneList.playSong();

        System.out.println("\n--- Playing Twinkle Twinkle Little Star ---\n");

        String[] twinkle = {
            "do","do","sol","sol","la","la","sol",
            "fa","fa","mi","mi","re","re","do",

            "sol","sol","fa","fa","mi","mi","re",
            "sol","sol","fa","fa","mi","mi","re",

            "do","do","sol","sol","la","la","sol",
            "fa","fa","mi","mi","re","re","do"
        };

        ToneList.resetCounter(); //Reset counter
        ToneSynchronizer tw = new ToneSynchronizer(twinkle);
        tw.playSong();

        }
}
