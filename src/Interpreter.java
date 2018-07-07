import classfile.ClassFile;
import classfile.MemberInfo;
import classfile.attributeinfos.CodeAttribute;
import instructions.Factory;
import instructions.base.BytecodeReader;
import instructions.base.Instruction;
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
        mythread.pushMyframe(myframe);

        loop(mythread, bytecode);

    }

    public void loop(Mythread mythread, byte[] bytecode) {
        Myframe myframe = mythread.popMyframe();
        BytecodeReader reader = new BytecodeReader();

        while (true) {
            int pc = myframe.getNextPC();
            mythread.setPc(pc);

            reader.reset(bytecode, pc);
            int opcode = reader.readUint8();

//            try {
                Instruction inst = Factory.newInstruction(opcode);
                inst.fetchOperands(reader);
                myframe.setNextPC(reader.getPc());
                inst.execute(myframe);
//            } catch (Exception e) {
//                System.out.println();
//                System.out.println("LocalVars: " + myframe.getLocalVars().getInt(1));
//                System.out.println("LocalVars: " + myframe.getOperandStack());
//                break;
//            }
        }
    }

    public static MemberInfo getMainMethod(ClassFile cf) {
        for (MemberInfo m : cf.getMethods()) {
            if (m.getName().equals("main")) {
                return m;
            }
        }
        return null;
    }



}
