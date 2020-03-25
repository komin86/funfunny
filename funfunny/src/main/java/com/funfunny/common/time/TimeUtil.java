package com.funfunny.common.time;

import java.time.LocalDateTime;
import org.springframework.stereotype.Service;

@Service
public class TimeUtil {
	
	public LocalDateTime getLocalDateTime() {
		return LocalDateTime.now();
	}

	
}
