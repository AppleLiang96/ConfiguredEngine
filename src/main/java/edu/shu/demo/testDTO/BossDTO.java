package edu.shu.demo.testDTO;

import edu.shu.base.AbstractTreeNode;

import java.util.List;

/**
 * @author liang
 * @create 2020/4/19 10:30 上午
 */
public class BossDTO extends AbstractTreeNode {
    private String name;
    private Integer age;
    private List<ManagerDTO> managerDTOList;

    public BossDTO(String name, Integer age, List<ManagerDTO> managerDTOList) {
        this.name = name;
        this.age = age;
        this.managerDTOList = managerDTOList;
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

    public List<ManagerDTO> getManagerDTOList() {
        return managerDTOList;
    }

    public void setManagerDTOList(List<ManagerDTO> managerDTOList) {
        this.managerDTOList = managerDTOList;
    }
}