import instructions.InstructionFactory;
import instructions.base.Instruction;
import instructions.utils.BytecodeReader;
import rtda.methodarea.Method_;
import rtda.stack.StackFrame_;
import rtda.stack.Thread_;

public class Interpret6 {

    public static void interpret(Method_ method) {
        Thread_ thread = new Thread_();
        StackFrame_ frame = new StackFrame_(thread, method);
        thread.pushStackFrame_(frame);
        loop(thread, method.getCode());
    }

    private static void loop(Thread_ thread, int[] code) {
        StackFrame_ frame = thread.popStackFrame_();
        BytecodeReader reader = new BytecodeReader();
        while (true) {
            int pc = frame.getNextPC();
            thread.setPc(pc);
            reader.reset(code, pc);
            int opcode = reader.readUint8();
            Instruction instruction = InstructionFactory.getInstruction(opcode);
            instruction.fetchOperands(reader);
            frame.setNextPC(reader.getPc());
            System.out.println("pc: " + pc + " inst: " + instruction);
            instruction.execute(frame);
        }

    }
}
