package edu.shu.demo;

import edu.shu.demo.testDTO.WifeDTO;
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
        WifeDTO wifeDTO1 = new WifeDTO("w1", 20);
        EmployeeDTO employeeDTO1 = new EmployeeDTO("e1", 20, wifeDTO1);
        WifeDTO wifeDTO2 = new WifeDTO("w2", 20);
        EmployeeDTO employeeDTO2 = new EmployeeDTO("e2", 21, wifeDTO2);
        List<EmployeeDTO> employeeDTOS = new ArrayList<>();
        employeeDTOS.add(employeeDTO1);
        employeeDTOS.add(employeeDTO2);
        WifeDTO wifeDTO3 = new WifeDTO("w3", 20);
        ManagerDTO managerDTO1 = new ManagerDTO("m1", 30, wifeDTO3, employeeDTOS);
        List<ManagerDTO> managerDTOS = new ArrayList<>();
        managerDTOS.add(managerDTO1);
        WifeDTO wifeDTO4 = new WifeDTO("w4", 20);
        BossDTO bossDTO = new BossDTO("boss", 50, wifeDTO4, managerDTOS);
        TreeDTO tree = TreeUtils.createTree(bossDTO);
    }
}
