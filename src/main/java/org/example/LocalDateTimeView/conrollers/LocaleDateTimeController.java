package org.example.LocalDateTimeView.conrollers;

import org.example.LocalDateTimeView.dto.LocalDateTimeDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/datetime")
public class LocaleDateTimeController {
    @GetMapping(produces = "application/json")
    public ResponseEntity<LocalDateTimeDto> getCurrentDateTime() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTimeDto dto = new LocalDateTimeDto(now);
        return ResponseEntity.ok(dto);
    }
}
