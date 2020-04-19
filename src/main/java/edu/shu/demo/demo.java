package edu.shu.demo;

import edu.shu.domain.TreeDTO;
import edu.shu.demo.testDTO.BossDTO;
import edu.shu.demo.testDTO.EmployeeDTO;
import edu.shu.demo.testDTO.ManagerDTO;
import edu.shu.utils.TreeUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liang
 * @create 2020/4/19 1:03 下午
 */
public class demo {
    public static void main(String[] args) {
        EmployeeDTO employeeDTO1 = new EmployeeDTO("e1", 20);
        EmployeeDTO employeeDTO2 = new EmployeeDTO("e2", 21);
        List<EmployeeDTO> employeeDTOS = new ArrayList<>();
        employeeDTOS.add(employeeDTO1);
        employeeDTOS.add(employeeDTO2);
        ManagerDTO managerDTO1 = new ManagerDTO("m1", 30, employeeDTOS);
        List<ManagerDTO> managerDTOS = new ArrayList<>();
        managerDTOS.add(managerDTO1);
        BossDTO bossDTO = new BossDTO("boss", 50, managerDTOS);
        TreeDTO tree = TreeUtils.createTree(bossDTO);
    }
}
