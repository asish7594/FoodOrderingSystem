package test.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Restaurant {
    private String id;
    private String name;
    private Location location;
    private Set<Item> itemList;
    private int processingCapacity;
    private float rating;
}
