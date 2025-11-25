

public class TwinkleLittleStar {
    public static void main(String[] args) {
        String[] song = {
            "do", "do", "sol", "sol", "la", "la", "sol", "fa", "fa", "mi", "mi", "re", "re", "do",
            "sol", "sol", "fa", "fa", "mi", "mi", "re", "sol", "sol" ,"fa", "fa", "mi", "mi", "re",
            "do", "do", "sol", "sol", "la", "la", "sol", "fa", "fa", "mi", "mi", "re", "re", "do"
        };

        ToneSynchronizer twinkleStar = new ToneSynchronizer(song);
        twinkleStar.playSong();
    }
}
