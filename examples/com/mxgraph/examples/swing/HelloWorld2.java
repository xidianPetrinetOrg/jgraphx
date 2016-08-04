package com.mxgraph.examples.swing;

import javax.swing.JFrame;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxGraph;
import com.mxgraph.view.mxStylesheet;

public class HelloWorld2 extends JFrame
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2707712944901661771L;

	public HelloWorld2()
	{
		super("Hello, World!");

		mxGraph graph = new mxGraph();
		Object parent = graph.getDefaultParent();

		graph.getModel().beginUpdate();
		try
		{
			Object v1 = graph.insertVertex(parent, null, "Hello", 20, 20, 80,
					30);
			Object v2 = graph.insertVertex(parent, null, "World!", 240, 150,
					80, 30);
			graph.insertEdge(parent, null, "Edge", v1, v2);
			
			// 修改style
			Object[] cells = new Object[2];
			cells[0] = v1;
			cells[1] = v2;
			graph.setCellStyles(mxConstants.STYLE_LABEL_POSITION, mxConstants.ALIGN_RIGHT,cells);
			//graph.setCellStyles(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_TRIANGLE,cells);
			//graph.setCellStyles(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_ACTOR,cells);
			//graph.setCellStyles(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_HEXAGON,cells);
			//graph.setCellStyles(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_LABEL,cells);
			graph.setCellStyles(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_SWIMLANE,cells);
	
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
		System.out.println(style.getStyles());
	}

}
