package com.example.cafe.serv;

import java.util.List;

import com.example.cafe.payload.userdt;

public interface userserv {
	userdt regnewuser(userdt us);
	userdt createuser(userdt us);
	userdt updateuser(userdt usd,Integer id);
	userdt getuser(Integer id);
	List<userdt> getusers();
	void deleteuser(Integer id);

}
