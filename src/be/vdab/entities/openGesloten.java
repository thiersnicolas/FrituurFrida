package be.vdab.entities;

import java.time.LocalDateTime;

public class openGesloten {
@Override
public String toString() {
	int day = LocalDateTime.now().getDayOfWeek().getValue();
	return day == (1|4) ? "<img src= 'images/gesloten.png' alt='gesloten'>" : 
		"<img src= 'images/open.png' alt='open'>";
}
}
