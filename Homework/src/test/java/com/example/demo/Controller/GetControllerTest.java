package com.example.demo.Controller;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.demo.Message.Message;

class GetControllerTest
{
	static private TextController servlet;
	static private HttpServletRequest mockRequest;
	static private HttpServletResponse mockResponse;
	static private ArrayList<Integer> files;

	@BeforeAll
	static public void setUp()
	{
		servlet = new TextController();
		files = new ArrayList<Integer>();
		mockRequest = createMock(HttpServletRequest.class); // 加载
		mockResponse = createMock(HttpServletResponse.class);
	}
	@AfterAll
	static public void tearDown()
	{
		File file;
		final String rootPath = "src/main/resources/messages/"; 
		for(int i=0;i<files.size();++i)
		{
			file = new File(rootPath + files.get(i) + ".txt");
			if(file.exists())
				file.delete();
		}
	}
	@Test
	@DisplayName("测试用户昵称及内容")
	void test()
	{
		mockResponse.setHeader("Access-Control-Allow-Origin", "*");
		replay(mockResponse);
		servlet.userSend("team001", "2333333", "data", mockResponse);
		verify(mockResponse);

		
		final String rootPath = "src/main/resources/messages/"; 
		File fileReader = new File(rootPath); 
		String[] fileList = fileReader.list(); 
		int len = fileList.length - 1; 
		fileReader = new File(rootPath + len + ".txt");
		files.add(len);
		
		
		try
		{
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileReader));
			Message message = (Message)ois.readObject();
			assertEquals(message.getName(),"team001");
			assertEquals(message.getContent(),"2333333");
			assertEquals(message.getImage(),"data");
			ois.close();
		}
		catch (Exception e)
		{
			
		}
	}

}
