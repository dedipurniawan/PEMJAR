package TB;

import java.io.*;

public class Struct implements Serializable {
	 private int i = 0;
	 private float f = 0.0F;
	 public Struct(int i, float f) {
	    this.i = i;
	    this.f = f;
	 }
  
	 public int getInt() {
		 return i;
	 }
  
	 public float getFloat() {
		 return f;
	 }
  
	 public void setInt(int i) {
		 this.i = i;
	 }
  
	 public void setFloat(float f) {
		 this.f = f;
	 }
}