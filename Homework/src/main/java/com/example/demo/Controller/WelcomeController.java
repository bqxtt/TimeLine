package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WelcomeController
{
	@RequestMapping("/submit")
	public String submit()
	{
		return "submit";
	}
	@RequestMapping("/showResult")
	public String showResult()
	{
		//System.out.println("hhh");
		return "showResult";
	}
}
