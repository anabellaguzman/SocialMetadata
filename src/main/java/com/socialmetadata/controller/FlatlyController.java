package com.socialmetadata.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FlatlyController {
	
	@RequestMapping("/flatly")
//	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public String setupForm(Map<String, Object> map){
		
		return "flatly";
		
	}

}
