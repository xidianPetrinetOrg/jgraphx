package com.mxgraph.examples.swing;

import javax.swing.JFrame;

import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;
import com.mxgraph.util.mxPoint;
import com.mxgraph.util.mxRectangle;
import com.mxgraph.view.mxGraph;

public class TestLayout extends JFrame
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2707712944901661771L;

	public TestLayout()
	{
		super("Hello, World!");

		mxGraph graph = new mxGraph();
		Object parent = graph.getDefaultParent();

		graph.getModel().beginUpdate();
		try
		{
			Object v1 = graph.insertVertex(parent, null, "Hello", 20, 20, 80, 30, 
					mxConstants.STYLE_LABEL_POSITION + "=" +mxConstants.ALIGN_LEFT);
			        //mxConstants.STYLE_LABEL_POSITION + "=" +mxConstants.ALIGN_RIGHT);
					//"");
			Object v2 = graph.insertVertex(parent, null, "World!", 240, 150, 80, 30,"");
			graph.insertEdge(parent, null, "Edge", v1, v2);
		}
		finally
		{
			graph.getModel().endUpdate();
		}
		
		mxHierarchicalLayout layout = new mxHierarchicalLayout(graph);
		// cell的边界是否包含Label，false，利于对其关系仅与几何形状有关，比较整齐。但是，边界处的label有可能看不见，如，label在vertex的左边时，最左边的label就可能看不见。
		layout.setUseBoundingBox(false);
		// 计算
		layout.execute(parent);
		
		System.out.println(graph.getGraphBounds()); // [x=-54.5,y=0.0,w=134.5,h=110.0]
		mxRectangle rec = graph.getGraphBounds();
		// 保证视图之外的Label能看见
		graph.getView().setTranslate(new mxPoint(-rec.getX(), -rec.getY()));
		
		System.out.println(graph.getGraphBounds()); // [x=0,y=0,w=134.5,h=110.0]
        
		mxGraphComponent graphComponent = new mxGraphComponent(graph);
		getContentPane().add(graphComponent);
	}

	public static void main(String[] args)
	{
		TestLayout frame = new TestLayout();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 320);
		frame.setVisible(true);
	}

}
