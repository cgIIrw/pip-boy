package classfile;

import classfile.constantInfos.*;

/**
 * Created by yin on 18/4/15.
 */


public interface ConstantInfo {
    void readInfo(ClassReader reader);
    int getId();
}


