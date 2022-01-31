package domain;

/**
 * @ProjectName: AddBeansProgrammatically
 * @Author: Temesgen D.
 * @Date: 1/31/22
 */

public class Parrot {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Parrot{" +
                "name='" + name + '\'' +
                '}';
    }
}
