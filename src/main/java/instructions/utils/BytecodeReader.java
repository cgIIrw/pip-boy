package instructions.utils;

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

    // 编写的时候原本code是byte类型数组，处理8位无
    // 符号的时候 & 0xff，但是在项目其它处已经进行
    // 了byte转换int，所以直接将code设置为int数组
    public int readUint8() {
        int i = this.code[this.bpc] & 0xff;
        this.bpc++;
        return i;
    }

    // 8位有符号
    public int readInt8() {
        int i = this.code[this.bpc];
        bpc++;
        return i;
    }

    // 16位无符号
    public int readUint16() {
        int int01 = readUint8();
        int int02 = readUint8();
        return (int01 << 8) | int02;
    }

    // 16位有符号
    public int readInt16() {
        // 直接用short截断int型数据
        return (short) readUint16();
    }

    // 32位有符号
    public int readInt32() {
        int int01 = readUint8();
        int int02 = readUint8();
        int int03 = readUint8();
        int int04 = readUint8();
        return (int01 << 24) | (int02 << 16) | (int03 << 8) | int04;
    }

    // 32位有符号元素构成的数组
    public int[] readInt32s(int n) {
        int[] ints = new int[n];
        for (int i = 0; i < n; i++) {
            ints[i] = this.readInt32();
        }
        return ints;
    }

    public void skipPadding() {
        while (bpc % 4 != 0) {
            readUint8();
        }
    }
}
