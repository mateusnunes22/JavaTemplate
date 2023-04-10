package com.springproject.entrypoint.controller.response.service;

import com.springproject.entrypoint.controller.response.BaseResponse;

public interface BaseResponseService {
	void addBaseResponse(BaseResponse response, String message);

	BaseResponse addBaseResponse(String message);
}
