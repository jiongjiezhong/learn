package com.product.web.tag;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class DateFormatTag extends SimpleTagSupport {

	private Date value;
	private String format;
	private String locale;
	
	public Date getValue() {
		return value;
	}

	public void setValue(Date value) {
		this.value = value;
	}

	public String getFormat() {
		return format;
	}
	
	public void setFormat(String format) {
		this.format = format;
	}
	
	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	@Override
	public void doTag() throws JspException, IOException {
		if(null==getValue()){
			getJspContext().getOut().print("");
		}else{
			String lang = locale.split("_")[0];
			String country = locale.split("_")[1];
			String str = new SimpleDateFormat(format,new Locale(lang, country)).format(getValue());
			getJspContext().getOut().print(str);
		}
	}


}
