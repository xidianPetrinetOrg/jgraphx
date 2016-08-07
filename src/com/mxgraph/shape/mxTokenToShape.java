package com.mxgraph.shape;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 传递token number至mxTokenEllipseShape，在椭圆中心显示token number<br>
 * 本类必须完整实现序列化，否则，拖动形状时，产生异常java.io.NotSerializableException。<br>
 * (1) 无参构造函数[可选]; (2) set、get实例变量; (3) writeObject(),readObject()
 * @see mxTokenEllipseShape
 * @author Jtduan
 *
 */
public class mxTokenToShape implements Serializable
{	
	private static final long serialVersionUID = 109846063257947121L;

	/** 在椭圆中心显示token number */
	private int tokenNum;
	
	/** shape label， 形状显示标识 */
	private String label;
	
	/** 
	 * 默认无参构造函数，序列化必须。
	 * 用于mxTokenEllipseShape，在椭圆中心显示token number
	 */
    public mxTokenToShape() {
		this.tokenNum = 0;
		this.label = "";
	}
    
    /**
     * 用于mxTokenEllipseShape，在椭圆中心显示token number
     * @param label  形状显示标识
     * @param tokenNum
     */
    public mxTokenToShape(String label, int tokenNum) {
		this.tokenNum = tokenNum;
		this.label = label;
	}

	/** 获取shape label*/
	public String getLabel() {
		return label;
	}

	/** 设置shape label， 作为此shape的显示标识**/
	public void setLabel(String label) {
		this.label = label;
	}

	/** 设置token number, 显示在椭圆形状中心 */
	public void setTokenNum(int tokenNum) {
		this.tokenNum = tokenNum;
	}
	
	/** 获取token number */
	public int getTokenNum() {
		return tokenNum;
	}

	/**
	 * 显示椭圆形状的lable，缺省是显示在椭圆中心。为了不与token显示位置冲突，请改变其显示位置<br>
	 * mxGraph graph = new mxGraph();<br>
	 * label的水平方向显示位置:<br>
	 * graph.setCellStyles(mxConstants.STYLE_LABEL_POSITION, mxConstants.ALIGN_RIGHT,cells);
	 * label的垂直方向显示位置:<br>
	 * graph.setCellStyles(mxConstants.STYLE_VERTICAL_LABEL_POSITION, mxConstants.ALIGN_TOP,cells);	
	 * @return
	 */
	@Override
	public String toString() { return label;}
	
	/** 序列化必须实现 */
	private void writeObject(ObjectOutputStream out) throws IOException {
		out.writeObject(label);
		out.writeInt(tokenNum);
	}
	
	/** 序列化必须实现 */
	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		label = (String) in.readObject();
		tokenNum = in.readInt();
	}

}
