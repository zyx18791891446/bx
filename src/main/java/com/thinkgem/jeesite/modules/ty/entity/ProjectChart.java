package com.thinkgem.jeesite.modules.ty.entity;

public class ProjectChart {
private String name;   //单位
private String value;   //总费用
@Override
public String toString() {
	return "ProjectChart [name=" + name + ", value=" + value + "]";
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getValue() {
	return value;
}
public void setValue(String value) {
	this.value = value;
}

}
