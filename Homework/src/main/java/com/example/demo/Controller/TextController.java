package com.example.demo.Controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Message.Message;


@RestController
@RequestMapping("/line")
public class TextController
{
	// localhost:8080/line/send
	@RequestMapping(value = "/send", method = RequestMethod.POST)
	public void userSend(@RequestParam(value = "user") String user, @RequestParam(value = "content") String content,
			@RequestParam(value = "image") String image, HttpServletResponse response)
	{
		final String rootPath = "src/main/resources/messages/";
		response.setHeader("Access-Control-Allow-Origin", "*");
		System.out.println("Wow");

		Date date = new Date();
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = sdf1.format(date);
		
		System.out.println(time);
		
		Message message = new Message(user,content,image,time);
		
		
		File fileReader = new File(rootPath);
		String[] fileList = fileReader.list(); 
		int len = fileList.length;
		
		fileReader = new File(rootPath + len + ".txt");
		
		if(!fileReader.exists())
		{
			try
			{
				fileReader.createNewFile();
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileReader));
				oos.writeObject(message);
				oos.close();
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		/*
		 * Write to file.txt
		 */
	}

	@RequestMapping("/")
	public String index(HttpServletResponse response)
	{
		response.setHeader("Access-Control-Allow-Origin", "*");
		return "Greetings from Spring Boot!";
	}
}