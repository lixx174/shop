package com.qh.domain.primitive;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 时间段
 *
 * @author Jinx
 */
@Getter
@AllArgsConstructor
public class Period {

    private LocalDateTime startTime;

    private LocalDateTime endTime;
}
