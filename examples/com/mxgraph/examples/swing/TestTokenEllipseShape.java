package com.mxgraph.examples.swing;

import javax.swing.JFrame;

import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.model.mxCell;
import com.mxgraph.shape.mxTokenToShape;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxGraph;

/**
 * 测试shape: 椭圆中心显示token的数量（大于6）或显示相应数量的小黑点。<br>
 *
 */
public class TestTokenEllipseShape extends JFrame
{
	private static final long serialVersionUID = 5483575278950465873L;

	public TestTokenEllipseShape()
	{
		super("Hello, World!");
	}
	
	public void test2() {
		mxGraph graph = new mxGraph();
		Object parent = graph.getDefaultParent();

		graph.getModel().beginUpdate();
		try
		{
			mxCell v1 = (mxCell) graph.insertVertex(parent, null, "Hello", 20,
			20, 80, 30, "shape=" + mxConstants.SHAPE_RECTANGLE);
			
			//Object v2 = graph.insertVertex(parent, null, "World!", 240, 150, 80, 30,"shape=" + mxConstants.SHAPE_ELLIPSE);
			Object v2 = graph.insertVertex(parent, null, 
					new mxTokenToShape("token",5), 240, 150, 80, 30,
					mxConstants.STYLE_SHAPE + "=" + mxConstants.SHAPE_TOKEN_ELLIPSE);
			
			graph.insertEdge(parent, null, "Edge", v1, v2);
		}
		finally
		{
			graph.getModel().endUpdate();
		}
		
		mxGraphComponent graphComponent = new mxGraphComponent(graph);
		getContentPane().add(graphComponent);
		
		// layout
		mxHierarchicalLayout layout = new mxHierarchicalLayout(graph);
		layout.execute(graph.getDefaultParent());
	}

	public static void main(String[] args)
	{
		TestTokenEllipseShape frame = new TestTokenEllipseShape();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 320);
		frame.setVisible(true);
		//frame.test1();
		frame.test2();
		frame.validate();  // 重画请求，或者： frame.revalidate(); 不知为何，frame.invalidate(); 无反应
		
	}

}
