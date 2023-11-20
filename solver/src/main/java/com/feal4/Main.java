package com.feal4;

public class Main {

    private static void solveForK1(int key0) {
        for(int k1=0; k1<4096; k1++) {
            int key_tilda = BitManupulation.generate12BitKeyForInnerBytes(k1);
            int first_a1 = BitManupulation.calculateConstInnerBytesk1(0, key_tilda, key0);

            for(int w1=1; w1<FEAL4.count; w1++) {
                if(first_a1 != BitManupulation.calculateConstInnerBytesk1(w1, key_tilda,  key0))
                    break;

                if(w1 == FEAL4.count-1) {
                    for(int k2=0; k2<1048576; k2++) {
                        int key1 = BitManupulation.generate20BitKeyForOutterBytes(k2, key_tilda);
                        int first_a2 = BitManupulation.calculateConstOutteBytesk1(0, key0, key1);

                        for(int w2=1; w2<FEAL4.count; w2++) {
                            if(first_a2 != BitManupulation.calculateConstOutteBytesk1(w2, key0, key1))
                                break;

                            if(w2 == FEAL4.count-1)
                                solveForK2(key0, key1);
                        }
                    }
                }
            }
        }
    }

    private static void solveForK2(int key0, int key1) {
        for(int k1=0; k1<4096; k1++) {
            int key_tilda = BitManupulation.generate12BitKeyForInnerBytes(k1);
            int first_a1 = BitManupulation.calculateConstInnerBytesk2(0, key_tilda, key0, key1);

            for(int w1=1; w1<FEAL4.count; w1++) {
                if(first_a1 != BitManupulation.calculateConstInnerBytesk2(w1, key_tilda,  key0, key1))
                    break;

                if(w1 == FEAL4.count-1) {
                    for(int k2=0; k2<1048576; k2++) {
                        int key2 = BitManupulation.generate20BitKeyForOutterBytes(k2, key_tilda);
                        int first_a2 = BitManupulation.calculateConstOutteBytesk2(0, key0, key1, key2);

                        for(int w2=1; w2<FEAL4.count; w2++) {
                            if(first_a2 != BitManupulation.calculateConstOutteBytesk2(w2, key0, key1, key2))
                                break;

                            if(w2 == FEAL4.count-1)
                                solveForK3(key0, key1, key2);
                        }
                    }
                }
            }
        }
    }

    private static void solveForK3(int key0, int key1, int key2) {
        for(int k1=0; k1<4096; k1++) {
            int key_tilda = BitManupulation.generate12BitKeyForInnerBytes(k1);
            int first_a1 = BitManupulation.calculateConstInnerBytesk3(0, key_tilda, key0, key1, key2);

            for(int w1=1; w1<FEAL4.count; w1++) {
                if(first_a1 != BitManupulation.calculateConstInnerBytesk3(w1, key_tilda,  key0, key1, key2))
                    break;

                if(w1 == FEAL4.count-1) {
                    for(int k2=0; k2<1048576; k2++) {
                        int key3 = BitManupulation.generate20BitKeyForOutterBytes(k2, key_tilda);
                        int first_a2 = BitManupulation.calculateConstOutteBytesk3(0, key0, key1, key2, key3);

                        for(int w2=1; w2<FEAL4.count; w2++) {
                            if(first_a2 != BitManupulation.calculateConstOutteBytesk3(w2, key0, key1, key2, key3))
                                break;

                            if(w2 == FEAL4.count-1)
                                validate(key0, key1, key2, key3);
                        }
                    }
                }
            }
        }
    }

    private static void validate(int key0, int key1, int key2, int key3) {
        int y0 = BitManupulation.f(FEAL4.L0^FEAL4.R0^key0);
        int y1 = BitManupulation.f(FEAL4.L0^y0^key1);
        int y2 = BitManupulation.f(FEAL4.L0^FEAL4.R0^y1^key2);
        int y3 = BitManupulation.f(FEAL4.L0^y0^y2^key3);

        key0 = Integer.reverseBytes(key0);
        key1 = Integer.reverseBytes(key1);
        key2 = Integer.reverseBytes(key2);
        key3 = Integer.reverseBytes(key3);
        int key4 = Integer.reverseBytes(FEAL4.L0^FEAL4.R0^y1^y3^FEAL4.L4);
        int key5 = Integer.reverseBytes(FEAL4.R0^y1^y3^y0^y2^FEAL4.R4);

        int key[] = {key0, key1, key2, key3, key4, key5};
        byte[] data = new byte[8];

        for(int w=0; w<FEAL4.count; w++) {
            for (int i=0;i<8;i++)
                data[i] = (byte)(Integer.parseInt(FEAL4.cyphertext[w].substring(i * 2, (i * 2) + 2),16)&255);

            FealProgram.decrypt(data, key);

            StringBuilder sb = new StringBuilder(data.length * 2);
            for(byte b: data)
                sb.append(String.format("%02x", b));

            if(!FEAL4.plaintext[w].equals(sb.toString()))
                return;
        }

        System.out.println("0x" + Integer.toHexString(key0) + "\t0x" + Integer.toHexString(key1) + "\t0x" + Integer.toHexString(key2) + "\t0x" + Integer.toHexString(key3) + "\t0x" + Integer.toHexString(key4) + "\t0x" + Integer.toHexString(key5));
    }

    public static void main(String [] args) {

        FEAL4.newObject();
        Long currentTimeMillis = System.currentTimeMillis();
        System.out.println("Starting to decrypt, Time: " + currentTimeMillis + " ms");

        for(int k1=0; k1<4096; k1++) {
            int key_tilda = BitManupulation.generate12BitKeyForInnerBytes(k1);
            int first_a1 = BitManupulation.calculateConstInnerBytesk0(0, key_tilda);

            for(int w1=1; w1<FEAL4.count; w1++) {
                if(first_a1 != BitManupulation.calculateConstInnerBytesk0(w1, key_tilda))
                    break;

                if(w1 == FEAL4.count-1) {
                    for(int k2=0; k2<1048576; k2++) {
                        int key0 = BitManupulation.generate20BitKeyForOutterBytes(k2, key_tilda);
                        int first_a2 = BitManupulation.calculateConstOutteBytesk0(0, key0);

                        for(int w2=1; w2<FEAL4.count; w2++) {
                            if(first_a2 !=BitManupulation.calculateConstOutteBytesk0(w2, key0))
                                break;

                            if(w2 == FEAL4.count-1)
                                solveForK1(key0);
                        }
                    }
                }
            }
        }
        long endTime = System.currentTimeMillis();
        long totalTime = endTime-currentTimeMillis;
        System.out.println("Finished decryption, Total time taken: " + totalTime + " ms");
    }
}
