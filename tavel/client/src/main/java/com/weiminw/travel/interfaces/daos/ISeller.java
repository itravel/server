package com.weiminw.travel.interfaces.daos;

public interface ISeller {

	public long getId();

	public ISeller setId(int id);

	public String getCellPhone();

	public ISeller setCellPhone(String cellPhone);

	public String getCloudPushCk();

	public ISeller setCloudPushCk(String cloudPushCk);

	public String getCloudPushTag();

	public ISeller setCloudPushTag(String cloudPushTag);

	public String getCloudPushUk();

	public ISeller setCloudPushUk(String cloudPushUk);

	public String getFixTelephone();

	public ISeller setFixTelephone(String fixTelephone);

	public long getHid();

	public ISeller setHid(long hid);

	public String getIdentify();

	public ISeller setIdentify(String identify);

	public byte getIdentifyType();

	public ISeller setIdentifyType(byte identifyType);

	public String getName();

	public ISeller setName(String name);

	public String getPassword();

	public ISeller setPassword(String password);

}