public class AlgoFactory {
    private String word;
    private int cipher;
    private String type;

    //produce either unicode algo or shift algo
    public static Algos produce(String word, int cipher, String type) {
        switch (type) {
            case "unicode":
                return new UnicodeAlgo(word, cipher);
            default:
                return new ShiftAlgo(word, cipher);
        }
    }
}

