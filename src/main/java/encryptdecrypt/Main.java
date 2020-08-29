package encryptdecrypt;

public class Main {
    /*
    At this stage, you need to add the ability to read and
    write original and cipher data to files. The program must
    parse two additional arguments -in and -out to specify
    the full name of a file to read data and to write the
    result. Arguments -mode, -key, and -data should still
    work as before.
    Your program should read data from -data or from a file
    written in the -in argument. That's why you can't have
    both -data and -in arguments simultaneously, only one of
    them.
    If there is no -mode, the program should work in enc mode.
    If there is no -key, the program should consider that key = 0.
    If there is no -data, and there is no -in the program
    should assume that the data is an empty string.
    If there is no -out argument, the program must print data
    to the standard output.
    If there are both -data and -in arguments, your program
    should prefer -data over -in.
    If there is a non-standard situation (an input file does
    not exist or an argument doesn’t have a value), the program
    should not fail. Instead, it must display a clear message
    about the problem and stop successfully. The message should
    contain the word "Error" in any case.
    */

    public static void main(String[] args) {

        String mode = "enc";
        int key = 5;
        String data = " ";
        String newData;


        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-mode")) {
                mode = args[i + 1];
                break;
            }
        }

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-key")) {
                key = Integer.valueOf(args[i + 1]);
                break;
            }
        }

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-data")) {
                data = args[i + 1];
                break;
            }
        }

        if (mode.equals("enc")) {
            newData = Main.encrypt(data, key);
            System.out.println(newData);
        } else {
            newData = Main.decrypt(data, key);
            System.out.println(newData);
        }
    }

    public static String encrypt(String message, int key) {
        StringBuilder sb = new StringBuilder(message);
        for (int i = 0; i < sb.length(); i++) {
            int newChar = sb.charAt(i);
            if (newChar + key > 126) {
                newChar = 31 + (key - (126 - newChar));
            } else {
                newChar += key;
            }
            sb.setCharAt(i, (char) newChar);
        }
        return sb.toString();
    }

    public static String decrypt(String message, int key) {
        StringBuilder sb = new StringBuilder(message);
        for (int i = 0; i < sb.length(); i++) {
            int newChar = sb.charAt(i);
            if (newChar - key < 32) {
                newChar = 127 - (key - (newChar - 32));
            } else {
                newChar -= key;
            }
            sb.setCharAt(i, (char) newChar);
        }
        return sb.toString();
    }
}
