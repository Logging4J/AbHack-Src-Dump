//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.asm.mixin.injection.invoke.util;

import org.spongepowered.asm.mixin.injection.struct.*;
import org.spongepowered.asm.lib.tree.*;
import org.apache.logging.log4j.*;
import org.spongepowered.asm.lib.tree.analysis.*;

public class InsnFinder
{
    private static final Logger logger;
    
    public AbstractInsnNode findPopInsn(final Target target, final AbstractInsnNode abstractInsnNode) {
        try {
            new PopAnalyzer(abstractInsnNode).analyze(target.classNode.name, target.method);
        }
        catch (AnalyzerException ex) {
            if (ex.getCause() instanceof AnalysisResultException) {
                return ((AnalysisResultException)ex.getCause()).getResult();
            }
            InsnFinder.logger.catching((Throwable)ex);
        }
        return null;
    }
    
    static {
        logger = LogManager.getLogger("mixin");
    }
    
    static class AnalysisResultException extends RuntimeException
    {
        private static final long serialVersionUID = 1L;
        private AbstractInsnNode result;
        
        public AnalysisResultException(final AbstractInsnNode result) {
            this.result = result;
        }
        
        public AbstractInsnNode getResult() {
            return this.result;
        }
    }
    
    enum AnalyzerState
    {
        SEARCH, 
        ANALYSE, 
        COMPLETE;
    }
    
    static class PopAnalyzer extends Analyzer<BasicValue>
    {
        protected final AbstractInsnNode node;
        
        public PopAnalyzer(final AbstractInsnNode node) {
            super((Interpreter)new BasicInterpreter());
            this.node = node;
        }
        
        protected Frame<BasicValue> newFrame(final int n, final int n2) {
            return new PopFrame(n, n2);
        }
        
        class PopFrame extends Frame<BasicValue>
        {
            private AbstractInsnNode current;
            private AnalyzerState state;
            private int depth;
            
            public PopFrame(final int n, final int n2) {
                super(n, n2);
                this.state = AnalyzerState.SEARCH;
                this.depth = 0;
            }
            
            public void execute(final AbstractInsnNode current, final Interpreter<BasicValue> interpreter) throws AnalyzerException {
                super.execute(this.current = current, (Interpreter)interpreter);
            }
            
            public void push(final BasicValue basicValue) throws IndexOutOfBoundsException {
                if (this.current == PopAnalyzer.this.node && this.state == AnalyzerState.SEARCH) {
                    this.state = AnalyzerState.ANALYSE;
                    ++this.depth;
                }
                else if (this.state == AnalyzerState.ANALYSE) {
                    ++this.depth;
                }
                super.push((Value)basicValue);
            }
            
            public BasicValue pop() throws IndexOutOfBoundsException {
                if (this.state == AnalyzerState.ANALYSE && --this.depth == 0) {
                    this.state = AnalyzerState.COMPLETE;
                    throw new AnalysisResultException(this.current);
                }
                return (BasicValue)super.pop();
            }
        }
    }
}
