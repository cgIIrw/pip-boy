package classfile.constantpool.constantInfos;

import classfile.utils.ClassReader;
import classfile.constantpool.ConstantInfo;

import java.io.*;

/**
 * Created by yin on 18/4/16.
 */
public class ConstantUtf8Info implements ConstantInfo {
    private final int tag = 1;
    private String str;

    @Override
    public void readInfo(ClassReader reader) {
        int length = reader.readUint16();
        byte[] bytes = reader.readBytes(length);
        str = new String(bytes, 0, bytes.length);
    }

    @Override
    public int getTag() {
        return tag;
    }

    public String getStr() {
        return str;
    }
}
