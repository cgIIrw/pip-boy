import classfile.MemberInfo;
import classfile.attributeinfos.CodeAttribute;
import rtda.Myframe;
import rtda.Mythread;

public class Interpreter {

    public void interpret(MemberInfo memberInfo) {
        CodeAttribute codeAttr = memberInfo.getCodeAttribute();
        int maxLocals = codeAttr.getMaxLocals();
        int maxStack = codeAttr.getMaxStack();
        byte[] bytecode = codeAttr.getCode();

        Mythread mythread = new Mythread();
        Myframe myframe = new Myframe(mythread, maxLocals, maxStack);



    }


}
