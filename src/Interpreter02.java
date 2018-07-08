import classpath.ClassPath;
import instructions.Factory;
import instructions.base.BytecodeReader;
import instructions.base.Instruction;
import rtda.Myframe;
import rtda.Mythread;
import rtda.heap.MyMethod;
import rtda.heap.Myclass;
import rtda.heap.MyclassLoader;

public class Interpreter02 {

    public void interpret(MyMethod myMethod) {
        Mythread mythread = new Mythread();
        Myframe myframe = new Myframe(mythread, myMethod);
        mythread.pushMyframe(myframe);

        loop(mythread, myMethod.getCode());
    }





    public void work(ClassPath cp, String className) {
        MyclassLoader myclassLoader = new MyclassLoader(cp);

        Myclass mainClass = myclassLoader.loadClass(className);
        MyMethod mainMethod = mainClass.getStaticMethod("main", "([Ljava/lang/String;)V");
        if (mainMethod != null) {
            interpret(mainMethod);
        } else {
            System.out.println("-----------------");
        }


    }


    public void loop(Mythread mythread, int[] bytecode) {
        Myframe myframe = mythread.getTopFrame();
        BytecodeReader reader = new BytecodeReader();

        while (true) {
            int pc = myframe.getNextPC();
            mythread.setPc(pc);

            reader.reset(bytecode, pc);
            int opcode = reader.readUint8();

            try {
            Instruction inst = Factory.newInstruction(opcode);
            inst.fetchOperands(reader);
            myframe.setNextPC(reader.getPc());


            System.out.println(pc + inst.toString());
            inst.execute(myframe);
            } catch (Exception e) {
//                System.out.println();
//                System.out.println("LocalVars: " + myframe.getLocalVars().getInt(1));
//                System.out.println("LocalVars: " + myframe.getOperandStack());
//                break;
           }
        }
    }




}


