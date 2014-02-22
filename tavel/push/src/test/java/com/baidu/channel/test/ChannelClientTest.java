package com.baidu.channel.test;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import com.baidu.yun.channel.auth.ChannelKeyPair;
import com.baidu.yun.channel.client.BaiduChannelClient;
import com.baidu.yun.channel.exception.ChannelClientException;
import com.baidu.yun.channel.exception.ChannelServerException;
import com.baidu.yun.channel.model.BindInfo;
import com.baidu.yun.channel.model.ChannelMessage;
import com.baidu.yun.channel.model.FetchMessageRequest;
import com.baidu.yun.channel.model.FetchMessageResponse;
import com.baidu.yun.channel.model.InitAppIoscertRequest;
import com.baidu.yun.channel.model.PushBroadcastMessageRequest;
import com.baidu.yun.channel.model.PushBroadcastMessageResponse;
import com.baidu.yun.channel.model.PushTagMessageRequest;
import com.baidu.yun.channel.model.PushTagMessageResponse;
import com.baidu.yun.channel.model.PushUnicastMessageRequest;
import com.baidu.yun.channel.model.PushUnicastMessageResponse;
import com.baidu.yun.channel.model.QueryBindListRequest;
import com.baidu.yun.channel.model.QueryBindListResponse;
import com.baidu.yun.channel.model.VerifyBindRequest;
import com.baidu.yun.core.log.YunLogEvent;
import com.baidu.yun.core.log.YunLogHandler;

public class ChannelClientTest {

	// n2WOyGXVgnBV31TIRYGdt11s/rno1pacvXpSjjf28EG39IEx4FweClLrm
//	private static MiniWebServer webServer = MiniWebServer.getInstance();

	@Test
	public void testQueryBindList() {
		
		// 1. ����developerƽ̨��ApiKey/SecretKey
		String apiKey = "GkWwrvZrCaMQfCZ190ujndZm";
		String secretKey = "I5nqT2szvC12Qdf1gHZ5RSpPnluVo4VI";
		ChannelKeyPair pair = new ChannelKeyPair(apiKey, secretKey);
		
		// 2. ����BaiduChannelClient����ʵ��
		BaiduChannelClient channelClient = new BaiduChannelClient(pair);
		
		// 3. ��Ҫ�˽⽻��ϸ�ڣ���ע��YunLogHandler��
		channelClient.setChannelLogHandler(new YunLogHandler() {
			@Override
			public void onHandle(YunLogEvent event) {
				// TODO Auto-generated method stub
				System.out.println(event.getMessage());
			}
		});
		
		try {
			// 4. �������������
			QueryBindListRequest request = new QueryBindListRequest();
			request.setUserId("1144280722819924199");
			
			// 5. ����queryBindList�ӿ�
			QueryBindListResponse response = channelClient.queryBindList(request);
			
			// 6. �Է��صĽ�������в���
			List<BindInfo> bindInfos = response.getBinds();
			for ( BindInfo bindInfo : bindInfos ) {
				long channelId = bindInfo.getChannelId();
				String userId = bindInfo.getUserId();
				int status = bindInfo.getBindStatus();
				System.out.println("channel_id:" + channelId + ", user_id: " + userId + ", status: " + status);
				
				String bindName = bindInfo.getBindName();
				long bindTime = bindInfo.getBindTime();
				String deviceId = bindInfo.getDeviceId();
				int deviceType = bindInfo.getDeviceType();
				long timestamp = bindInfo.getOnlineTimestamp();
				long expire = bindInfo.getOnlineExpires();
				
				System.out.println("bind_name:" + bindName + "\t" + "bind_time:" + bindTime);
				System.out.println("device_type:" + deviceType + "\tdeviceId" + deviceId);
				System.out.println(String.format("timestamp: %d, expire: %d", timestamp, expire));
				
			}
			
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
	
	@Test
	public void testPushUnicastMessage() {

		/*
		 * @brief	���͵�����Ϣ(��Ϣ����Ϊ͸�����ɿ�����Ӧ���Լ�4������Ϣ����)
		 * 			message_type = 0 (Ĭ��Ϊ0)
		 */
		
		// 1. ����developerƽ̨��ApiKey/SecretKey
		String apiKey = "GkWwrvZrCaMQfCZ190ujndZm";
		String secretKey = "I5nqT2szvC12Qdf1gHZ5RSpPnluVo4VI";
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
			PushUnicastMessageRequest request = new PushUnicastMessageRequest();
			request.setDeviceType(3);
			request.setChannelId(4035698885061886729L);
			request.setUserId("1144280722819924199");
			
			request.setMessage("hello channel");
			
			// 5. ����pushMessage�ӿ�
			PushUnicastMessageResponse response = channelClient.pushUnicastMessage(request);
				
			// 6. ��֤���ͳɹ�
			Assert.assertEquals(1, response.getSuccessAmount());
			
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

	@Test
	public void testPushUnicastAndroidNotification() {

		/*
		 * @brief	���͵�����Ϣ(��Ϣ����Ϊ֪ͨ��Android sdk service9����Ϣ����֪ͨ������Ӧ�ã� ����IOS����)
		 * 			message_type = 1, device_type = 3
		 */
		
		// 1. ����developerƽ̨��ApiKey/SecretKey
		String apiKey = "EURAf2Qzru12h1m57nYgFImj";
		String secretKey = "AZvCruwWzTeORphcQqKyQGGAYdLLXZ00";
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
			PushUnicastMessageRequest request = new PushUnicastMessageRequest();
			request.setDeviceType(3);
			request.setChannelId(4035698885061886729L);
			request.setUserId("814432199857187466");
			
			request.setMessageType(1);
			request.setMessage("{\"title\":\"Notify_title_danbo\",\"description\":\"Notify_description_content\"}");
			
			// 5. ����pushMessage�ӿ�
			PushUnicastMessageResponse response = channelClient.pushUnicastMessage(request);
				
			// 6. ��֤���ͳɹ�
			Assert.assertEquals(1, response.getSuccessAmount());
			
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
	
	@Test
	public void testPushUnicastIosNotification() {

		/*
		 * @brief	���͵�����Ϣ(��Ϣ����Ϊ֪ͨ��IOSͨ��APNS�յ�֪ͨ)
		 * 			message_type = 1, device_type = 4
		 */
		
		// 1. ����developerƽ̨��ApiKey/SecretKey
		String apiKey = "EURAf2Qzru12h1m57nYgFImj";
		String secretKey = "AZvCruwWzTeORphcQqKyQGGAYdLLXZ00";
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
			PushUnicastMessageRequest request = new PushUnicastMessageRequest();
			request.setDeviceType(4);
//			request.setChannelId(4035698885061886729L);
			request.setUserId("814432199857187466");
			
			request.setMessageType(1);
			request.setMessage("{aps}");
			
			// 5. ����pushMessage�ӿ�
			PushUnicastMessageResponse response = channelClient.pushUnicastMessage(request);
				
			// 6. ��֤���ͳɹ�
			Assert.assertEquals(1, response.getSuccessAmount());
			
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
	
	@Test
	public void testPushTagMessage() {

		// 1. ����developerƽ̨��ApiKey/SecretKey
		String apiKey = "GkWwrvZrCaMQfCZ190ujndZm";
		String secretKey = "I5nqT2szvC12Qdf1gHZ5RSpPnluVo4VI";
		ChannelKeyPair pair = new ChannelKeyPair(apiKey, secretKey);
		
		// 2. ����BaiduChannelClient����ʵ��
		BaiduChannelClient channelClient = new BaiduChannelClient(pair);
		
		// 3. ��Ҫ�˽⽻��ϸ�ڣ���ע��YunLogHandler��
		channelClient.setChannelLogHandler(new YunLogHandler() {
			@Override
			public void onHandle(YunLogEvent event) {
				// TODO Auto-generated method stub
				System.out.println(event.getMessage());
			}
		});
		
		try {
			
			// 4. �������������
			PushTagMessageRequest request = new PushTagMessageRequest();
			request.setMessageType(0);
			request.setDeviceType(3);
			request.setTagName("������Ҫ��");
			request.setMessage("{\"title\":\"��֪ͨ\",\"description\":\"����Ҫ����ddd�����ٶȻص����ǡ�\"}");
			
			// 5. ����pushTagMessage�ӿ�
			PushTagMessageResponse response = channelClient.pushTagMessage(request);
			if ( response.getSuccessAmount() == 1 ) {
				// TODO
			}
			
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
	
	@Test
	public void testPushBroadcastMessage() {

		// 1. ����developerƽ̨��ApiKey/SecretKey
		String apiKey = "GkWwrvZrCaMQfCZ190ujndZm";
		String secretKey = "I5nqT2szvC12Qdf1gHZ5RSpPnluVo4VI";
		ChannelKeyPair pair = new ChannelKeyPair(apiKey, secretKey);
		
		// 2. ����BaiduChannelClient����ʵ��
		BaiduChannelClient channelClient = new BaiduChannelClient(pair);
		
		// 3. ��Ҫ�˽⽻��ϸ�ڣ���ע��YunLogHandler��
		channelClient.setChannelLogHandler(new YunLogHandler() {
			@Override
			public void onHandle(YunLogEvent event) {
				// TODO Auto-generated method stub
				System.out.println(event.getMessage());
			}
		});
		
		try {
			
			// 4. �������������
			PushBroadcastMessageRequest request = new PushBroadcastMessageRequest();
			request.setMessageType(0);
			request.setDeviceType(3);
			request.setMessage("{\"title\":\"��֪ͨ\",\"description\":\"����Ҫ����ddd�����ٶȻص����ǡ�\"}");
			
			// 5. ����pushTagMessage�ӿ�
			PushBroadcastMessageResponse response = channelClient.pushBroadcastMessage(request);
			if ( response.getSuccessAmount() == 1 ) {
				// TODO
			}
			
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
	
	@Test
	public void testBindVerify() {

		// 1. ����developerƽ̨��ApiKey/SecretKey
		String apiKey = "GkWwrvZrCaMQfCZ190ujndZm";
		String secretKey = "I5nqT2szvC12Qdf1gHZ5RSpPnluVo4VI";
		ChannelKeyPair pair = new ChannelKeyPair(apiKey, secretKey);
		
		// 2. ����BaiduChannelClient����ʵ��
		BaiduChannelClient channelClient = new BaiduChannelClient(pair);
		
		// 3. ��Ҫ�˽⽻��ϸ�ڣ���ע��YunLogHandler��
		channelClient.setChannelLogHandler(new YunLogHandler() {
			@Override
			public void onHandle(YunLogEvent event) {
				// TODO Auto-generated method stub
				System.out.println(event.getMessage());
			}
		});
		
		try {
			
			// 4. �������������
			VerifyBindRequest request = new VerifyBindRequest();
			request.setChannelId(4035698885061886729L);
			request.setUserId("1144280722819924199");

			// 5. ����verifyBind�ӿ�
			channelClient.verifyBind(request);
			
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
	
	// ----------------------------------------------------------------------------
	@Test
	public void testFetchMessage() {

		// 1. ����developerƽ̨��ApiKey/SecretKey
		String apiKey = "GkWwrvZrCaMQfCZ190ujndZm";
		String secretKey = "I5nqT2szvC12Qdf1gHZ5RSpPnluVo4VI";
		ChannelKeyPair pair = new ChannelKeyPair(apiKey, secretKey);
		
		// 2. ����BaiduChannelClient����ʵ��
		BaiduChannelClient channelClient = new BaiduChannelClient(pair);
		
		// 3. ��Ҫ�˽⽻��ϸ�ڣ���ע��YunLogHandler��
		channelClient.setChannelLogHandler(new YunLogHandler() {
			@Override
			public void onHandle(YunLogEvent event) {
				// TODO Auto-generated method stub
				System.out.println(event.getMessage());
			}
		});
		
		try {
			
			// 4. �������������
			FetchMessageRequest request = new FetchMessageRequest();
			request.setUserId("1144280722819924199");
			
			// 5. ����fetchMessage�ӿ�
			FetchMessageResponse resp = channelClient.fetchMessage(request);
			List<ChannelMessage> messages = resp.getMessages();
			for ( ChannelMessage msg : messages ) {
				System.out.println(msg.getData());
			}
			
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
	
	
	@Test
	public void testInitIosCert() {

		/*
		 */
		
		// 1. ����developerƽ̨��ApiKey/SecretKey
		String apiKey = "EURAf2Qzru12h1m57nYgFImj";
		String secretKey = "AZvCruwWzTeORphcQqKyQGGAYdLLXZ00";
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
			InitAppIoscertRequest request = new InitAppIoscertRequest();
			request.setName("name");
			request.setDescription("description");
			
			request.setDevCert("");
			request.setReleaseCert("");
			
			// 5. ����initAppIoscert�ӿ�
			channelClient.initAppIoscert(request);
				
			
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
