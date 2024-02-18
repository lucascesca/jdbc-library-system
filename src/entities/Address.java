package entities;

import entities.enums.States;

import java.io.Serializable;
import java.util.Objects;

public class Address implements Serializable {
    private Integer id;
    private String street;
    private Integer number;
    private String type;
    private String complement;
    private String city;
    private States uf;

    public Address() {}

    public Address(Integer id, String street, Integer number, String type, String complement, String city, States uf) {
        this.street = street;
        this.number = number;
        this.type = type;
        this.complement = complement;
        this.city = city;
        this.uf = uf;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public States getUf() {
        return uf;
    }

    public void setUf(States uf) {
        this.uf = uf;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address address)) return false;
        return Objects.equals(getId(), address.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", number=" + number +
                ", type='" + type + '\'' +
                ", complement='" + complement + '\'' +
                ", city='" + city + '\'' +
                ", uf=" + uf +
                '}';
    }
}
