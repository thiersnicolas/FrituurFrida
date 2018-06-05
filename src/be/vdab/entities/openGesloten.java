package be.vdab.entities;

import java.time.LocalDateTime;

public class openGesloten {
@Override
public String toString() {
	int day = LocalDateTime.now().getDayOfWeek().getValue();
	return day == (1|4) ? "gesloten" : 
		"open";
}
}
