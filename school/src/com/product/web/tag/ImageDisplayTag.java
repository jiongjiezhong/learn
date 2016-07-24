package com.product.web.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class ImageDisplayTag extends SimpleTagSupport {
	private String imageList;
	private int index; // -1 全部显示
	private String imagePath;
	private String imageStyle;
	private boolean canDelete = false; //是否显示删除按钮
	private String deleteFunc;         //删除图片的javascript函数
	@Override
	public void doTag() throws JspException, IOException {
		if(getImageList() == null || "".equals(getImageList())){
			getJspContext().getOut().print("");
		}else{
			StringBuffer sb = new StringBuffer();
			String[] images = imageList.split(";");
			if(index == -1){
				for(int i=0; i<images.length; i++){
					if(null == images[i] || "".equals(images[i])) continue;
					if(canDelete){
						sb.append("<span style='position:relative;float:left;z-index:99;"+imageStyle+"'>");
						sb.append("<a style='position:absolute;right:0;top:5px;' href='javascript:void(0)' title='"+images[i]+"' onclick='"+(deleteFunc==null?"":deleteFunc+"(this)")+"' class='btnDel' ></a>");
						sb.append("<img src='"+ imagePath+"/"+images[i]+"' style='float:left;margin:2px;"+imageStyle+"'/></span>");
					}else{
						sb.append("<img src='"+ imagePath+"/"+images[i]+"' style='float:left;margin:2px;"+imageStyle+"'/>");
					}
				}
			}else{
				if(canDelete){
					sb.append("<span style='position:relative;float:left;"+imageStyle+"'");
					sb.append("<a style='position:absolute;right:0;top:5px;' href='javascript:void(0)' title='"+images[index]+"' onclick='"+(deleteFunc==null?"":deleteFunc+"(this)")+"' class='btnDel' ></a>");
					sb.append("<img src='"+ imagePath+"/"+images[index]+"' style='float:left;margin:2px;"+imageStyle+"'/></span>");
				}else{
					if(index<=images.length-1 && (!"".equals(images[index].trim())||null==images[index]))
						sb.append("<img src='"+imagePath+"/"+images[index]+"' style='float:left;margin:2px;"+imageStyle+"'/>");
				}
			}
			getJspContext().getOut().print(sb.toString());
		}
	}
	public String getImageList() {
		return imageList;
	}
	public void setImageList(String imageList) {
		this.imageList = imageList;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public String getImageStyle() {
		return imageStyle;
	}
	public void setImageStyle(String imageStyle) {
		this.imageStyle = imageStyle;
	}
	public boolean isCanDelete() {
		return canDelete;
	}
	public void setCanDelete(boolean canDelete) {
		this.canDelete = canDelete;
	}
	public String getDeleteFunc() {
		return deleteFunc;
	}
	public void setDeleteFunc(String deleteFunc) {
		this.deleteFunc = deleteFunc;
	}
	
}
