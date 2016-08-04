package com.yzz.diary.email;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@Component
public class EmailSender {
	private static Logger logger = LoggerFactory.getLogger(EmailSender.class);
	private static final String DEFAULT_ENCODING = "utf-8";
	private MimeMessage mmg;
	private MimeMessageHelper helper;
	private Template template;
	private Configuration config;
	private static final String emailFrom = "skzheng@163.com";
	private JavaMailSender mailSender;

	/**
	 * 发送纯文本邮件.
	 */
	public boolean sendMail(String userName, String content) {
		logger.info("send text email");
		try {
			init(userName);
			this.setTemplate("mailTemplate.ftl");
			content(userName, content);
			mailSender.send(mmg);
			logger.info("send success");
			return true;
		} catch (MessagingException e) {
			logger.debug("构造邮件失败", e);
			return false;
		} catch (Exception e) {
			logger.debug("发送邮件失败", e);
			return false;
		}
	}

	/**
	 * 使用模板发送html格式邮件
	 * 
	 * @param from
	 *            为null时使用系统默认邮件发送
	 * @param to
	 * @param subject
	 * @param content
	 *            和template对应的内容
	 * @param template
	 *            使用的模板
	 * @return
	 */
	public boolean sendMail(String from, String[] to, String subject,
			@SuppressWarnings("rawtypes") Map content, String template) {
		logger.info("send html email");
		try {
			this.setTemplate(template);
			mmg = mailSender.createMimeMessage();
			helper = new MimeMessageHelper(mmg, true, DEFAULT_ENCODING);
			helper.setTo(to);
			helper.setFrom(from == null ? emailFrom : from);
			helper.setSubject(subject);
			helper.setText(generateContent(content), true);
			mailSender.send(mmg);
			return true;
		} catch (MessagingException e) {
			logger.debug("构造邮件失败", e);
			return false;
		} catch (Exception e) {
			logger.debug("发送邮件失败", e);
			return false;
		}
	}

	/**
	 * 初始化
	 * */
	private void init(String userName) throws MessagingException {
		mmg = mailSender.createMimeMessage();
		helper = new MimeMessageHelper(mmg, true, DEFAULT_ENCODING);
		helper.setTo(userName);
		helper.setFrom(emailFrom);
		helper.setSubject("原记系统邮件");
	}

	/**
	 * 组成Content
	 * */
	private void content(String userName, String content)
			throws MessagingException {
		if (StringUtils.isNotBlank(content)) {
			String contentMail = generateContent(content);
			helper.setText(contentMail, true);
		}
	}

	/**
	 * 使用Freemarker生成html格式内容.
	 */
	private String generateContent(String content) throws MessagingException {
		@SuppressWarnings("rawtypes")
		Map context = Collections.singletonMap("content", content);
		return this.generateContent(context);
	}

	/**
	 * 使用Freemarker生成html格式内容.
	 */
	private String generateContent(@SuppressWarnings("rawtypes") Map context) throws MessagingException {

		try {
			return FreeMarkerTemplateUtils.processTemplateIntoString(template,
					context);
		} catch (IOException e) {
			logger.error("生成邮件内容失败, FreeMarker模板不存在", e);
			throw new MessagingException("FreeMarker模板不存在", e);
		} catch (TemplateException e) {
			logger.error("生成邮件内容失败, FreeMarker处理失败", e);
			throw new MessagingException("FreeMarker处理失败", e);
		}
	}

	/**
	 * Spring的MailSender.
	 */
	@Autowired
	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	/**
	 * 注入Freemarker引擎配置,构造Freemarker 邮件内容模板.
	 */
	@Autowired
	public void setFreemarkerConfiguration(Configuration freemarkerConfiguration)
			throws IOException {
		config = freemarkerConfiguration;
		template = freemarkerConfiguration.getTemplate("mailTemplate.ftl",
				DEFAULT_ENCODING);
	}

	public void setTemplate(String templateName) throws IOException {
		template = config.getTemplate(templateName, DEFAULT_ENCODING);
	}

}
