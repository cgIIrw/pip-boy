package rtda.methodarea.countparams_utils;

import java.util.ArrayList;

public class MethodDescriptor {
    private ArrayList<String> parameterTypes = new ArrayList<>();
    private String returnType;


    public void addParameterType(String t) {
       parameterTypes.add(t);
    }

    public ArrayList<String> getParameterTypes() {
        return parameterTypes;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }
}
