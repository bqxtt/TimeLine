package com.example.demo.Message;

import java.io.Serializable;

public class Message implements Serializable
{
	String name;
	String content;
	String image;
	String time;
	public Message(String name,String content,String image,String time)
	{
		this.name = name;
		this.content = content;
		this.image = image;
		this.time = time;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getContent()
	{
		return content;
	}
	public void setContent(String content)
	{
		this.content = content;
	}
	public String getImage()
	{
		return image;
	}
	public void setImage(String image)
	{
		this.image = image;
	}
	public String getTime()
	{
		return time;
	}
	public void setTime(String time)
	{
		this.time = time;
	}
	
}
