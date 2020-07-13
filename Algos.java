public interface Algos {
    final int ASCII_LOWER_BEGIN = 97;
    final int NUM_ALPHABET = 26;
    final int ASCII_DIFF = 32;
    public String encrypt(String word, int cipher); //encrypt method for algos to override
    public String decrypt(String word, int cipher); //decrypt method for algos to override
}
