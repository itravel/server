package com.weiminw.business.aos;

import com.weiminw.travel.dao.impls.pos.SellerEntity;
import com.weiminw.travel.interfaces.daos.ISeller;

public final class Sellers {
	public static ISeller of(){
		return new SellerEntity();
	}
	public static ISeller of(String name, String password,  long hid, String cellPhone, String fixTelephone,
			String cloudPushCk, String cloudPushTag, String cloudPushUk,
			String identity,
			byte identityType){
		ISeller seller = new SellerEntity();
		seller.setCellPhone(cellPhone);
		seller.setPassword(password);
		seller.setCloudPushCk(cloudPushCk);
		seller.setCloudPushTag(cloudPushTag);
		seller.setCloudPushUk(cloudPushUk);
		seller.setFixTelephone(fixTelephone);
		seller.setHid(hid);
		seller.setIdentify(identity);
		seller.setIdentifyType(identityType);
		seller.setName(name);
		return seller;
	}
	
}
