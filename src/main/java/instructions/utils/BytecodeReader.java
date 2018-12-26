package instructions.base;

// 字节码读取器
public class BytecodeReader {

    private int[] code;
    private int bpc;

    public void reset(int[] code, int bpc) {
        this.code = code;
        this.bpc = bpc;
    }

    public int getPc() {
        return bpc;
    }

    // 处理无符号的时候直接 & 0xff
    public int readUint8() {
        int i = this.code[this.bpc] & 0xff;
        this.bpc++;
        return i;
    }

    // 16位无符号
    public int readUint16() {
        int int01 = readUint8();
        int int02 = readUint8();
        return (int01 << 8) | int02;
    }

    // 32位无符号
    public long readInt32() {
        int int01 = readUint8();
        int int02 = readUint8();
        int int03 = readUint8();
        int int04 = readUint8();
        return (int01 << 24) | (int02 << 16) | (int03 << 8) | int04;
    }

    // 32位无符号元素构成的数组
    public long[] readInt32s(int n) {
        long[] longs = new long[n];
        for (int i = 0; i < n; i++) {
            longs[i] = this.readInt32();
        }
        return longs;
    }

    public void skipPadding() {
        while (bpc % 4 != 0) {
            readUint8();
        }
    }
}
