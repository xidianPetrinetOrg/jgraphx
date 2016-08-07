package com.mxgraph.shape;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import com.mxgraph.canvas.mxGraphics2DCanvas;
import com.mxgraph.model.mxCell;
import com.mxgraph.view.mxCellState;

/**
 * shape: 椭圆中心显示token的数量（大于6）或显示相应数量的小黑点。<br>
 * mxGraph graph = new mxGraph();<br>
 * mxTokenToShape token = new mxTokenToShape("label",5)<br>
 * object v1 = graph.insertVertex(parent, null, token, 20,20, 40, 40, mxConstants.STYLE_SHAPE + "=" + mxConstants.SHAPE_TOKEN_ELLIPSE);
 * @see mxTokenToShape
 * @author Jtduan
 *
 */
public class mxTokenEllipseShape extends mxBasicShape
{	
	/**
	 * 重写了基类的方法，刷出本类的形状
	 */
	public void paintShape(mxGraphics2DCanvas canvas, mxCellState state) {
		Rectangle rect = state.getRectangle();

		Shape ellipse = new Ellipse2D.Double(rect.x, rect.y, rect.width, rect.height);

		// Paints the background
		if (configureGraphics(canvas, state, true)) {
			canvas.fillShape(ellipse, hasShadow(canvas, state));
		}

		// Paints the foreground
		if (configureGraphics(canvas, state, false)) {
			fillToken(canvas,state);
			canvas.getGraphics().draw(ellipse);
		}

	}
	
	private void fillToken(mxGraphics2DCanvas canvas, mxCellState state) {
		// cell的矩型边界
		Rectangle rect = state.getRectangle();
		double x0,y0,w,h,xc,yc,x,y;
		x0 = rect.x;     y0 = rect.y;
		w = rect.width;  h = rect.height;
		// 中心
		xc = x0 + w/2.0;
		yc = y0 + h/2.0;
		//System.out.println(x0+","+ y0+","+xc+","+yc+","+w+","+h);
		
		// 获取token number
		int tokenNumber = 0;
		mxCell cell = (mxCell) state.getCell(); 	
		Object value = cell.getValue();
		if (value instanceof mxTokenToShape) {
			tokenNumber = ((mxTokenToShape) value).getTokenNum();
		}
		
		//System.out.println(tokenNumber);
		
		Graphics2D g = canvas.getGraphics();
		// 坐标原点移到中心
		g.translate(xc, yc);
		
		// Saves the state and configures the graphics object
		Paint p = g.getPaint();
		Color previousColor = g.getColor();
		g.setColor(Color.black);

		// Fills the token
		Shape token;
		switch(tokenNumber) {
		case 0: break;
		case 1:
			x = 0; y = 0;
			token = new Ellipse2D.Double(x, y, 4, 4);
			g.fill(token);
			break;
		case 2:
			x = -4; y = 0;
			token = new Ellipse2D.Double(x, y, 4, 4);
			g.fill(token);
			x = 4; y = 0;
			token = new Ellipse2D.Double(x, y, 4, 4);
			g.fill(token);
			break;
		case 3:
			x = 0; y = -4;
			token = new Ellipse2D.Double(x, y, 4, 4);
			g.fill(token);
			x = -4; y = 4;
			token = new Ellipse2D.Double(x, y, 4, 4);
			g.fill(token);
			x = 4; y = 4;
			token = new Ellipse2D.Double(x, y, 4, 4);
			g.fill(token);
			break;
		case 5:
			x = 0; y = 0;
			token = new Ellipse2D.Double(x, y, 4, 4);
			g.fill(token);
		case 4:
			x = -4; y = -4;
			token = new Ellipse2D.Double(x, y, 4, 4);
			g.fill(token);
			x = 4; y = -4;
			token = new Ellipse2D.Double(x, y, 4, 4);
			g.fill(token);
			x = -4; y = 4;
			token = new Ellipse2D.Double(x, y, 4, 4);
			g.fill(token);
			x = 4; y = 4;
			token = new Ellipse2D.Double(x, y, 4, 4);
			g.fill(token);
			break;
		default:
			// 直接写出token number
			g.drawString(tokenNumber + "", -4, 0);
			break;
		}
		
		// Restores the state of the graphics object
		g.setColor(previousColor);
		g.setPaint(p);
		// 恢复坐标原点
		g.translate(-xc, -yc);
	}
	
}
