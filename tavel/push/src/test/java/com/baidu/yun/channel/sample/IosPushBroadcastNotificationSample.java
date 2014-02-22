package com.baidu.yun.channel.sample;

import com.baidu.yun.channel.auth.ChannelKeyPair;
import com.baidu.yun.channel.client.BaiduChannelClient;
import com.baidu.yun.channel.exception.ChannelClientException;
import com.baidu.yun.channel.exception.ChannelServerException;
import com.baidu.yun.channel.model.PushBroadcastMessageRequest;
import com.baidu.yun.channel.model.PushBroadcastMessageResponse;
import com.baidu.yun.core.log.YunLogEvent;
import com.baidu.yun.core.log.YunLogHandler;

public class IosPushBroadcastNotificationSample {

	public static void main(String[] args) {
		
		/*
		 * @brief	���͹㲥֪ͨ(IOS APNS)
		 * 			message_type = 1 (Ĭ��Ϊ0)
		 */
		
		// 1. ����developerƽ̨��ApiKey/SecretKey
		String apiKey = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
		String secretKey = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
		ChannelKeyPair pair = new ChannelKeyPair(apiKey, secretKey);
		
		// 2. ����BaiduChannelClient����ʵ��
		BaiduChannelClient channelClient = new BaiduChannelClient(pair);

		
		// 3. ��Ҫ�˽⽻��ϸ�ڣ���ע��YunLogHandler��
		channelClient.setChannelLogHandler(new YunLogHandler() {
			@Override
			public void onHandle(YunLogEvent event) {
				System.out.println(event.getMessage());
			}
		});
		
		try {
			
			// 4. �������������
			PushBroadcastMessageRequest request = new PushBroadcastMessageRequest();
			request.setDeviceType(4);	// device_type => 1: web 2: pc 3:android 4:ios 5:wp	
			request.setMessageType(1);
			request.setDeployStatus(2); // DeployStatus => 1: Developer 2: Production
			request.setMessage("{\"aps\":{\"alert\":\"Hello Baidu Channel\"}}");
 			
			// 5. ����pushMessage�ӿ�
			PushBroadcastMessageResponse response = channelClient.pushBroadcastMessage(request);
				
			// 6. ��֤���ͳɹ�
			System.out.println("push amount : " + response.getSuccessAmount()); 
			
		} catch (ChannelClientException e) {
			// ����ͻ��˴����쳣
			e.printStackTrace();
		} catch (ChannelServerException e) {
			// �������˴����쳣
			System.out.println(
					String.format("request_id: %d, error_code: %d, error_message: %s" , 
						e.getRequestId(), e.getErrorCode(), e.getErrorMsg()
						)
					);
		}
		
	}
	
}
