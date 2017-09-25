package com.redrobin.menuparser;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

public class Franchise {
	private String id;
	private String name;
	private Timestamp lastUpdated;
	
	private List<Restaurant> restaurants;
	
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
	
    public Timestamp getLastUpdated() {
        return lastUpdated;
    }
    public void setLastUpdated(Timestamp lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public List<Restaurant> getRestaurants() {
		return restaurants;
	}
	public void setRestaurants(List<Restaurant> restaurants) {
		this.restaurants = restaurants;
	}
	
	@Override 
	public String toString() {
	    return "NAME=" + name + "  ID=" + id;
	}
	
	
	@Override
	public int hashCode() {
	    return Objects.hash(id, name, lastUpdated);
	}
	
	@Override
	public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
            
        if (!(o instanceof Franchise)) {
            return false;
        }
        
        Franchise that = (Franchise) o;
        return (
                Objects.equals(this.id, that.getId()) &&
                Objects.equals(this.name, that.getName()) &&
                Objects.equals(this.lastUpdated,  that.getLastUpdated())
        );
	}

}
