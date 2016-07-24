/**     
 * @Title:  Snippet.java   
 * @Package circle   
 * @Description:    TODO  
 * @author: Android_Robot     
 * @date:   2016年3月10日 下午12:37:03   
 * @version V1.0     
 */  
package circle;

/**
 * @author bc
 *
 */
public class Point {
	 public double x=0;
	 public double y=0;

	@Override
	public String toString() {
		return "Point [x=" + x + ", y=" + y + "]";
	}

	public Point(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}

	public Point() {
		super();
	}
	
	
}

