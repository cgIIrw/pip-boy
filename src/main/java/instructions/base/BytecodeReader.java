package instructions.base;

public class BytecodeReader {

    int[] code;
    int pc;

    public void reset(int[] code, int pc) {
        this.code = code;
        this.pc = pc;
    }

    public int getPc() {
        return pc;
    }

    // 读取有符号数，可以直接返回byte，在最高位它自动进行应该的处理
    public int readInt8() {
        int i = this.code[this.pc];
        this.pc++;
        return i;
    }

    // 处理无符号的时候直接 & 0xff
    public int readUint8() {
        int i = this.code[this.pc] & 0xff;
        this.pc++;
        return i;
    }

    public int readInt16() {
        return (short)readUint16();
    }

    public int readUint16() {
        int int01 = readUint8();
        int int02 = readUint8();
        return (int01 << 8) | int02;
    }

    // readInt32是有符号的，最后返回三十二位最高位会自动为符号位
    public int readInt32() {
        int int01 = readUint8();
        int int02 = readUint8();
        int int03 = readUint8();
        int int04 = readUint8();
        return (int01 << 24) | (int02 << 16) | (int03 << 8) | int04;
    }

    public int[] readInt32s(int n) {
        int[] ints = new int[n];
        for (int i = 0; i < n; i++) {
            ints[i] = this.readInt32();
        }
        return ints;
    }

    public void skipPadding() {
        while (pc % 4 != 0) {
            readUint8();
        }
    }
}
