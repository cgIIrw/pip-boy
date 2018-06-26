package classfile;

import java.io.*;

/**
 * Created by yin on 18/4/16.
 */
public class ConstantUtf8Info implements ConstantInfo {
    private String str;

    @Override
    public void readInfo(ClassReader reader) {
        int length = reader.readUint16();
        byte[] bytes = reader.readBytes(length);
        // 如果不用decodeMutf8()也能运行!!!!!!!!!!!!!!!!
//        try {
            // str = decodeMutf8(bytes);
            str = new String(bytes, 0 ,bytes.length);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }

    // 原书并没有这个函数,但是采用的直接调用字段str,所以还是采用这个函数
    public String getStr() {
        return str;
    }

    // 摘自原书作者classpy项目的代码
    public static String decodeMutf8(byte[] bytes) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream(bytes.length + 2);
        DataOutputStream dos = new DataOutputStream(baos);
        dos.writeShort(bytes.length);
        dos.write(bytes);

        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        DataInputStream dis = new DataInputStream(bais);
        return dis.readUTF();
    }


}
