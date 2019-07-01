package com.yzd.influxdb.service.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by ups on 14/01/18.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Metrics {
    private String name;
    private String pattern;
    private String value;
}
