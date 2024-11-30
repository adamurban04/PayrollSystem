package com.mycompany.payrollsystem.system;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScaleLoaderTest {

    @BeforeAll
    static void setUp() throws Exception {
        ScaleLoader.loadScales("src/database/Salaries.csv");
    }

    @Test
    void testLoadPay() {
        int maxScalePoints = ScaleLoader.getMaxScalePoints("Professor");
        assertTrue(maxScalePoints > 0, "Scale points should be loaded for 'Professor'");

        double salary = ScaleLoader.getPay("Academic", "Professor", "1");
        assertTrue(salary > 0, "Salary should be greater than 0 for 'Professor' scale point 1");
    }
}
