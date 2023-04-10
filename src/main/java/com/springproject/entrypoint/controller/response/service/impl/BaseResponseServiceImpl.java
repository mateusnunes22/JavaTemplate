package com.springproject.entrypoint.controller.response.service.impl;

import org.springframework.stereotype.Component;

import com.springproject.core.domain.UserDomain;
import com.springproject.core.usecase.UserUseCase;
import com.springproject.entrypoint.controller.response.BaseResponse;
import com.springproject.entrypoint.controller.response.service.BaseResponseService;
import com.springproject.util.JwtUtils;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class BaseResponseServiceImpl implements BaseResponseService {

	private final UserUseCase userUseCase;

	@Override
	public void addBaseResponse(BaseResponse response, String message) {
		UserDomain userDomain = JwtUtils.userLogged();
		userDomain = userUseCase.refreshToken(userDomain);
		response.setMessage(message);
		response.setToken(userDomain.getCurrentToken());
	}

	@Override
	public BaseResponse addBaseResponse(String message) {
		BaseResponse response = new BaseResponse();
		addBaseResponse(response, message);
		return response;
	}

}
