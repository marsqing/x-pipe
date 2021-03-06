package com.ctrip.xpipe.redis.protocal.cmd;


import com.ctrip.xpipe.redis.protocal.protocal.RequestStringParser;

import io.netty.channel.Channel;

/**
 * @author wenchao.meng
 *
 * 2016年3月29日 下午2:51:47
 */
public class Replconf extends AbstractRedisCommand{
	
	private ReplConfType replConfType;
	private String argu;
	
	public Replconf(ReplConfType replConfType, String argu) {
		this.replConfType = replConfType;
		this.argu = argu;
	}

	@Override
	public String getName() {
		return "replconf";
	}

	@Override
	protected boolean hasResponse() {
		
		if(replConfType == ReplConfType.ACK){
			return false;
		}
		return true;
	}

	@Override
	protected void doRequest(Channel channel){
		
		
		boolean logRead = true, logWrite = true;
		
		if(replConfType == ReplConfType.ACK){
			logWrite = false;
		}
		
		RequestStringParser request = new RequestStringParser(logRead, logWrite, getName(), replConfType.toString(), argu);
		writeAndFlush(channel, request.format());
	}

	public enum ReplConfType{
		
		LISTENING_PORT("listening-port"),
		CAPA("capa"),
		ACK("ack");
		
		
		private String command;
		ReplConfType(String command){
			this.command = command;
		}

		@Override
		public String toString() {
			return command;
		}
	}
}
