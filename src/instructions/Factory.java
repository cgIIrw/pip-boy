package instructions;

import instructions.base.Instruction;
import instructions.constants.ACONST_NULL;
import instructions.constants.Ldc.LDC;
import instructions.constants.Ldc.LDC2_W;
import instructions.constants.Ldc.LDC_W;
import instructions.constants.NOP;
import instructions.constants.*;
import instructions.control.return_ins.*;
import instructions.loads.aload.*;
import instructions.loads.dload.*;
import instructions.loads.fload.*;
import instructions.loads.iload.*;
import instructions.loads.lload.*;
import instructions.math.add.*;
import instructions.math.and.*;
import instructions.math.div.*;
import instructions.math.iinc.*;
import instructions.math.mul.*;
import instructions.math.neg.*;
import instructions.math.or.*;
import instructions.math.rem.*;
import instructions.math.sh.*;
import instructions.math.sub.*;
import instructions.math.xor.*;
import instructions.stack.dup.*;
import instructions.stack.pop.*;
import instructions.stack.swap.*;
import instructions.stores.astore.*;
import instructions.stores.dstore.*;
import instructions.stores.fstore.*;
import instructions.stores.istore.*;
import instructions.stores.lstore.*;
import instructions.references.*;

public class Factory {
    static NOP nop = new NOP();
    static ACONST_NULL aconst_null = new ACONST_NULL();
    static ICONST_M1 iconst_m1 = new ICONST_M1();
    static ICONST_0 iconst_0 = new ICONST_0();
    static ICONST_1 iconst_1 = new ICONST_1();
    static ICONST_2 iconst_2 = new ICONST_2();
    static ICONST_3 iconst_3 = new ICONST_3();
    static ICONST_4 iconst_4 = new ICONST_4();
    static ICONST_5 iconst_5 = new ICONST_5();
    static LCONST_0 lconst_0 = new LCONST_0();
    static LCONST_1 lconst_1 = new LCONST_1();
    static FCONST_0 fconst_0 = new FCONST_0();
    static FCONST_1 fconst_1 = new FCONST_1();
    static FCONST_2 fconst_2 = new FCONST_2();
    static DCONST_0 dconst_0 = new DCONST_0();
    static DCONST_1 dconst_1 = new DCONST_1();
    static ILOAD_0 iload_0 = new ILOAD_0();
    static ILOAD_1 iload_1 = new ILOAD_1();
    static ILOAD_2 iload_2 = new ILOAD_2();
    static ILOAD_3 iload_3 = new ILOAD_3();
    static LLOAD_0 lload_0 = new LLOAD_0();
    static LLOAD_1 lload_1 = new LLOAD_1();
    static LLOAD_2 lload_2 = new LLOAD_2();
    static LLOAD_3 lload_3 = new LLOAD_3();
    static FLOAD_0 fload_0 = new FLOAD_0();
    static FLOAD_1 fload_1 = new FLOAD_1();
    static FLOAD_2 fload_2 = new FLOAD_2();
    static FLOAD_3 fload_3 = new FLOAD_3();
    static DLOAD_0 dload_0 = new DLOAD_0();
    static DLOAD_1 dload_1 = new DLOAD_1();
    static DLOAD_2 dload_2 = new DLOAD_2();
    static DLOAD_3 dload_3 = new DLOAD_3();
    static ALOAD_0 aload_0 = new ALOAD_0();
    static ALOAD_1 aload_1 = new ALOAD_1();
    static ALOAD_2 aload_2 = new ALOAD_2();
    static ALOAD_3 aload_3 = new ALOAD_3();
    static ISTORE_0 istore_0 = new ISTORE_0();
    static ISTORE_1 istore_1 = new ISTORE_1();
    static ISTORE_2 istore_2 = new ISTORE_2();
    static ISTORE_3 istore_3 = new ISTORE_3();
    static LSTORE_0 lstore_0 = new LSTORE_0();
    static LSTORE_1 lstore_1 = new LSTORE_1();
    static LSTORE_2 lstore_2 = new LSTORE_2();
    static LSTORE_3 lstore_3 = new LSTORE_3();
    static FSTORE_0 fstore_0 = new FSTORE_0();
    static FSTORE_1 fstore_1 = new FSTORE_1();
    static FSTORE_2 fstore_2 = new FSTORE_2();
    static FSTORE_3 fstore_3 = new FSTORE_3();
    static DSTORE_0 dstore_0 = new DSTORE_0();
    static DSTORE_1 dstore_1 = new DSTORE_1();
    static DSTORE_2 dstore_2 = new DSTORE_2();
    static DSTORE_3 dstore_3 = new DSTORE_3();
    static ASTORE_0 astore_0 = new ASTORE_0();
    static ASTORE_1 astore_1 = new ASTORE_1();
    static ASTORE_2 astore_2 = new ASTORE_2();
    static ASTORE_3 astore_3 = new ASTORE_3();
    static POP pop = new POP();
    static POP2 pop2 = new POP2();
    static DUP dup = new DUP();
    static DUP_X1 dup_x1 = new DUP_X1();
    static DUP_X2 dup_x2 = new DUP_X2();
    static DUP2 dup2 = new DUP2();
    static DUP2_X1 dup2_x1 = new DUP2_X1();
    static DUP2_X2 dup2_x2 = new DUP2_X2();
    static SWAP swap = new SWAP();
    static IADD iadd = new IADD();
    static LADD ladd = new LADD();
    static FADD fadd = new FADD();
    static DADD dadd = new DADD();
    static ISUB isub = new ISUB();
    static LSUB lsub = new LSUB();
    static FSUB fsub = new FSUB();
    static DSUB dsub = new DSUB();
    static IMUL imul = new IMUL();
    static LMUL lmul = new LMUL();
    static FMUL fmul = new FMUL();
    static DMUL dmul = new DMUL();
    static IDIV idiv = new IDIV();
    static LDIV ldiv = new LDIV();
    static FDIV fdiv = new FDIV();
    static DDIV ddiv = new DDIV();
    static IREM irem = new IREM();
    static LREM lrem = new LREM();
    static FREM frem = new FREM();
    static DREM drem = new DREM();
    static INEG ineg = new INEG();
    static LNEG lneg = new LNEG();
    static FNEG fneg = new FNEG();
    static DNEG dneg = new DNEG();
    static ISHL ishl = new ISHL();
    static LSHL lshl = new LSHL();
    static ISHR ishr = new ISHR();
    static LSHR lshr = new LSHR();
    static IUSHR iushr = new IUSHR();
    static LUSHR lushr = new LUSHR();
    static IAND iand = new IAND();
    static LAND land = new LAND();
    static IOR ior = new IOR();
    static LOR lor = new LOR();
    static IXOR ixor = new IXOR();
    static LXOR lxor = new LXOR();
    static IRETURN ireturn = new IRETURN();
    static LRETURN lreturn = new LRETURN();
    static FRETURN freturn = new FRETURN();
    static DRETURN dreturn = new DRETURN();
    static ARETURN areturn = new ARETURN();
    static RETURN _return = new RETURN();

    public static Instruction newInstruction(int opCode) {
        switch (opCode) {
            case 0x00:
                return nop;
            case 0x01:
                return aconst_null;
            case 0x02:
                return iconst_m1;
            case 0x03:
                return iconst_0;
            case 0x04:
                return iconst_1;
            case 0x05:
                return iconst_2;
            case 0x06:
                return iconst_3;
            case 0x07:
                return iconst_4;
            case 0x08:
                return iconst_5;
            case 0x09:
                return lconst_0;
            case 0x0a:
                return lconst_1;
            case 0x0b:
                return fconst_0;
            case 0x0c:
                return fconst_1;
            case 0x0d:
                return fconst_2;
            case 0x0e:
                return dconst_0;
            case 0x0f:
                return dconst_1;
            case 0x10:
                return new BIPUSH();
            case 0x11:
                return new SIPUSH();
            case 0x12:
                return new LDC();
            case 0x13:
                return new LDC_W();
            case 0x14:
                return new LDC2_W();
            case 0x15:
                return new ILOAD();
            case 0x16:
                return new LLOAD();
            case 0x17:
                return new FLOAD();
            case 0x18:
                return new DLOAD();
            case 0x19:
                return new ALOAD();
            case 0x1a:
                return iload_0;
            case 0x1b:
                return iload_1;
            case 0x1c:
                return iload_2;
            case 0x1d:
                return iload_3;
            case 0x1e:
                return lload_0;
            case 0x1f:
                return lload_1;
            case 0x20:
                return lload_2;
            case 0x21:
                return lload_3;
            case 0x22:
                return fload_0;
            case 0x23:
                return fload_1;
            case 0x24:
                return fload_2;
            case 0x25:
                return fload_3;
            case 0x26:
                return dload_0;
            case 0x27:
                return dload_1;
            case 0x28:
                return dload_2;
            case 0x29:
                return dload_3;
            case 0x2a:
                return aload_0;
            case 0x2b:
                return aload_1;
            case 0x2c:
                return aload_2;
            case 0x2d:
                return aload_3;
            case 0x36:
                return new ISTORE();
            case 0x37:
                return new LSTORE();
            case 0x38:
                return new FSTORE();
            case 0x39:
                return new DSTORE();
            case 0x3a:
                return new ASTORE();
            case 0x3b:
                return istore_0;
            case 0x3c:
                return istore_1;
            case 0x3d:
                return istore_2;
            case 0x3e:
                return istore_3;
            case 0x3f:
                return lstore_0;
            case 0x40:
                return lstore_1;
            case 0x41:
                return lstore_2;
            case 0x42:
                return lstore_3;
            case 0x43:
                return fstore_0;
            case 0x44:
                return fstore_1;
            case 0x45:
                return fstore_2;
            case 0x46:
                return fstore_3;
            case 0x47:
                return dstore_0;
            case 0x48:
                return dstore_1;
            case 0x49:
                return dstore_2;
            case 0x4a:
                return dstore_3;
            case 0x4b:
                return astore_0;
            case 0x4c:
                return astore_1;
            case 0x4d:
                return astore_2;
            case 0x4e:
                return astore_3;
            case 0x57:
                return pop;
            case 0x58:
                return pop2;
            case 0x59:
                return dup;
            case 0x5a:
                return dup_x1;
            case 0x5b:
                return dup_x2;
            case 0x5c:
                return dup2;
            case 0x5d:
                return dup2_x1;
            case 0x5e:
                return dup2_x2;
            case 0x5f:
                return swap;
            case 0x60:
                return iadd;
            case 0x61:
                return ladd;
            case 0x62:
                return fadd;
            case 0x63:
                return dadd;
            case 0x64:
                return isub;
            case 0x65:
                return lsub;
            case 0x66:
                return fsub;
            case 0x67:
                return dsub;
            case 0x68:
                return imul;
            case 0x69:
                return lmul;
            case 0x6a:
                return fmul;
            case 0x6b:
                return dmul;
            case 0x6c:
                return idiv;
            case 0x6d:
                return ldiv;
            case 0x6e:
                return fdiv;
            case 0x6f:
                return ddiv;
            case 0x70:
                return irem;
            case 0x71:
                return lrem;
            case 0x72:
                return frem;
            case 0x73:
                return drem;
            case 0x74:
                return ineg;
            case 0x75:
                return lneg;
            case 0x76:
                return fneg;
            case 0x77:
                return dneg;
            case 0x78:
                return ishl;
            case 0x79:
                return lshl;
            case 0x7a:
                return ishr;
            case 0x7b:
                return lshr;
            case 0x7c:
                return iushr;
            case 0x7d:
                return lushr;
            case 0x7e:
                return iand;
            case 0x7f:
                return land;
            case 0x80:
                return ior;
            case 0x81:
                return lor;
            case 0x82:
                return ixor;
            case 0x83:
                return lxor;
            case 0x84:
                return new IINC();
            case 0xb2:
                return new GET_STATIC();
            case 0xb3:
                return new PUT_STATIC();
            case 0xb4:
                return new GET_FIELD();
            case 0xb5:
                return new PUT_FIELD();
            case 0xbb:
                return new NEW();
            case 0xc0:
                return new CHECK_CAST();
            case 0xc1:
                return new INSTANCE_OF();
            case 0xb6:
                return new INVOKE_VIRTUAL();
            case 0xb7:
                return new INVOKE_SPECIAL();
            case 0xb8:
                return new INVOKE_STATIC();
            case 0xac:
                return ireturn;
            case 0xad:
                return lreturn;
            case 0xae:
                return freturn;
            case 0xaf:
                return dreturn;
            case 0xb0:
                return areturn;
            case 0xb1:
                return _return;
            default:
                throw new RuntimeException("Unsupported opcode: " + opCode);
        }

    }
}
