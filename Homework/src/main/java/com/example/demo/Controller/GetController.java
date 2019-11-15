package com.example.demo.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Message.Message;

import net.sf.json.JSONArray;

@RestController
@RequestMapping("/get")
public class GetController
{
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getLines(HttpServletResponse response)
	{
		final String rootPath = "src/main/resources/messages/";
		response.setHeader("Access-Control-Allow-Origin", "*");
		
		File fileReader = new File(rootPath);
		String[] fileList = fileReader.list(); 
		ArrayList<Message> messages = new ArrayList<Message>();
		
		for(int i=fileList.length-1;i>=0;--i)
		{
			fileReader = new File(rootPath + fileList[i]);
			ObjectInputStream ois = null;
			try
			{
				ois = new ObjectInputStream(new FileInputStream(fileReader));
				Message message = (Message)ois.readObject();
				messages.add(message);
				ois.close();
			} catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		JSONArray a = JSONArray.fromObject(messages);
		
		//System.out.println(a.toString());
		
		
		return a.toString();
	}
}