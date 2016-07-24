/**     
 * @Title:  Caculate.java   
 * @Package circle   
 * @Description:    TODO  
 * @author: Android_Robot     
 * @date:   2016年3月10日 下午12:38:40   
 * @version V1.0     
 */
package circle;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bc
 *
 */
public class Caculate {
	double range = 0.000001;

	// 浮点数判同
	boolean double_equals(double a, double b) {
		return Math.abs(a - b) < range;
	}

	// 两点之间距离的平方
	double distance_sqr(Point a, Point b) {
		return (a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y);
	}

	// 两点之间的距离
	public double distance(Point a, Point b) {
		return Math.sqrt(distance_sqr(a, b));
	}

	double sqrt(double d) {
		return Math.sqrt(d);
	}

	// 求两圆相交交点
	List<Point> intersect(Circle[] circles) {
		List<Point> points = new ArrayList<Point>();
		Point p0, p1;
		double d, a, b, c, p, q, r;
		double cos_value[] = new double[2], sin_value[] = new double[2];
		if (double_equals(circles[0].center.x, circles[1].center.x)
				&& double_equals(circles[0].center.y, circles[1].center.y)
				&& double_equals(circles[0].r, circles[1].r)) {
			return null;
		}
		// 判断2圆不相交
		d = distance(circles[0].center, circles[1].center);
		if (d > circles[0].r + circles[1].r || d < circles[0].r - circles[1].r) {
			return null;
		}
		a = 2.0 * circles[0].r * (circles[0].center.x - circles[1].center.x);
		b = 2.0 * circles[0].r * (circles[0].center.y - circles[1].center.y);
		c = circles[1].r * circles[1].r - circles[0].r * circles[0].r
				- distance_sqr(circles[0].center, circles[1].center);
		p = a * a + b * b;
		q = -2.0 * a * c;
		if (double_equals(d, circles[0].r + circles[1].r)
				|| double_equals(d, circles[0].r - circles[1].r)) {
			cos_value[0] = -q / p / 2.0;
			sin_value[0] = sqrt(1 - cos_value[0] * cos_value[0]);
			p0 = new Point();
			p0.x = circles[0].r * cos_value[0] + circles[0].center.x;
			p0.y = circles[0].r * sin_value[0] + circles[0].center.y;
			points.add(p0);
			if (!double_equals(distance_sqr(p0, circles[1].center),
					circles[1].r * circles[1].r)) {
				p0.y = circles[0].center.y - circles[0].r * sin_value[0];
			}
			return points;
		}

		r = c * c - b * b;
		cos_value[0] = (sqrt(q * q - 4.0 * p * r) - q) / p / 2.0;
		cos_value[1] = (-sqrt(q * q - 4.0 * p * r) - q) / p / 2.0;
		sin_value[0] = sqrt(1 - cos_value[0] * cos_value[0]);
		sin_value[1] = sqrt(1 - cos_value[1] * cos_value[1]);

		p0 = new Point();
		p1 = new Point();
		p0.x = circles[0].r * cos_value[0] + circles[0].center.x;
		p1.x = circles[0].r * cos_value[1] + circles[0].center.x;
		p0.y = circles[0].r * sin_value[0] + circles[0].center.y;
		p1.y = circles[0].r * sin_value[1] + circles[0].center.y;

		if (!double_equals(distance_sqr(p0, circles[1].center), circles[1].r
				* circles[1].r)) {
			p0.y = circles[0].center.y - circles[0].r * sin_value[0];
		}
		if (!double_equals(distance_sqr(p0, circles[1].center), circles[1].r
				* circles[1].r)) {
			p0.x = circles[0].center.x-circles[0].r * cos_value[0]  ;
		}
		if (!double_equals(distance_sqr(p1, circles[1].center), circles[1].r
				* circles[1].r)) {
			p1.y = circles[0].center.y - circles[0].r * sin_value[1];
		}
		if (!double_equals(distance_sqr(p1, circles[1].center), circles[1].r
				* circles[1].r)) {
			p1.x = circles[0].center.x-circles[0].r * cos_value[1] ;
		}
		if (double_equals(p0.y, p1.y) && double_equals(p0.x, p1.x)) {
			if (p0.y > 0) {
				p1.y = -p1.y;
			} else {
				p0.y = -p0.y;
			}
		}
		points = new ArrayList<Point>();
		points.add(p0);
		points.add(p1);
		return points;
	}

	public Point getSddwPoint(Circle[] circles) {
		if(circles[0]==null){return null;}
		List<Point> list = intersect(circles);
		System.out.println(list);
		double d = 0;
		if(list==null||list.isEmpty()){return null;}
		for (Point p : list) {
//			System.out.println("distance:" + distance(p, circles[2].center));
//			System.out.println("第三圆半径:" + circles[2].r);
//			System.out.println(Math.abs(distance(p, circles[2].center)
//					- circles[2].r));
			if (Math.abs(distance(p, circles[2].center) - circles[2].r) < 0.0001) {
				return p;
			}
		}
		return null;
	}

	public Circle[] setSddwPoint(Point p, Circle[] cricles) {
		for (Circle c : cricles) {
			c.r = distance(p, c.center);
		}
		return cricles;
	}

	public static void main2(String[] args) {
		Circle c1 = new Circle(), c2 = new Circle(), c3 = new Circle();
		Point p1 = new Point(), p2 = new Point(), p3 = new Point();
		p1.x = 0;
		p1.y = 0;
		p2.x = 25;
		p2.y = 0;
		c1.center = p1;
		c1.r = 7.071067;
		c2.center = p2;
		c2.r = 20.615528;
		p3.x = 9;
		p3.y = 12;
		c3.center = p3;
		c3.r = 6.403124;
		Circle[] circles = new Circle[] { c1, c2, c3 };
		Caculate c = new Caculate();
		List<Point> list = c.intersect(circles);
		// System.out.println(list);
		System.out.println(c.getSddwPoint(circles));
		// Point p4 = new Point();
		// p4.x = 9;
		// p4.y = 13;
		// circles=c.setSddwPoint(p4,circles);
		// for(Circle c5:circles){
		// System.out.println(c5);
		// }
	}

	public static void main(String[] args) {
		Caculate c = new Caculate();
		Point p1 = new Point(120.357951, 30.310544), p2 = new Point(120.357973,
				30.318639), p3 = new Point(120.348424, 30.318602);
		Point p = new Point(120.3524061, 30.3115322);
		double r1 = c.distance(p, p1);
		double r2 = c.distance(p, p2);
		double r3 = c.distance(p, p3);
		System.out.println(r1);
		System.out.println(r2);
		System.out.println(r3);
		Circle c1 = new Circle(p1, r1), c2 = new Circle(p2, r2), c3 = new Circle(
				p3, r3);
		Circle[] circles = new Circle[] { c1, c2, c3 };
		System.out.println(c.getSddwPoint(circles));
	}
}
