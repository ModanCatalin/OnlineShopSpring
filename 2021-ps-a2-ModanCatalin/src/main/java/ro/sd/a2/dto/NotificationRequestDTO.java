package ro.sd.a2.dto;

import java.util.List;

public class NotificationRequestDTO {

    private String name;
    private Integer id;
    private List<String> addreess;

    public NotificationRequestDTO(String name, Integer id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<String> getAddreess() {
        return addreess;
    }

    public void setAddreess(List<String> addreess) {
        this.addreess = addreess;
    }

    @Override
    public String toString() {
        return "NotificationRequestDTO{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", addreess=" + addreess +
                '}';
    }
}
