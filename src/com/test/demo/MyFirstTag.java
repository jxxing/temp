package com.test.demo;

import java.io.IOException;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.JspTag;
import javax.servlet.jsp.tagext.SimpleTag;

public class MyFirstTag implements SimpleTag{
	JspContext jspContext;
	
	public MyFirstTag(){
		System.out.println("无参构造器！");
	}
	public void doTag() throws IOException, JspException {
		System.out.println("doTa");
		jspContext.getOut().print("This is my first tag.");
	}
	
	public void setParent(JspTag parent){
		System.out.println("setParent");
	}
	
	public JspTag getParent(){
		System.out.println("getParent");
		return null;
	}
	
	public void setJspContext(JspContext jspContext){
		System.out.println("setJspContext");
		this.jspContext = jspContext;
	}
	
	public void setJspBody(JspFragment body){
		System.out.println("setJspBody");
	}

}

