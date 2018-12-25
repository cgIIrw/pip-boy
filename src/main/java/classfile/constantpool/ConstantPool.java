package classfile.constantpool;

import classfile.constantpool.constantInfos.*;
import classfile.utils.ClassReader;

/**
 * Created by yin on 18/4/15.
 */
// 常量池代码抽象，它是Class文件之中的资源仓库
public class ConstantPool {
    private ConstantInfo[] cp;

    // 读取数据初始化常量池
    public void readConstantPool(ClassReader reader) {
        int cpCount = reader.readUint16();
        cp = new ConstantInfo[cpCount];

        for (int i = 1; i < cpCount; i++) {
            cp[i] = ConstantInfoFactory.readConstantInfo(reader, this);
            if (cp[i] instanceof ConstantLongInfo || cp[i] instanceof ConstantDoubleInfo) {
                i++;
            }
        }
    }

    // 通过索引找到常量池中的表
    private ConstantInfo getConstantInfo(int index) {
        if (cp == null) {
            throw new RuntimeException("没有readConstantPool初始化常量池");
        }
        // 这里没作数组越界检查
        ConstantInfo cpInfo = cp[index];
        if (cpInfo == null) {
            throw new RuntimeException("不存在的常量池索引！");
        }
        return cpInfo;
    }

    public String[] getNameAndType(int index) {
        String[] str = new String[2];
        ConstantNameAndTypeInfo ntInfo = (ConstantNameAndTypeInfo) getConstantInfo(index);
        str[0] = getUtf8(ntInfo.getNameIndex());
        str[1] = getUtf8(ntInfo.getDescriptorIndex());
        return str;
    }

    public String getClassName(int index) {
        ConstantClassInfo classInfo = (ConstantClassInfo) getConstantInfo(index);
        return getUtf8(classInfo.getNameIndex());
    }

    public String getUtf8(int index) {
        ConstantUtf8Info utf8Info = (ConstantUtf8Info) getConstantInfo(index);
        // 在ConstantInfoFactory.readConstantInfo()时已经readInfo()过并获得了str
        return utf8Info.getStr();
    }

    public ConstantInfo[] getCp() {
        return cp;
    }
}
