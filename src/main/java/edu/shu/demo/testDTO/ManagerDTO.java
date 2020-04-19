package edu.shu.demo.testDTO;

import edu.shu.base.AbstractTreeNode;

import java.util.List;

/**
 * @author liang
 * @create 2020/4/19 10:25 上午
 */
public class ManagerDTO extends AbstractTreeNode {
    private String name;
    private Integer age;
    private List<EmployeeDTO> employeeDTOList;

    public ManagerDTO(String name, Integer age, List<EmployeeDTO> employeeDTOList) {
        this.name = name;
        this.age = age;
        this.employeeDTOList = employeeDTOList;
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

    public List<EmployeeDTO> getEmployeeDTOList() {
        return employeeDTOList;
    }

    public void setEmployeeDTOList(List<EmployeeDTO> employeeDTOList) {
        this.employeeDTOList = employeeDTOList;
    }
}
