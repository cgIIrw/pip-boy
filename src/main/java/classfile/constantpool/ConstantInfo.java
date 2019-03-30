package classfile.constantpool;

import classfile.utils.ClassReader;

/**
 * Created by cgrw on 18/4/15.
 */


public interface ConstantInfo {
    void readInfo(ClassReader reader);
    int getTag();
}


