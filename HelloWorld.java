package example;

import java.util.ArrayList;
import java.util.List;

abstract class Example0
{
	
	
}
 //Test comment 0
public class Example extends Example0  {
	 //Test comment 1
  private ArrayList<String> names;

  public Example() {
	  //Test comment 2
    names = new ArrayList<>();
  }

  public void addName(String name) {
	  //Test comment 3
    names.add(name);
  }

  public List<String> getNames() {
	  //Test comment 4
    return new ArrayList<>(names); //another inline comment
  }
}
