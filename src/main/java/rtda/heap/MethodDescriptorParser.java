package rtda.heap;

import java.util.ArrayList;

public class MethodDescriptorParser {
    private String raw;
    private int offset;
    MethodDescriptor parsed;


    public MethodDescriptor parseMethodDescriptor(String descriptor) {
        return this.parse(descriptor);
    }




    public MethodDescriptor parse(String descriptor) {
        this.raw = descriptor;
        this.parsed = new MethodDescriptor();
        startParams();
        parseParamTypes();
        endParams();
        parseReturnType();
        finish();
        return this.parsed;
    }

    private void startParams() {
        if ('(' != readUint8()) {
            causePanic();
        }
    }

    private void endParams() {
        if (')' != readUint8()) {
            causePanic();
        }
    }

    private void finish() {
        if (offset != raw.length()) {
            causePanic();
        }
    }

    private void causePanic() {
        throw new RuntimeException("BAD descriptor: " + this.raw);
    }

    private int readUint8() {
        int b = raw.charAt(offset);
        this.offset++;
        return b;
    }

    private void unreadUint8() {
        this.offset--;
    }

    private void parseParamTypes() {
        while (true) {
            String t = parseFieldType();
            if (!t.equals("")) {
                this.parsed.addParameterType(t);
            } else {
                break;
            }
        }
    }

    private void parseReturnType() {
        if (this.readUint8() == (int)'V') {
            this.parsed.setReturnType("v");
            return;
        }

        this.unreadUint8();
        String t = parseFieldType();
        if (!t.equals("")) {
            this.parsed.setReturnType(t);
            return;
        }

        this.causePanic();
    }

    private String parseFieldType() {
        switch (this.readUint8()) {
            case 'B':
                return "B";
            case 'C':
                return "C";
            case 'D':
                return "D";
            case 'F':
                return "F";
            case 'I':
                return "I";
            case 'J':
                return "J";
            case 'S':
                return "S";
            case 'Z':
                return "Z";
            case 'L':
                return this.parseObjectType();
            case '[':
                return this.parseArrayType();
            default:
                this.unreadUint8();
                return "";
        }
    }

    private String parseObjectType() {
        String unread = raw.substring(this.offset, raw.length() - 1);
        int semicolonIndex = unread.indexOf(';', offset);
        if (semicolonIndex == -1) {
            this.causePanic();
            return "";
        } else {
            int objStart = offset - 1;
            int objEnd = offset + semicolonIndex + 1;
            this.offset = objEnd;
            return raw.substring(objStart, objEnd);
        }
    }

    private String parseArrayType() {
        int arrStart = offset - 1;
        this.parseFieldType();
        int arrEnd = offset;
        return raw.substring(arrStart, arrEnd);
    }
}
