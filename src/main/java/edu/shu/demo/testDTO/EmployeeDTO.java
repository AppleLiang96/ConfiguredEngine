package edu.shu.demo.testDTO;


import edu.shu.base.AbstractTreeNode;

public class EmployeeDTO extends AbstractTreeNode {

	private String name;
	private Integer age;

	public EmployeeDTO(String name, Integer age) {
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
}
