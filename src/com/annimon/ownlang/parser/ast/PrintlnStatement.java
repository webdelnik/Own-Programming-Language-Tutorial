package com.annimon.ownlang.parser.ast;

import com.annimon.ownlang.Console;

/**
 *
 * @author aNNiMON
 */
public final class PrintlnStatement implements Statement {
    
    public final Expression expression;

    public PrintlnStatement(Expression expression) {
        this.expression = expression;
    }

    @Override
    public void execute() {
        Console.println(expression.eval());
    }
    
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return "println " + expression;
    }
}
