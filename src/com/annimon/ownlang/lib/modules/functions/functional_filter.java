package com.annimon.ownlang.lib.modules.functions;

import com.annimon.ownlang.exceptions.TypeException;
import com.annimon.ownlang.lib.*;
import java.util.ArrayList;
import java.util.List;

import java.util.Map;

public final class functional_filter implements Function {

    @Override
    public Value execute(Value... args) {
        Arguments.check(2, args.length);
        if (args[1].type() != Types.FUNCTION) {
            throw new TypeException("Function expected in second argument");
        }

        final Value container = args[0];
        final Function consumer = ((FunctionValue) args[1]).getValue();
        if (container.type() == Types.ARRAY) {
            return filterArray((ArrayValue) container, consumer);
        }
        
        if (container.type() == Types.MAP) {
            return filterMap((MapValue) container, consumer);
        }

        throw new TypeException("Invalid first argument. Array or map expected");
    }
    
    private Value filterArray(ArrayValue array, Function predicate) {
        final int size = array.size();
        final List<Value> values = new ArrayList<Value>(size);
        for (Value value : array) {
            if (predicate.execute(value) != NumberValue.ZERO) {
                values.add(value);
            }
        }
        final int newSize = values.size();
        return new ArrayValue(values.toArray(new Value[newSize]));
    }
    
    private Value filterMap(MapValue map, Function predicate) {
        final MapValue result = new MapValue(map.size());
        for (Map.Entry<Value, Value> element : map) {
            if (predicate.execute(element.getKey(), element.getValue()) != NumberValue.ZERO) {
                result.set(element.getKey(), element.getValue());
            }
        }
        return result;
    }
}