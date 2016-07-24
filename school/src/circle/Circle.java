/**     
 * @Title:  Circle.java   
 * @Package circle   
 * @Description:    TODO  
 * @author: Android_Robot     
 * @date:   2016年3月10日 下午12:37:52   
 * @version V1.0     
 */
package circle;

/**
 * @author bc
 *
 */
public class Circle {
	public Point center;
	public double r=0;
	@Override
	public String toString() {
		return "Circle [center=" + center + ", r=" + r + "]";
	}
	
	
	public Circle() {
		super();
	}


	public Circle(double x,double y, double r) {
		this.center = new Point(x,y);
		this.r = r;
	}


	public Circle(Point center, double r) {
		super();
		this.center = center;
		this.r = r;
	}
	
	
}
