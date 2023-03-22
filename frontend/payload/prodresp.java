package com.example.cafe.payload;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class prodresp {
	
	private List<productdt> prods;
	private int pageno;
	private int pagesize;
	private long totalprods;
	private int totalpages;
	private boolean lastpage;
}
