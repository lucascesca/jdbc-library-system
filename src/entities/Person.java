package entities;

import java.io.Serializable;
import java.util.Objects;

public abstract class Person {
    protected Integer id;
    protected String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
            this.name = name;
        }
}
