package classfile;

/**
 * Created by yin on 18/4/15.
 */
public class ConstantPool {
    private ConstantInfo[] cp;

    // 读取数据初始化常量池
    public void readConstantPool(ClassReader reader) {
        int cpCount = reader.readUint16();
        cp = new ConstantInfo[cpCount];

        for (int i = 1; i < cpCount; i++) {
            cp[i] = CreateConstantInfo.readConstantInfo(reader, this);
            if (cp[i] instanceof ConstantLongInfo || cp[i] instanceof ConstantDoubleInfo) {
                i++;
            }
        }
    }
    // 通过索引找到常量池中的表
    public ConstantInfo getConstantInfo(int index) {
        if (cp == null) {
            throw new RuntimeException("没有readConstantPool初始化常量池");
        }
        // 为了简便这里就不作数组越界检查
        ConstantInfo cpInfo = cp[index];
        if (cpInfo == null) {
            throw new RuntimeException("Invalid constant pool index!");
        }
        return cpInfo;
    }







}
