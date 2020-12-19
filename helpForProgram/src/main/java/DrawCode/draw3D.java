package draw;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;

/**
 * 
 * @author 路灯下的美男子
 *
 */

public class DrawObjects {
	private Graphics2D gp2d;
	public DrawObjects(Graphics2D g) {	
		BasicStroke bs = new BasicStroke( 10.1f,BasicStroke.CAP_ROUND,BasicStroke.JOIN_BEVEL);
		this.gp2d = g;
		
	}
	public DrawObjects(boolean b,Graphics2D g) {
		this.gp2d = g;
		if(b)
			gp2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);//抗锯齿
	}
	public void drawDisk(int x,int y,int width,int height,int thickness,Color bottom,Color top,Color body) {
		//x,y为圆盘圆心坐标
		//坐标变换
		x = x-width;
		y = y-height;
		//画盘子方法				
		gp2d.setColor(bottom);//先画底部
		gp2d.fillOval(x,y,width,height);		
		gp2d.setColor(body);//画主干
		for(int j = 1;j < thickness;j++) {
			gp2d.fillOval(x,y--,width,height);
		}
		gp2d.setColor(top);//封顶
		gp2d.fillOval(x,y,width,height);			
	
	}
	public void drawBox(int x,int y,int width,int height,int move,int thickness,Color bottom,Color top,Color body) {
		//x,y为平行四边形中心坐标
		//坐标转换
		x = x-width/2;
		y = y-height/2;
		//画盒子方法				
		gp2d.setColor(bottom);//先画底部
		this.drawParallelogram(x, y--, height, width, move);		
		gp2d.setColor(body);//画主干
		for(int j = 1;j < thickness;j++) {
			this.drawParallelogram(x, y--, height, width, move);
		}
		gp2d.setColor(top);//封顶
		this.drawParallelogram(x, y, height, width, move);
	}
	public void drawParallelogram(int x,int y,int height,int width,int move) {//画平行四边形
		//x,y左上顶点坐标
		//move<0向左移动 ，>0向右移动
		// 绘制平行四边形点
		int[] px = new int[4];
		int[] py = new int[4];
		px[3] = x;px[2] = x+width;px[1] = x + width+move;px[0] = x+move;
		py[3] = y;py[2] = y;py[1] = y+height;py[0] = y+height;
		gp2d.fillPolygon(px,py, 4);	
	}
	public static void main(String[] args) {
		
	}
	
}
