package com.mycompany.payrollsystem.system.ui;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.*;

class CLITest {

    @Test
    void testAdminLogin() {
        String input = "admin\nadmin123\n4\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        CLI cli = new CLI();
        assertDoesNotThrow(cli::run, "Admin should log in without issues");
    }

    @Test
    void testHRLogin() {
        String input = "hr\nhr123\n4\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        CLI cli = new CLI();
        assertDoesNotThrow(cli::run, "Admin should log in without issues");
    }


    @Test
    void testHRLoginAndPromotion() {
        String input = "hr\nhr123\n1\n4\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        CLI cli = new CLI();
        assertDoesNotThrow(cli::run, "HR should log in and perform promotion without issues");
    }
}
