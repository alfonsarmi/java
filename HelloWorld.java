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


interface Drawable{  
    public void draw();  
}  

interface Addable{  
    int add(int a,int b);  
}  

public class HelloWorld {
	int count = 0;
	
	public static void test(String[] args) {  
        int width=10;  
  
        //without lambda, Drawable implementation using anonymous class  
        Drawable d=new Drawable(){  
            public void draw(){System.out.println("Drawing "+width);}  
        };  
        d.draw();  
    }  
	
	 public static void test2(String[] args) {  
	        int width=10;  
	          
	        //with lambda  
	        Drawable d2=()->{  
	            System.out.println("Drawing "+width);  
	        };  
	        d2.draw();  
	    }  
	 
	 public static void test3(String[] args) {  
         
	        // Multiple parameters in lambda expression  
	        Addable ad1=(a,b)->(a+b);  
	        System.out.println(ad1.add(10,20));  
	          
	        // Multiple parameters with data type in lambda expression  
	        Addable ad2=(int a,int b)->(a+b);  
	        System.out.println(ad2.add(100,200));  
	    }  
	 
	 public static void test4(String[] args) {  
         
	        List<String> list=new ArrayList<String>();  
	        list.add("ankit");  
	        list.add("mayank");  
	        list.add("irfan");  
	        list.add("jai");  
	          
	        list.forEach(  
	            (n)->System.out.println(n)  
	        );  
	    }  
	 
	 public static void test5(String[ ] args) {
		    try {
		      int[] myNumbers = {1, 2, 3};
		      System.out.println(myNumbers[10]);
		    } catch (Exception e) {
		      System.out.println("Something went wrong.");
		    }
		  }
	 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int testIf = 2;
		if (testIf == returntwo())
		{
			
			System.out.println("Este es el if");
		}
		else
		{
			System.out.println("Este es el else");			
		}
		
		if (testIf == returntwo())
		{
			
			System.out.println("Este es el if 2");
		}
		else if (testIf == 3)
		{
			System.out.println("Este es el elseif");			
		}
		
		while(testIf < 5)
		{
			System.out.println("Este es el while");
			testIf++;
		}
		
		do
		{
			System.out.println("Este es el do while");
			testIf++;
			
			if (testIf == 1)
			{
				break;
			}
			else
			{
				continue;
			}			
		}
		while (testIf < 50);
		
		int i = (int) (Math.random() * 5.0);
        switch (i) {
	        case 0:
	            System.out.println("Zero");
	            break;
	
	        case 1:
	            System.out.println("One");
	            break;
	
	        case 2:
	        case 3:
	            System.out.println("Two or Three");
	            break;
	
	        default:
	            System.out.println("Other");
	            break;
            
        }
        
        
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
	
	public void syncadd(int value){

	    synchronized(this){
	       this.count += value;   
	    }
	  }
	
	 public synchronized void add(int value){
	      this.count += value;
	  }
	
	static private int givemeatwo()
	{
		return 2;		
	}
	
	static private int returntwo()
	{
		return givemeatwo();		
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
