package classfile;

import java.math.BigInteger;

/**
 * @author cgIIrw
 * @date 2018/4/11
 */
public class ClassReader {
    private byte[] data = new byte[0];
    private int cursor = 0;

    public ClassReader(byte[] data) {
        this.data = data;
    }

    // byte to u1
    public int readUint8() {
        int val = data[cursor++] & 0xff;
        return val;
    }

    // byte to u2
    public int readUint16() {
        int val = 0;
        for (int i = 1 + cursor; i >= cursor; i--) {
            val |= (data[i] & 0xff) << ((1 + cursor - i) * 8);
        }
        cursor = cursor + 2;
        return val;
    }

    // byte to u4
    public long readUint32() {
        long val = 0;
        for (int i = 3 + cursor; i >= cursor; i--) {
            val |= (data[i] & 0xff) << ((3 + cursor - i) * 8);
        }
        cursor = cursor + 4;
        return val;
    }

    // byte to u8
//    public long readUint64() {
//        long val = 0;
//        for (int i = 7 + cursor; i >= cursor; i--) {
//            val |= (data[i] & 0xff) << ((7 - i) * 8);
//        }
//        cursor = cursor + 8;
//        return val;
//    }
    public BigInteger readUint64() {
        byte[] bytes = new byte[8];
        for (int i = 0; i < 8; i++) {
            bytes[i] = data[cursor + i];
        }
        cursor = cursor + 8;
        return new BigInteger(bytes);
    }

    // 读取uint16表
    public int[] readUint16s() {
        int n = readUint16();
        int[] s = new int[n];
        for (int i = 0; i < n; i++) {
            s[i] = readUint16();
        }
        return s;
    }

    // 用于读取指定数量的字节
    public byte[] readBytes(int n) {
        byte[] bytes = new byte[n];

        for (int i = 0; i < n; i++) {
            bytes[i] = data[cursor + i];
        }
        cursor = cursor + n;
        return bytes;
    }
}
