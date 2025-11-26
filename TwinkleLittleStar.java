

public class TwinkleLittleStar {
    public static void main(String[] args) {
        
        String[] song = {
            "do", "do", "sol", "sol", "la", "la", "sol", "pause", "fa", "fa", "mi", "mi", "re", "re", "do", "pause",
            "sol", "sol", "fa", "fa", "mi", "mi", "re", "pause", "sol", "sol" ,"fa", "fa", "mi", "mi", "re", "pause",
            "do", "do", "sol", "sol", "la", "la", "sol", "pause", "fa", "fa", "mi", "mi", "re", "re", "do", "pause"
        };

        ToneSynchronizer twinkleStar = new ToneSynchronizer(song);
        twinkleStar.playSong();
    }
}
