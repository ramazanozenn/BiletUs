


package com.biletus.app;

import org.junit.Test;
import static org.junit.Assert.*;

public class TicketPriceTest {

    @Test
    public void totalPriceCalculation_isCorrect() {

        int studentPrice = 400;
        int adultPrice = 500;

        int studentCount = 2;
        int adultCount = 1;

        int expectedTotal = 1300;
        int actualTotal = (studentCount * studentPrice) + (adultCount * adultPrice);

        assertEquals("The total price was calculated incorrectly.", expectedTotal, actualTotal);
    }
}