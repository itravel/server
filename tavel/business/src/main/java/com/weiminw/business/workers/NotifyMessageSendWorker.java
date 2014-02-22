package com.weiminw.business.workers;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.baidu.yun.channel.auth.ChannelKeyPair;
import com.baidu.yun.channel.client.BaiduChannelClient;
import com.baidu.yun.channel.model.PushUnicastMessageRequest;
import com.baidu.yun.channel.model.PushUnicastMessageResponse;



public final class NotifyMessageSendWorker {
	private static final ExecutorService executor = Executors.newFixedThreadPool(100);
	private static final Logger logger = LogManager.getLogger(NotifyMessageSendWorker.class);
	private final static class MessagePush implements Callable<String> {
		private static final String apiKey = "vnWOlOtrAtGQYENrRmwr0KjK";
		private static final String secretKey = "bVtWAjGKkAAIB8QBgPxOHCzbcZmRmCeY";
		private static final ChannelKeyPair pair = new ChannelKeyPair(apiKey, secretKey);
		private static final BaiduChannelClient channelClient = new BaiduChannelClient(pair);
		private final PushUnicastMessageRequest request;
		public MessagePush(String baiduPushUerId,long baiduPushChannelId,String message) {
			request = new PushUnicastMessageRequest();
			request.setDeviceType(3);	// device_type => 1: web 2: pc 3:android 4:ios 5:wp		
			request.setChannelId(baiduPushChannelId);	
			request.setUserId(baiduPushUerId);
			request.setMessageType(1);
			request.setMessage(message);
			
		}
		@Override
		public String call() throws Exception {
			PushUnicastMessageResponse response = channelClient.pushUnicastMessage(request);
			logger.debug(response.getSuccessAmount());
			return String.valueOf(response.getSuccessAmount());
		}
	}
	public static void execute(Callable<String> callable) {
		executor.submit(callable);
	}
	
	public static NotifyMessageSendWorker create(){
		return new NotifyMessageSendWorker();
	}
//	public boolean send(IReservationRequest request){
//		MessagePush push = new MessagePush(request.getReceiver().getCloudPushUk(),Long.valueOf(request.getReceiver().getCloudPushCk()),"预定一间大床房,价格200~300之间");
//		executor.submit(push);
//		return false;
//	}

}
