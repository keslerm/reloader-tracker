package com.dasbiersec.reloader.domain.chronograph;


import javax.persistence.*;

@Entity
@Table(name = "fps")
public class FPS
{
	@Id
	private Integer id;
	private Integer fps;

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Integer getFps()
	{
		return fps;
	}

	public void setFps(Integer fps)
	{
		this.fps = fps;
	}
}
