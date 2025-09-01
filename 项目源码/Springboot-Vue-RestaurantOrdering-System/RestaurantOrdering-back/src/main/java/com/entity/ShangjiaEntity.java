package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/** 
 * 用户
 */

@TableName("shangjia")
public class ShangjiaEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@TableId(type = IdType.AUTO)
	private Long id;
	
	/**
	 * 用户账号
	 */
	private String username;
	
	/**
	 * 密码
	 */
	private String password;

	/**
	 * 资质
	 */
	private String zizhi;
	
	/**
	 * 用户类型
	 */
	private String role;
	
	private Date addtime;

	private String shenhe;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getZizhi() {
		return zizhi;
	}

	public void setZizhi(String zizhi) {
		this.zizhi = zizhi;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public String getShenhe() {
		return shenhe;
	}

	public void setShenhe(String shenhe) {
		this.shenhe = shenhe;
	}
}
