package com.example.p01;



import java.time.LocalDate;
import java.time.YearMonth;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.EnableScheduling;




@SpringBootTest
class P01ApplicationTests {

	@Test
	void ZZZ() {
		int year = LocalDate.now().getYear();
		int month = 12;
		if (month == 12) {
			year = year + 1;
			month = 1;
		} else {
			month = month + 1;
		}			
		int howManyDay = YearMonth.of(year, month).lengthOfMonth();
		LocalDate date = LocalDate.of(year, month, 1);
		System.out.println(howManyDay);
		System.out.println(date);
	}


}
