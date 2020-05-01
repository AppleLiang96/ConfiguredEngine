package edu.shu.demo.testDTO;

/**
 * @author liang
 * @create 2020/4/20 10:12 上午
 */
public class WifeDTO {
    private String name;
    private Integer age;

    public WifeDTO(String name, Integer age) {
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
