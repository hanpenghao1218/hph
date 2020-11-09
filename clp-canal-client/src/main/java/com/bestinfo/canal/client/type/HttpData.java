package com.bestinfo.canal.client.type;

import java.io.Serializable;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "http-data")
public class HttpData implements Serializable {
	private static final long serialVersionUID = 1L;
	/** 请求ip */
	public String ip;
	/** 超时时间 */
	public int timeOut;
	/** 渠道编号 */
	public String channelCode;
	/** 渠道密码 */
	public String password;
	/** 公钥串 */
	public String publicKeyStr;
	/** 私钥串 */
	public String privateKeyStr;
	/** 文件下载地址 */
	public String downloadUrl = "";
	/** 本地保存路径 */
	public String path = "";
	/** 签名开关 */
	public boolean sign = true;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getTimeOut() {
		return timeOut;
	}

	public void setTimeOut(int timeOut) {
		this.timeOut = timeOut;
	}

	public String getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPublicKeyStr() {
		return publicKeyStr;
	}

	public void setPublicKeyStr(String publicKeyStr) {
		this.publicKeyStr = publicKeyStr;
	}

	public String getPrivateKeyStr() {
		return privateKeyStr;
	}

	public void setPrivateKeyStr(String privateKeyStr) {
		this.privateKeyStr = privateKeyStr;
	}

	public String getDownloadUrl() {
		return downloadUrl;
	}

	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public boolean isSign() {
		return sign;
	}

	public void setSign(boolean sign) {
		this.sign = sign;
	}
}