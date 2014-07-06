package com.itravel.server.dal.common;

public class LimitParameter {
	int page = 0;
	//int firstPageNum = 15;
	//int everyPageNum = 10;
	int firstPageNum = 50;
	int everyPageNum = 40;
	
	public LimitParameter(int page){
		this.page = page;
	}
	
	public int getLimitFirstPar(){
		int firstPage = page;
		if(firstPage==0){
			return 0;
		}else{
			int result = firstPageNum;
			while(firstPage>1){
				result += everyPageNum;
				firstPage--;
			}
			return result;
		}
	}
	
	public int getLimitSecondPar(){
		int secondPage = page;
		if(secondPage==0){
			return firstPageNum;
		}else{
			return everyPageNum;
		}
	}
}
