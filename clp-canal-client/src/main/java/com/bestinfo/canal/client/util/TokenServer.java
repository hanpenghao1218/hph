package com.bestinfo.canal.client.util;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import redis.clients.jedis.ShardedJedis;

@Component("TokenServer")
public class TokenServer {
	private static final Logger Log = LoggerFactory.getLogger(TokenServer.class);
	public void setToken(ShardedJedis shardedJedis, JSONObject json) {
		try {
			if (shardedJedis == null) {
				Log.error("sharded Jedis is null");
			} else {
				if (json.getInteger("code") == 0) {
					shardedJedis.set(Tools.key, json.toJSONString());
				}
			}
		} finally {
			shardedJedis.close();
		}

	}

	public JSONObject getToken(ShardedJedis shardedJedis) {
		try {
			if (shardedJedis == null) {
				Log.error("sharded Jedis is null");
			} else {
				return JSONObject.parseObject(shardedJedis.get(Tools.key));
			}
		} finally {
			shardedJedis.close();
		}
		return null;
	}
}