package com.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sample.repository.MemberRepository;

@Controller
@Transactional(rollbackFor = Exception.class)
@RequestMapping("/sa")
public class TestController {

	@Autowired
	MemberRepository memberRepository;

	@ResponseBody
	@RequestMapping("/hello")
	public String name() throws Exception {
		return "hello";
	}
}
