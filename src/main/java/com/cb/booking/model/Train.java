package com.cb.booking.model;

import lombok.Data;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Data
public class Train {

    private String from;
    private String to;
    private double price;

    public static final int NO_OF_SEATS_IN_SECTION = 3; //can be moved to configuration for each section
    private Map<String, Section> trainSectionsMap;

    public Train(String[] sectionNames, String from, String to, double price) {

        this.from = from;
        this.to = to;
        this.price = price;
        trainSectionsMap = new HashMap<>();
        for(String sectionName: sectionNames) {
            trainSectionsMap.put(sectionName, new Section(NO_OF_SEATS_IN_SECTION));
        }

    }
}
