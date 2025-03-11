package com.product.dto;

import java.time.LocalDateTime;


import lombok.Builder;
import lombok.Data;


@Data
@Builder

public class ExceptionResponse {
	private String statusCode;
	private String error;
	private String message;
	private LocalDateTime timeStamp;
}
