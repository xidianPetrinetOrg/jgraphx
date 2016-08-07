/**
 * Copyright (c) 2007-2012, JGraph Ltd
 */
package com.mxgraph.examples.swing;

import javax.swing.JFrame;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxDomUtils;
import com.mxgraph.view.mxGraph;

/**
 * 通过insertVertex的value传递对象，使用Element接口。
 * graph.insertVertex(parent, null, Element对象,20, 20, 80,30);
 *
 */
public class UserObject3 extends JFrame
{

	private static final long serialVersionUID = -708317745824467773L;
	
	public UserObject3()
	{
		super("Hello, World!");
		
		Document doc = mxDomUtils.createDocument();
		Element token1 = doc.createElement("Token1");
		token1.setAttribute("Label", "t1");
		token1.setAttribute("TokenNumber", "5");
		
		Element token2 = doc.createElement("Token2");
		token2.setAttribute("Label", "t2");
		token2.setAttribute("TokenNumber", "2");
		

		mxGraph graph = new mxGraph();

		Object parent = graph.getDefaultParent();

		graph.getModel().beginUpdate();
		try
		{
			Object v1 = graph.insertVertex(parent, null, token1,
				20, 20, 80,30);
			Object v2 = graph.insertVertex(parent, null, token2, 
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
		UserObject3 frame = new UserObject3();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 320);
		frame.setVisible(true);
	}

}
