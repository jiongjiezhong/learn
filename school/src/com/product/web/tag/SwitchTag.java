package com.product.web.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class SwitchTag extends SimpleTagSupport {
	private Object value;
	
	private String switches;
	
	private String choices;
	

	public String getSwitches() {
		return switches;
	}

	public void setSwitches(String switches) {
		this.switches = switches;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public String getChoices() {
		return choices;
	}

	public void setChoices(String choices) {
		this.choices = choices;
	}

	@Override
	public void doTag() throws JspException, IOException {
		//初始�?
		if(value==null) value = "";
		//判断选项是否有效
		if(switches == null||choices == null) getJspContext().getOut().print(value.toString());
		String[] switchArray = switches.split(",");
		String[] choiceArray = choices.split(",");
		if(switchArray.length != choiceArray.length){
			getJspContext().getOut().print(value.toString());
		}
		//根据值显示显示内�?
		for(int i=0; i<switchArray.length; i++){
			String item = switchArray[i];
			if("".equals(item)) continue;
			if(item.equals(value.toString())) {
				getJspContext().getOut().print(choiceArray[i]);
				break;
			}
		}
	}

}
