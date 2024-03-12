package entities;

import java.io.Serializable;
import java.util.Objects;

public abstract class Person {
    protected Integer id;
    protected String name;

    public String getName() { return name; }
    public void setName(String name) {
            this.name = name;
        }

    public Integer getId() { return id; }
    public void setId (Integer id) { this.id = id; }
}
