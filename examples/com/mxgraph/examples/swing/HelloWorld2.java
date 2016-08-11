package com.mxgraph.examples.swing;

import javax.swing.JFrame;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxGraph;
import com.mxgraph.view.mxStylesheet;

/**
 * mxGraph基本用法，insertVertex, style设置，限制编辑Label，不产生悬挂边，不能改变cell大小等等
 */
public class HelloWorld2 extends JFrame
{
	private static final long serialVersionUID = -7304457221034788891L;

	public HelloWorld2()
	{
		super("Hello, World!");

		mxGraph graph = new mxGraph() {
			// 使用以下各个graph.setCells代替
//			// Overrides method to disallow label editing,限制编辑顶点和边的label
//			public boolean isCellEditable(Object cell) {
//				// return !getModel().isEdge(cell); // disallow edge label editing, 但是边的Label位置可以拖动
//				return false; //disallow label editing, 但是边的Label位置可以拖动
//			}
		};
		
		graph.setCellsDeletable(false);
		graph.setCellsEditable(false);
		graph.setCellsDisconnectable(false);  // 不会拖动边，而使两端顶点不连接
		graph.setCellsMovable(true);
		//graph.setCellsLocked(true);
		graph.setCellsResizable(false);
		graph.setVertexLabelsMovable(true);
		graph.setEdgeLabelsMovable(true);
		
		graph.setAllowDanglingEdges(false); // 设置true: 鼠标掠过，按住左键，可以拖出一条悬挂边;
		
		Object parent = graph.getDefaultParent();

		graph.getModel().beginUpdate();
		try
		{
			Object v1 = graph.insertVertex(parent, null, "Hello", 20, 20, 80,30);
			Object v2 = graph.insertVertex(parent, null, "World!", 240, 150,80, 30);
			// 可以这样指定形状或style
			//object v1 = graph.insertVertex(parent, null, "", 20,20, 80, 30, mxConstants.STYLE_SHAPE + "=" + mxConstants.SHAPE_TOKEN_ELLIPSE);
			//Object v2 = graph.insertVertex(parent, null, "World!", 200, 150,80, 60, "shape=ellipse;perimeter=ellipsePerimeter");
			//Object v1 = graph.insertVertex(parent, null, "Hello", 20, 20, 80, 30,"shape=and;fillColor=#ff0000;gradientColor=#ffffff;shadow=1");
			graph.insertEdge(parent, null, "Edge", v1, v2);
			
			// 修改style
			Object[] cells = new Object[2];
			cells[0] = v1;
			cells[1] = v2;
			/** label的水平方向显示位置 */
			//graph.setCellStyles(mxConstants.STYLE_LABEL_POSITION, mxConstants.ALIGN_RIGHT,cells);
			
			/** label的垂直方向显示位置 */
			graph.setCellStyles(mxConstants.STYLE_VERTICAL_LABEL_POSITION, mxConstants.ALIGN_TOP,cells);
			
			/** mxConstants.STYLE_SHAPE指定形状shape,mxConstants.SHAPE_*是具体形状    */
			//graph.setCellStyles(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_TRIANGLE,cells);
			//graph.setCellStyles(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_ACTOR,cells);
			//graph.setCellStyles(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_HEXAGON,cells);
			//graph.setCellStyles(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_LABEL,cells);
			//graph.setCellStyles(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_SWIMLANE,cells);
			//graph.setCellStyles(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_DOUBLE_ELLIPSE,cells);
			graph.setCellStyles(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_CYLINDER,cells);
	
		}
		finally
		{
			graph.getModel().endUpdate();
		}

		mxGraphComponent graphComponent = new mxGraphComponent(graph);
		getContentPane().add(graphComponent);
	}

	public static void main(String[] args)
	{
		HelloWorld2 frame = new HelloWorld2();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 320);
		frame.setVisible(true);
		
		// 缺省Style
		mxStylesheet style = new mxStylesheet();
		
		System.out.println(style.getDefaultVertexStyle()); 
		
		System.out.println(style.getDefaultEdgeStyle());
		
		// defaultEdge={endArrow=classic, shape=connector, fontColor=#446299, strokeColor=#6482B9, align=center, verticalAlign=middle}, 
		// defaultVertex={shape=rectangle, fontColor=#774400, strokeColor=#6482B9, fillColor=#C3D9FF, align=center, verticalAlign=middle}

		System.out.println(style.getStyles());
	}

}
