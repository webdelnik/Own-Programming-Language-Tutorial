package com.annimon.ownlang.parser.ast;

import com.annimon.ownlang.lib.MapValue;
import com.annimon.ownlang.lib.Value;
import java.util.Map;

/**
 *
 * @author aNNiMON
 */
public final class MapExpression implements Expression {
    
    public final Map<Expression, Expression> elements;

    public MapExpression(Map<Expression, Expression> arguments) {
        this.elements = arguments;
    }
    
    @Override
    public Value eval() {
        final int size = elements.size();
        final MapValue map = new MapValue(size);
        for (Expression key : elements.keySet()) {
            map.set(key.eval(), elements.get(key).eval());
        }
        return map;
    }
    
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return elements.toString();
    }
}
