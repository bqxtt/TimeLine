package com.example.demo.Controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.Message.Message;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.easymock.EasyMock.*;

@SpringBootTest
class TextControllerTest
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
	@DisplayName("测试动态保存")
	void test()
	{
		final String rootPath = "src/main/resources/messages/"; 
		File fileReader = new File(rootPath); 
		int s = fileReader.list().length;
		
		mockResponse.setHeader("Access-Control-Allow-Origin", "*");
		mockResponse.setHeader("Access-Control-Allow-Origin", "*");
		mockResponse.setHeader("Access-Control-Allow-Origin", "*");
		replay(mockResponse);
		servlet.userSend("a", "bbbbb", "i", mockResponse);
		servlet.userSend("sdf", "dd", "", mockResponse);
		servlet.userSend("aa", "dfa", "", mockResponse);
		verify(mockResponse);

		int t = fileReader.list().length;
		
		for(int i=t-1;i>=s;--i)
			files.add(i);
		 
		 
		assertEquals(t - s,3);
		
		
	}

}
