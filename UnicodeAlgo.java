public class UnicodeAlgo implements Algos{
    private String word;
    private int cipher;

    UnicodeAlgo(String word, int cipher) {
        this.word = word;
        this.cipher = cipher;
    }

    //encrypt/decrypt which is just plain shifting, no wrap around
    @Override
    public String encrypt(String word, int cipher) {
        String result = "";
        int precipher = 0;
        for(int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            precipher = c + cipher;     //shift forward cipher amount
            result += (char)precipher;
        }
        return result;
    }

    @Override
    public String decrypt(String word, int cipher) {
        String result = "";
        int precipher = 0;
        for(int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            precipher = c - cipher;     //shift back cipher amount
            result += (char)precipher;
        }
        return result;
    }
}

