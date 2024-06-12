package com.enterprise.mixsale.common;

import org.springframework.http.HttpStatus;

import com.enterprise.mixsale.payload.ApiResponse;
import com.enterprise.mixsale.util.Constants;


public abstract class AbstractCommon {

	protected final static ApiResponse getSavedSuccessResponse() {
        return ApiResponse.builder()
                .success(true)
                .code(Integer.toString(HttpStatus.OK.value()))
                .message(Constants.MESSAGE_KEYS_SAVE_ENG)
                .messageTh(Constants.MESSAGE_KEYS_SAVE_TH)
                .build();
    }
	
	protected final static ApiResponse getOkResponse(HttpStatus code, String message) {
        return ApiResponse.builder()
                .success(true)
                .code(Integer.toString(code.value()))
                .message(message)
                .build();
    }
	
	protected final static ApiResponse getOkResponse() {
        
        return ApiResponse.builder()
                .success(true)
                .code(Integer.toString(HttpStatus.OK.value()))
                .message(Constants.MESSAGE_KEY_ENG)
                .messageTh(Constants.MESSAGE_KEY_TH)
                .build();
    }
	
	protected final static <T> ApiResponse getOkResponseData(T body) {
        
        return ApiResponse.builder()
                .success(true)
                .code(Integer.toString(HttpStatus.OK.value()))
                .data(body)
                .message(Constants.MESSAGE_KEY_ENG)
                .messageTh(Constants.MESSAGE_KEY_TH)
                .build();
    }
}
