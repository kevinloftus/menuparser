package com.redrobin.menuparser;

import java.util.Objects;

public class MenuCode {
    private String id;
    private String name;
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "NAME=" + name + "  ID=" + id;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
            
        if (!(o instanceof MenuCode)) {
            return false;
        }
        
        MenuCode that = (MenuCode) o;
        return (
                Objects.equals(this.id, that.getId()) &&
                Objects.equals(this.name, that.getName())
                );
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
