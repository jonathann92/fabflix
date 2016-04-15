package objects;

public class MutableInt {

	  int value = 0; // note that we start at 1 since we're counting
	  public void increment () { ++value;      }
	  public int  get ()       { return value; }
	  public void set(int n) { value = n; }
		
}
