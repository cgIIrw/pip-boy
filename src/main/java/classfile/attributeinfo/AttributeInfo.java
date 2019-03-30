package classfile.attributeinfo;

import classfile.utils.ClassReader;

/**
 * Created by cgrw on 18/4/22.
 */
// 属性表接口
public interface AttributeInfo {
    void readInfo(ClassReader reader);
}
