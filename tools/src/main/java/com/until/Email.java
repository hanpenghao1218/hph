package com.until;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import com.impl.CheckImpl;
import com.type.BaseData;

public class Email {
	public static String sendUser;
	public static String sendPwd;
	public static boolean send;

	public static void init(BaseData baseData) {
		sendUser = baseData.getSendUser();
		sendPwd = baseData.getSendPwd();
		send = baseData.isSend();
	}

	/**
	 * 发送带附件的邮件
	 * 
	 * @param receive  收件人
	 * @param subject  邮件主题
	 * @param msg      邮件内容
	 * @param filename 附件地址
	 * @return
	 * @throws GeneralSecurityException
	 */
	public static void sendMail(String[] receives, String subject, String msg, String... filename) throws Exception {
		if (send) {
			CheckImpl.Log.info("开始发送邮件");
			// 1.创建邮件对象
			Properties properties = new Properties();
			properties.put("mail.smtp.host", "smtp.tongshugene.com"); // 指定SMTP服务器
			properties.put("mail.smtp.auth", "true"); // 指定是否需要SMTP验证
			Session session = Session.getInstance(properties);
			MimeMessage message = new MimeMessage(session);
			System.setProperty("mail.mime.splitlongparameters", "false");// 设置了附件名过长问题
			/*
			 * 2.设置发件人 其中 InternetAddress 的三个参数分别为: 邮箱, 显示的昵称(只用于显示, 没有特别的要求), 昵称的字符集编码
			 */
			message.setFrom(new InternetAddress(sendUser));
			/*
			 * 3.设置收件人 To收件人 CC 抄送 BCC密送
			 */
			Address[] addresses = new Address[receives.length];
			for (int i = 0; i < receives.length; i++) {
				CheckImpl.Log.info(receives[i]);
				addresses[i] = new InternetAddress(receives[i]);
			}
			message.setRecipients(MimeMessage.RecipientType.TO, addresses);

			/* 4.设置标题 */
			message.setSubject(subject);

			/* 5.设置邮件正文 */
			// 创建文本节点
			// 一个Multipart对象包含一个或多个bodypart对象，组成邮件正文
			MimeMultipart multipart = new MimeMultipart();

			MimeBodyPart text = new MimeBodyPart();
			text.setContent(msg, "text/html;charset=UTF-8");
			multipart.addBodyPart(text);

			// 创建附件节点 读取本地文件,并读取附件名称
			for (String string : filename) {
				MimeBodyPart file = new MimeBodyPart();
				DataHandler dataHandler = new DataHandler(new FileDataSource(string));
				file.setDataHandler(dataHandler);
				file.setFileName(MimeUtility.encodeText(dataHandler.getName()));
				multipart.addBodyPart(file);
			}

			multipart.setSubType("mixed");// 混合关系
			message.setContent(multipart);
			message.saveChanges();
			Transport transport = session.getTransport("smtp");
			transport.connect("smtp.tongshugene.com", sendUser, sendPwd);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
			CheckImpl.Log.info("邮件发送完成");
		}
	}

	public static void main(String[] args) throws Exception {
		String[] str = { "hanpenghao@tongshugene.com" }, file = { "pom.xml", "pom.xml" };
		BaseData baseData = new BaseData();
		baseData.sendUser = "ZHshare@tongshugene.com";
		baseData.sendPwd = "ZHsh@2021";
		baseData.send = false;
		init(baseData);
		sendMail(str, "比对差异", "有差异，见附件", file);
	}

}