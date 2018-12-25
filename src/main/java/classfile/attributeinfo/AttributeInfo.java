package classfile;

import classfile.utils.ClassReader;

/**
 * Created by yin on 18/4/22.
 */
public interface AttributeInfo {
    void readInfo(ClassReader reader);
}
