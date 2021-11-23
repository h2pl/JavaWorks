package com.javaworks.processor;

import com.javaworks.constants.CommonConstants;
import com.sun.source.tree.Tree;
import com.sun.tools.javac.api.JavacTrees;
import com.sun.tools.javac.code.Flags;
import com.sun.tools.javac.processing.JavacProcessingEnvironment;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.tree.TreeTranslator;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.ListBuffer;
import com.sun.tools.javac.util.Name;
import com.sun.tools.javac.util.Names;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.util.Collections;
import java.util.Set;

/**
 * @Author: hpl
 * @Date: 2021/11/19 14:17
 */
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class InstanceAnnotationProcessor extends AbstractProcessor {

    private JavacTrees javacTrees;
    private TreeMaker treeMaker;
    private Names names;

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Set<? extends Element> elementsAnnotatedWith = roundEnv.getElementsAnnotatedWith(GenerateInstance.class);
        elementsAnnotatedWith.forEach(element -> javacTrees.getTree(element).accept(new ElementTreeVisitor()));
        return true;
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return Collections.singleton(GenerateInstance.class.getName());
    }

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        this.javacTrees = JavacTrees.instance(processingEnv);
        Context context = ((JavacProcessingEnvironment) processingEnv).getContext();
        this.treeMaker = TreeMaker.instance(context);
        this.names = Names.instance(context);
    }

    private class ElementTreeVisitor extends TreeTranslator {
        private final static String FILED_NAME = CommonConstants.DEFAULT_INSTANCE_NAME;
        private final static String METHOD_NAME = CommonConstants.GET_INSTANCE_METHOD_NAME;
        private final Name fieldName;
        private final Name methodName;

        private ElementTreeVisitor() {
            this.fieldName = names.fromString(FILED_NAME);
            this.methodName = names.fromString(METHOD_NAME);
        }

        @Override
        public void visitClassDef(JCTree.JCClassDecl tree) {
            preCheck(tree);
            tree.defs = tree.defs.prepend(createField(tree)).prepend(createMethod(tree));
            System.out.println(tree.defs);
        }

        private void preCheck(JCTree.JCClassDecl tree) {
            for (JCTree node : tree.defs) {
                if (Tree.Kind.METHOD == node.getKind()) {
                    JCTree.JCMethodDecl methodDecl = (JCTree.JCMethodDecl) node;
                    if (!METHOD_NAME.equals(methodDecl.name.toString())) continue;
                    if (methodDecl.params.size() != 0) continue;
                    throw new RuntimeException("Class[" + tree.getSimpleName() + "] exist method[" + METHOD_NAME + "], conflicting to compiler created");
                }
            }
        }

        private JCTree.JCVariableDecl createField(JCTree.JCClassDecl jcClassDecl) {
            return treeMaker.VarDef(
                    treeMaker.Modifiers(Flags.PRIVATE | Flags.STATIC | Flags.FINAL),
                    fieldName,
                    treeMaker.Ident(jcClassDecl.name),
                    treeMaker.NewClass(null, com.sun.tools.javac.util.List.nil(), treeMaker.Ident(jcClassDecl.name), com.sun.tools.javac.util.List.nil(), null)
            );
        }

        private JCTree.JCMethodDecl createMethod(JCTree.JCClassDecl jcClassDecl) {
            ListBuffer<JCTree.JCStatement> statements = new ListBuffer<>();
            statements.append(treeMaker.Return(treeMaker.Ident(fieldName)));
            JCTree.JCBlock body = treeMaker.Block(0, statements.toList());
            return treeMaker.MethodDef(treeMaker.Modifiers(Flags.PUBLIC | Flags.STATIC), methodName, treeMaker.Ident(jcClassDecl.name), com.sun.tools.javac.util.List.nil(), com.sun.tools.javac.util.List.nil(), com.sun.tools.javac.util.List.nil(), body, null);
        }
    }
}
