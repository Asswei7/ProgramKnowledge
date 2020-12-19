package com.naming.test;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.visitor.VoidVisitor;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Asswei
 */
public class Application {
    public static final String FILE_PATH = "F:\\java\\IDEA\\WorkSpace\\Student\\src\\Student.java";
    public static final int PACKAGE_TYPE = 1;
    public static final int CLASS_TYPE = 2;
    public static final int VAR_TYPE = 3;


    public static void main(String[] args) throws FileNotFoundException {
        CompilationUnit cu = StaticJavaParser.parse(new File(FILE_PATH));
        NamingTest namingTest = new NamingTest();
        Advice advice = new Advice();
        //有用的是methodInfo，而不是methodInfoMap
        Map<String, Integer> methodInfo = new HashMap<>() ;
        VoidVisitor<Map<String, Integer>> methodInfoMap = new GetName.MethodInfoMap();
        methodInfoMap.visit(cu, methodInfo);

        Map<String, Integer> varInfo = new HashMap<>() ;
        VoidVisitor<Map<String, Integer>> varInfoMap = new GetName.VariableInfoMap();
        varInfoMap.visit(cu, varInfo);

        Map<String, Integer> packageInfo = new HashMap<>() ;
        VoidVisitor<Map<String, Integer>> packageInfoMap = new GetName.PackageInfoMap();
        packageInfoMap.visit(cu, packageInfo);

        Map<String, Integer> classInfo = new HashMap<>() ;
        VoidVisitor<Map<String, Integer>> classInfoMap = new GetName.ClassInfoMap();
        classInfoMap.visit(cu, classInfo);

        advice.printAdvice(packageInfo,namingTest,advice,PACKAGE_TYPE);
        advice.printAdvice(classInfo,namingTest,advice,CLASS_TYPE);
        advice.printAdvice(methodInfo,namingTest,advice,VAR_TYPE);
        advice.printAdvice(varInfo,namingTest,advice,VAR_TYPE);
    }
}
