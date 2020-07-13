public class ShiftAlgo implements Algos {

    private String word;
    private int cipher;

    ShiftAlgo(String word, int cipher) {
        this.word = word;
        this.cipher = cipher;
    }

    //encrypt which accounts for wrap around in alphabet
    @Override
    public String encrypt(String word, int cipher) {
        String result = "";
        int postcipher = 0;
        boolean wasUpper = false;
        for(int i = 0; i < word.length(); i++)
        {
            char c = word.charAt(i);
            postcipher = c;
            if(Character.isLetter(c)) {
                if (c < ASCII_LOWER_BEGIN) {    //change to lowercase
                    c += ASCII_DIFF;
                    wasUpper = true;
                }
                postcipher = ((c + cipher - ASCII_LOWER_BEGIN) % NUM_ALPHABET + ASCII_LOWER_BEGIN); //shift by cipher amount
                if (wasUpper)
                    postcipher -= ASCII_DIFF;   //turn back into uppercase if it was uppercase
                wasUpper = false;
            }
            result += (char)postcipher;     //add letter by letter into string
        }
        return result;
    }

    //decrypt which accounts for wrap around in alphabet
    @Override
    public String decrypt(String word, int cipher) {
        String result = "";
        int postcipher = 0;
        boolean wasUpper = false;
        for(int i = 0; i < word.length(); i++)
        {
            char c = word.charAt(i);
            postcipher = c;
            if(Character.isLetter(c)) {
                if (c < ASCII_LOWER_BEGIN) {    //change to lowercase
                    c += ASCII_DIFF;
                    wasUpper = true;
                }
                postcipher = ((c + (NUM_ALPHABET - cipher) - ASCII_LOWER_BEGIN) % NUM_ALPHABET + ASCII_LOWER_BEGIN); //shift by cipher amount
                if (wasUpper)
                    postcipher -= ASCII_DIFF;   //turn back into uppercase if it was uppercase
                wasUpper = false;
            }
            result += (char)postcipher;     //add letter by letter into string
        }
        return result;
    }
}

