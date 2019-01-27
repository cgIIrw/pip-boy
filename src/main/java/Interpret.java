import instructions.InstructionFactory;
import instructions.base.Instruction;
import instructions.control.return_.RETURN;
import instructions.utils.BytecodeReader;
import rtda.methodarea.Method_;
import rtda.stack.StackFrame_;
import rtda.stack.Thread_;

public class Interpret {

    public static void interpret(Method_ method, boolean logInst) {
        Thread_ thread = new Thread_();
        StackFrame_ frame = new StackFrame_(thread, method);
        thread.pushStackFrame_(frame);
        loop(thread, logInst);
    }

    private static void loop(Thread_ thread, boolean logInst) {

        BytecodeReader reader = new BytecodeReader();
        while (thread.getVirtualMachineStack().getEndMethodTop() != null) {
            StackFrame_ frame = thread.getTopFrame();
            int pc = frame.getNextPC();
            thread.setPc(pc);
            reader.reset(frame.getMethod_().getCode(), pc);
            int opcode = reader.readUint8();
            Instruction instruction = InstructionFactory.getInstruction(opcode);
            instruction.fetchOperands(reader);
            frame.setNextPC(reader.getPc());

            if (logInst) {
                logInstruction(frame, instruction);
            }
            instruction.execute(frame);
        }
    }

    private static void logInstruction(StackFrame_ frame, Instruction instruction) {
        Method_ method_ = frame.getMethod_();
        String className = method_.getClass_().getThisClassName();
        String methodName = method_.getName();
        int pc = frame.getThread_().getPc();
        System.out.println(className + " " + methodName + " " + pc + " " + instruction);
    }
}
