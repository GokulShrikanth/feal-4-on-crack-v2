package com.feal4;

public class Main {

    private static void solveForK1(int key0) {
        for(int k1=0; k1<4096; k1++) {
            int keyTilda = BitManipulation.generate12BitKeyForInnerBytes(k1);
            int firstA1 = BitManipulation.calcInnerBytesKey1(0, keyTilda, key0);

            for(int w1=1; w1<FEAL4.count; w1++) {
                if(firstA1 != BitManipulation.calcInnerBytesKey1(w1, keyTilda,  key0))
                    break;

                if(w1 == FEAL4.count-1) {
                    for(int k2=0; k2<1048576; k2++) {
                        int key1 = BitManipulation.generate20BitKeyForOuterBytes(k2, keyTilda);
                        int firstA2 = BitManipulation.calcOuterBytesKey1(0, key0, key1);

                        for(int w2=1; w2<FEAL4.count; w2++) {
                            if(firstA2 != BitManipulation.calcOuterBytesKey1(w2, key0, key1))
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
            int keyTilda = BitManipulation.generate12BitKeyForInnerBytes(k1);
            int firstA1 = BitManipulation.calcInnerBytesKey2(0, keyTilda, key0, key1);

            for(int w1=1; w1<FEAL4.count; w1++) {
                if(firstA1 != BitManipulation.calcInnerBytesKey2(w1, keyTilda,  key0, key1))
                    break;

                if(w1 == FEAL4.count-1) {
                    for(int k2=0; k2<1048576; k2++) {
                        int key2 = BitManipulation.generate20BitKeyForOuterBytes(k2, keyTilda);
                        int firstA2 = BitManipulation.calcOuterBytesKey2(0, key0, key1, key2);

                        for(int w2=1; w2<FEAL4.count; w2++) {
                            if(firstA2 != BitManipulation.calcOuterBytesKey2(w2, key0, key1, key2))
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
            int keyTilda = BitManipulation.generate12BitKeyForInnerBytes(k1);
            int firstA1 = BitManipulation.calcInnerBytesKey3(0, keyTilda, key0, key1, key2);

            for(int w1=1; w1<FEAL4.count; w1++) {
                if(firstA1 != BitManipulation.calcInnerBytesKey3(w1, keyTilda,  key0, key1, key2))
                    break;

                if(w1 == FEAL4.count-1) {
                    for(int k2=0; k2<1048576; k2++) {
                        int key3 = BitManipulation.generate20BitKeyForOuterBytes(k2, keyTilda);
                        int firstA2 = BitManipulation.calcOuterBytesKey3(0, key0, key1, key2, key3);

                        for(int w2=1; w2<FEAL4.count; w2++) {
                            if(firstA2 != BitManipulation.calcOuterBytesKey3(w2, key0, key1, key2, key3))
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
        int y0 = BitManipulation.f(FEAL4.L0^FEAL4.R0^key0);
        int y1 = BitManipulation.f(FEAL4.L0^y0^key1);
        int y2 = BitManipulation.f(FEAL4.L0^FEAL4.R0^y1^key2);
        int y3 = BitManipulation.f(FEAL4.L0^y0^y2^key3);

        int key4 = FEAL4.L0^FEAL4.R0^y1^y3^FEAL4.L4;
        int key5 = FEAL4.R0^y1^y3^y0^y2^FEAL4.R4;

        int[] key = {key0, key1, key2, key3, key4, key5};
        byte[] data = new byte[8];

        for(int w=0; w<FEAL4.count; w++) {
            for (int i=0;i<8;i++)
                data[i] = (byte)(Integer.parseInt(FEAL4.ciphertext[w].substring(i * 2, (i * 2) + 2),16)&255);

            FealProgram.decrypt(data, key);

            StringBuilder sb = new StringBuilder(data.length * 2);
            for(byte b: data)
                sb.append(String.format("%02x", b));

            if(!FEAL4.plaintext[w].contentEquals(sb))
                return;
        }

        System.out.println("0x" + Integer.toHexString(key0) + "\t0x" + Integer.toHexString(key1) + "\t0x" + Integer.toHexString(key2) + "\t0x" + Integer.toHexString(key3) + "\t0x" + Integer.toHexString(key4) + "\t0x" + Integer.toHexString(key5));
    }

    public static void main(String [] args) {

        FEAL4.newObject();
        long currentTimeMillis = System.currentTimeMillis();
        System.out.println("Starting to decrypt, Current Time in millis: " + currentTimeMillis + " ms");

        for(int k1=0; k1<4096; k1++) {
            int keyTilda = BitManipulation.generate12BitKeyForInnerBytes(k1);
            int firstA1 = BitManipulation.calcInnerBytesKey0(0, keyTilda);

            for(int w1=1; w1<FEAL4.count; w1++) {
                if(firstA1 != BitManipulation.calcInnerBytesKey0(w1, keyTilda))
                    break;

                if(w1 == FEAL4.count-1) {
                    for(int k2=0; k2<1048576; k2++) {
                        int key0 = BitManipulation.generate20BitKeyForOuterBytes(k2, keyTilda);
                        int firstA2 = BitManipulation.calcOuterBytesKey0(0, key0);

                        for(int w2=1; w2<FEAL4.count; w2++) {
                            if(firstA2 != BitManipulation.calcOuterBytesKey0(w2, key0))
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
