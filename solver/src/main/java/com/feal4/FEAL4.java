package com.feal4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FEAL4 {

    public static int count = 200;
    public static int L0, R0, L4, R4;
    public static String plaintext[] = new String[count];
    public static String cyphertext[] = new String[count];

    FEAL4() {}

    public static void newObject() {
        readText();
    }

    public static void splitPairs(int wordIndex) {
        L0 = (int) Long.parseLong(plaintext[wordIndex].substring(0,8), 16);
        R0 = (int) Long.parseLong(plaintext[wordIndex].substring(8), 16);
        L4 = (int) Long.parseLong(cyphertext[wordIndex].substring(0,8), 16);
        R4 = (int) Long.parseLong(cyphertext[wordIndex].substring(8), 16);
    }

    public static void readText() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("known.txt"));
            int count = 0;
            boolean isPlainText = true;
            String line = bufferedReader.readLine();

            while(line != null && count < plaintext.length) {
                if(line.length() != 0) {
                    if(isPlainText) {
                        plaintext[count] = line.substring(12);
                    }
                    else {
                        cyphertext[count] = line.substring(12);
                        count++;
                    }
                    isPlainText = !isPlainText;
                }
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
        }
        catch(FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
