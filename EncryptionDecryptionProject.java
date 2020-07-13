import java.util.*;
import java.io.*;
import java.lang.String;
import java.nio.file.*;

public class EncryptionDecryptionProject {

    public static void main(String[] args) {
        int cipher = 0;
        boolean decrypter = false;
        boolean writeToFile = false;
        boolean chooseAlgo = false;
        String word = "";
        String absoluteWritePath = "";
        String alg = "";

        //checks for -mode, -key, -data, -alg, -in, -out input
        for (int i = 1; i < args.length; i += 2) {
            if (args[i].equals("dec") && args[i - 1].equals("-mode"))
                decrypter = true;

            else if (args[i].matches("\\d+") && args[i - 1].equals("-key"))
                cipher = Integer.parseInt(args[i]);

            else if (args[i].matches("^.*$") && args[i - 1].equals("-data"))
                word = args[i];

            else if (args[i].matches("^.*$") && args[i - 1].equals("-alg")) {
                alg = args[i];
                chooseAlgo = true;
            } else if (args[i].matches(".*.txt") && args[i - 1].equals("-in")) {
                if (!word.equals(""))
                    continue;
                String absoluteReadPath = FileSystems.getDefault().getPath(args[i]).normalize().toAbsolutePath().toString(); //gets absolute path of file from input
                word = readFile(absoluteReadPath);
            } else if (args[i].matches(".*.txt") && args[i - 1].equals("-out")) {       //checks for -out input
                absoluteWritePath = FileSystems.getDefault().getPath(args[i]).normalize().toAbsolutePath().toString();
                writeToFile = true;
            }
        }

        //default algo is shift
        if (!chooseAlgo) {
            AlgoFactory obj = new AlgoFactory();    //call factory to produce desired object
            Algos algo = obj.produce(word, cipher, "shift");    //making the object of reference type interface, thus enabling methods encrypt and decrypt for overriding
            chooseOutput(algo, alg, decrypter, writeToFile, word, cipher, absoluteWritePath);
        } else {
            //take alg input and output that type of object
            AlgoFactory obj = new AlgoFactory();
            Algos algo = obj.produce(word, cipher, alg);
            chooseOutput(algo, alg, decrypter, writeToFile, word, cipher, absoluteWritePath);
        }

    }

    //output either in console or file depending on user choice
    public static void chooseOutput(Algos algo, String alg, boolean decrypter, boolean writeToFile, String word, int cipher, String absoluteWritePath) {
        if (!writeToFile) {
            System.out.println(chooseCrypt(algo, decrypter, word, cipher));
        } else {
            writeFile(algo, word, absoluteWritePath, decrypter, cipher, alg);
        }
    }

    //do either encrypt or decrypt depending on user choice
    public static String chooseCrypt(Algos algo, boolean decrypter, String word, int cipher) {
        if (!decrypter)
            return algo.encrypt(word, cipher);
        else
            return algo.decrypt(word, cipher);
    }

    //read the input file
    public static String readFile(String absoluteReadPath) {
        String word = "";
        File file = new File(absoluteReadPath);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                word += scanner.nextLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found, " + file);
        }
        return word;
    }

    //write to the output file
    public static void writeFile(Algos algo, String word, String file, boolean decrypter, int cipher, String alg) {
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(chooseCrypt(algo, decrypter, word, cipher));
        } catch (IOException e) {
            System.out.println("Error: Could not write to file, " + file);
        }
    }
}