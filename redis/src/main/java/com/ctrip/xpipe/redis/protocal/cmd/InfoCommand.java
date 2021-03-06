package com.ctrip.xpipe.redis.protocal.cmd;

import com.ctrip.xpipe.exception.XpipeException;
import com.ctrip.xpipe.redis.protocal.protocal.RequestStringParser;

import io.netty.channel.Channel;

/**
 * @author marsqing
 *
 *         May 9, 2016 5:42:01 PM
 */
public class InfoCommand extends AbstractRedisCommand {

	private String args;

	/**
	 * @param channel
	 */
	public InfoCommand() {
		this("");
	}

	public InfoCommand(String args) {
		this.args = args;
	}

	@Override
	public String getName() {
		return "info";
	}

	@Override
	protected void doRequest(Channel channel) throws XpipeException {
		RequestStringParser requestString = new RequestStringParser(getName(), args);
		writeAndFlush(channel, requestString.format());
	}

}
