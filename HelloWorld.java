package net.code.java;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.ChildListPropertyDescriptor;
import org.eclipse.jdt.core.dom.ChildPropertyDescriptor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.InfixExpression;
import org.eclipse.jdt.core.dom.Javadoc;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.SimplePropertyDescriptor;
import org.eclipse.jdt.core.dom.SwitchStatement;
import org.eclipse.jdt.core.dom.TagElement;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

public class HelloWorld {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			System.out.println("holi");
			
			
			ASTParser parser = ASTParser.newParser(AST.JLS10);
			parser.setSource("package example;\r\n import java.util.ArrayList;\r\n import java.util.List;\r\n public class Example {\r\n   public Example() {\r\n  names = new ArrayList<>();\r\n  }\r\n \r\n  public void addName(String name) {\r\n  names.add(name);\r\n  }\r\n \r\n  public List<String> getNames() {\r\n  return new ArrayList<>(names);\r\n   }\r\n }\r\n ".toCharArray());
			//parser.setSource("/*abc*/".toCharArray());
			parser.setKind(ASTParser.K_COMPILATION_UNIT);
			ASTNode node = parser.createAST(null);
			
			print(node);
	 
			//final CompilationUnit cu = (CompilationUnit) parser.createAST(null);
			
	 /* esto es un comentario
	   bastante fenomenal
	  */
			
		
	}
	
	static private void print(ASTNode node) {
	    List properties = node.structuralPropertiesForType();
	    for (Iterator iterator = properties.iterator(); iterator.hasNext();) {
	        Object descriptor = iterator.next();
	        if (descriptor instanceof SimplePropertyDescriptor) {
	            SimplePropertyDescriptor simple = (SimplePropertyDescriptor) descriptor;
	            Object value = node.getStructuralProperty(simple);
	            System.out.println(simple.getId()  + " (" + value.toString() + ") Operands: " + "Type: " + node.nodeClassForType(node.getNodeType()));
	        } else if (descriptor instanceof ChildPropertyDescriptor) {
	            ChildPropertyDescriptor child = (ChildPropertyDescriptor) descriptor;
	            ASTNode childNode = (ASTNode) node.getStructuralProperty(child);
	            if (childNode != null) {
	                System.out.println("Child (" + child.getId() + ") Type: " + node.nodeClassForType(node.getNodeType()) + "{");
	                print(childNode);
	                System.out.println("}");
	            }
	        } else {
	            ChildListPropertyDescriptor list = (ChildListPropertyDescriptor) descriptor;
	            System.out.println("List (" + list.getId() + "){");
	            print((List) node.getStructuralProperty(list));
	            System.out.println("}");
	        }
	    }
	}

	static private void print(List nodes) {
	    for (Iterator iterator = nodes.iterator(); iterator.hasNext();) {
	        print((ASTNode) iterator.next());
	    }
	}

}
