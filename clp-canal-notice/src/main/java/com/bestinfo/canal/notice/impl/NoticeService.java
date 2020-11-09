package com.bestinfo.canal.notice.impl;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;

import javax.annotation.PostConstruct;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.bestinfo.canal.client.impl.InterfaceClient;
import com.bestinfo.canal.client.type.ErrorCode;
import com.bestinfo.canal.client.type.HttpData;
import com.bestinfo.canal.client.util.HttpClient;
import com.bestinfo.canal.client.util.Tools;
import com.bestinfo.canal.notice.mapper.NoticeMapper;
import com.bestinfo.canal.notice.type.BaseResp;

@RestController
public class NoticeService {
	private static final Logger Log = LoggerFactory.getLogger(NoticeService.class);

	@Autowired
	HttpData httpData;
	@Autowired
	NoticeMapper mapper;
	private static SimpleDateFormat formatTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static SimpleDateFormat formatDay = new SimpleDateFormat("yyyy-MM-dd");

	@PostConstruct
	public void init() {
		InterfaceClient.init(null, httpData);
	}

	@RequestMapping(value = "/notice", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public void notice(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			int len = request.getContentLength();
			byte[] req = new byte[len];
			try {
				ServletInputStream sis = request.getInputStream();
				sis.read(req, 0, len);
			} catch (Exception e) {
				e.printStackTrace();
			}
			JSONObject json = JSONObject.parseObject(new String(req));
			JSONObject data = json.getJSONObject("data");
			int notifyType = json.getIntValue("notifyType");
			switch (notifyType) {
			case 1:// 游戏新期通知
				Log.info("游戏新期通知：[" + data + "]");
				break;
			case 2:// 开奖公告通知
				Log.info("开奖公告通知：[" + data + "]");
				break;
			case 3:// 开奖号码通知
				Log.info("开奖号码通知：[" + data + "]");
				break;
			case 4:// 通告公告
				Calendar rightNow = Calendar.getInstance();
				rightNow.setTime(formatTime.parse(data.getString("issueTime")));
				rightNow.add(Calendar.MONTH, 1);
				String content = data.getString("content");
				if (mapper.insertCMS(data.getString("issueTime"), data.getString("title"), content.length(), content,
						notifyType, formatDay.format(rightNow.getTime())) == 1) {
					Log.info("通告公告：[" + data + "]入库成功");
				} else {
					Log.error("通告公告：[" + data + "]入库失败");
				}
				break;
			case 5:// 期中奖文件通知
				Log.info("期中奖文件通知：[" + data + "]");
				HttpClient.download(Tools.redirectUrl(data.getString("url")),
						Tools.downloadPathMap.get(notifyType) + getFileName(data.getString("url")));
				HttpClient.download(Tools.redirectUrl(data.getString("checkFileUrl")),
						Tools.downloadPathMap.get(notifyType) + getFileName(data.getString("checkFileUrl")));
				break;
			case 6:// 期销售对账文件通知
				Log.info("期销售对账文件通知：[" + data + "]");
				HttpClient.download(Tools.redirectUrl(data.getString("url")),
						Tools.downloadPathMap.get(notifyType) + getFileName(data.getString("url")));
				HttpClient.download(Tools.redirectUrl(data.getString("checkFileUrl")),
						Tools.downloadPathMap.get(notifyType) + getFileName(data.getString("checkFileUrl")));
				break;
			case 7:// 期兑奖文件通知
				Log.info("期兑奖文件通知：[" + data + "]");
				HttpClient.download(Tools.redirectUrl(data.getString("url")),
						Tools.downloadPathMap.get(notifyType) + getFileName(data.getString("url")));
				HttpClient.download(Tools.redirectUrl(data.getString("checkFileUrl")),
						Tools.downloadPathMap.get(notifyType) + getFileName(data.getString("checkFileUrl")));
				break;
			case 8:// 日销售对账文件通知
				Log.info("日销售对账文件通知：[" + data + "]");
				HttpClient.download(Tools.redirectUrl(data.getString("url")),
						Tools.downloadPathMap.get(notifyType) + getFileName(data.getString("url")));
				HttpClient.download(Tools.redirectUrl(data.getString("checkFileUrl")),
						Tools.downloadPathMap.get(notifyType) + getFileName(data.getString("checkFileUrl")));
			case 9:// 日兑奖文件通知
				Log.info("日兑奖文件通知：[" + data + "]");
				HttpClient.download(Tools.redirectUrl(data.getString("url")),
						Tools.downloadPathMap.get(notifyType) + getFileName(data.getString("url")));
				HttpClient.download(Tools.redirectUrl(data.getString("checkFileUrl")),
						Tools.downloadPathMap.get(notifyType) + getFileName(data.getString("checkFileUrl")));
				break;
			case 10:// 日弃奖文件通知
				Log.info("日弃奖文件通知：[" + data + "]");
				HttpClient.download(Tools.redirectUrl(data.getString("url")),
						Tools.downloadPathMap.get(notifyType) + getFileName(data.getString("url")));
				HttpClient.download(Tools.redirectUrl(data.getString("checkFileUrl")),
						Tools.downloadPathMap.get(notifyType) + getFileName(data.getString("checkFileUrl")));
				break;
			default:
				Log.info("other通知：[" + json + "]");
				break;
			}
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(new BaseResp().toJson());
		} catch (Exception e) {
			Log.error("通知异常", e);
		}
	}

	private String getFileName(String url) {
		return url.substring(url.lastIndexOf("/") + 1, url.length());
	}

	@RequestMapping(value = "/download", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public void download(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			int type = ServletRequestUtils.getIntParameter(request, "type");
			int gameId = ServletRequestUtils.getIntParameter(request, "gameId");
			String period = ServletRequestUtils.getStringParameter(request, "period");
			String date = ServletRequestUtils.getStringParameter(request, "date");
			int channel = ServletRequestUtils.getIntParameter(request, "channel");
			String[] fileName = Tools.getDownloadName(type, gameId, period, date, channel);
			Log.info("主动下载：" + Arrays.toString(fileName));
			if (HttpClient.download(Tools.downloadUrlMap.get(type) + fileName[0],
					Tools.downloadPathMap.get(type) + fileName[0])
					&& HttpClient.download(Tools.downloadUrlMap.get(type) + fileName[1],
							Tools.downloadPathMap.get(type) + fileName[1])) {
				response.getWriter().write(new BaseResp().toJson());
			} else {
				response.getWriter()
						.write(new BaseResp(ErrorCode.DOWNLOADERR.getCode(), ErrorCode.DOWNLOADERR.getMsg()).toJson());
			}
		} catch (Exception e) {
			Log.error("主动下载异常", e);
		}
	}
}