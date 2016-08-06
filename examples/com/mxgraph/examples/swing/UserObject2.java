/**
 * Copyright (c) 2007-2012, JGraph Ltd
 */
package com.mxgraph.examples.swing;

import javax.swing.JFrame;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

/**
 * 通过insertVertex的value传递对象，但是，当拖动cell时，有异常java.io.NotSerializableException。
 * graph.insertVertex(parent, null, new TestValue("first"),20, 20, 80,30);
 *
 */
public class UserObject2 extends JFrame
{

	private static final long serialVersionUID = -708317745824467773L;
	
	class TestValue {
		public String label;
		public TestValue() {
			this.label = "";
		}
		
		public TestValue(String label) {
			this.label = label;
		}
		
		public void setLabel(String label) {
			this.label = label;
		}
		
		// 返回形状的Label(显示标识)
		@Override
		public String toString() {
			return label;
		}
	}

	public UserObject2()
	{
		super("Hello, World!");

		mxGraph graph = new mxGraph();

		Object parent = graph.getDefaultParent();

		graph.getModel().beginUpdate();
		try
		{
			Object v1 = graph.insertVertex(parent, null, new TestValue("first"),
				20, 20, 80,30);
			Object v2 = graph.insertVertex(parent, null, new TestValue("second"), 
				240, 150, 80,30);
			graph.insertEdge(parent, null, "first to second", v1, v2);
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
		UserObject2 frame = new UserObject2();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 320);
		frame.setVisible(true);
	}

}
