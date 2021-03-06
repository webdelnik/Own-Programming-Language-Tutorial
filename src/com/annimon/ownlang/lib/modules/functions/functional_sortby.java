package com.annimon.ownlang.lib.modules.functions;

import com.annimon.ownlang.exceptions.TypeException;
import com.annimon.ownlang.lib.*;
import java.util.Arrays;

public final class functional_sortby implements Function {

    @Override
    public Value execute(Value... args) {
        Arguments.check(2, args.length);
        if (args[0].type() != Types.ARRAY) {
            throw new TypeException("Array expected in first argument");
        }
        if (args[1].type() != Types.FUNCTION) {
            throw new TypeException("Function expected in second argument");
        }
        
        final Value[] elements = ((ArrayValue) args[0]).getCopyElements();
        final Function function = ((FunctionValue) args[1]).getValue();
        Arrays.sort(elements, (o1, o2) -> function.execute(o1).compareTo(function.execute(o2)));
        return new ArrayValue(elements);
    }
    
}