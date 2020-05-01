package edu.shu.demo.testDTO;


public class EmployeeDTO{

	private String name;
	private Integer age;
	private WifeDTO wifeDTO;

	public EmployeeDTO(String name, Integer age, WifeDTO wifeDTO) {
		this.name = name;
		this.age = age;
		this.wifeDTO = wifeDTO;
	}

	public WifeDTO getWifeDTO() {
		return wifeDTO;
	}

	public void setWifeDTO(WifeDTO wifeDTO) {
		this.wifeDTO = wifeDTO;
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
