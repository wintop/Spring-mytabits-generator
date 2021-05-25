package net.hyjuki.smgen.controller;

import net.hyjuki.smgen.base.common.HjkResponse;
import net.hyjuki.smgen.base.common.MessageData;
import net.hyjuki.smgen.model.UserAccount;
import net.hyjuki.smgen.service.UserAccountService;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user/")
public class UserAccountController {
	private static final Logger logger = LoggerFactory.getLogger(UserAccountController.class);
	@Autowired
	private UserAccountService userAccountService;

	@RequestMapping("get")
	public HjkResponse getUserAccount(Long id) {
		logger.info("====getUserAccount(get), id: {}", id);

		if (id == null) {
			return HjkResponse.fail(MessageData.ERROR_PARAM_NULL);
		}
		return HjkResponse.success(userAccountService.get(id));
	}

	@RequestMapping("list")
	public HjkResponse getUserAccountList(@RequestBody UserAccount userAccount) {
		logger.info("====getUserAccountList(list), userAccount: {}", userAccount);

		if (userAccount == null) {
			return HjkResponse.fail(MessageData.ERROR_PARAM_NULL);
		}
		return HjkResponse.success(userAccountService.find(userAccount));
	}

	@RequestMapping("add")
	public HjkResponse addUserAccount(@RequestBody UserAccount userAccount) {
		logger.info("====addUserAccount(add), userAccount: {}", userAccount);

		if (userAccount == null) {
			return HjkResponse.fail(MessageData.ERROR_PARAM_NULL);
		}
		return HjkResponse.success(userAccountService.save(userAccount));
	}

	@RequestMapping("edit")
	public HjkResponse editUserAccount(@RequestBody UserAccount userAccount) {
		logger.info("====editUserAccount(edit), userAccount: {}", userAccount);

		if (userAccount == null) {
			return HjkResponse.fail(MessageData.ERROR_PARAM_NULL);
		}
		return HjkResponse.success(userAccountService.update(userAccount));
	}

	@RequestMapping("edit_status")
	public HjkResponse editUserAccountStatus(Long id, Byte status) {
		logger.info("====editUserAccountStatus(edit_status), id: {}, status: {}", id, status);

		if (id == null || status == null) {
			return HjkResponse.fail(MessageData.ERROR_PARAM_NULL);
		}

		UserAccount userAccount = new UserAccount();
		userAccount.setId(id);
		userAccount.setStatus(status);
		return HjkResponse.success(userAccountService.update(userAccount));
	}

	@RequestMapping("login")
	public HjkResponse login(String username, String password) {
		logger.info("===========login user: {}, pass: {}", username, password);

		if (username == null) {
			return HjkResponse.fail(MessageData.ERROR_NO_USERNAME);
		}
		if (password == null) {
			return HjkResponse.fail(MessageData.ERROR_NO_PASSWORD);
		}

		boolean rememberMe = false;
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
		try {
			subject.login(token);
		} catch (UnknownAccountException uae) {
			logger.info("该用户不存在：" + token.getPrincipal());
			return HjkResponse.fail(MessageData.ERROR_USERNAME_PASSWORD);
		} catch (IncorrectCredentialsException ice) {
			logger.info("该用户账户 " + token.getPrincipal() + " 密码不正确!");
			return HjkResponse.fail(MessageData.ERROR_USERNAME_PASSWORD);
		} catch (LockedAccountException lae) {
			logger.info("用户 " + token.getPrincipal() + " 被锁定.  " + "请联系管理员解锁.");
			return HjkResponse.fail(MessageData.ERROR_USER_LOCKED);
		}
		System.out.println(subject.getPrincipal());

		logger.info("====== response: {}", subject.getSession().getId().toString());
		return HjkResponse.success(subject.getSession().getId().toString());
	}

	/**
	 * 获取用户信息
	 */
	@RequestMapping("info")
	@ResponseBody
	public HjkResponse getUserInfo() {
		logger.info("======getUserInfo(info) no param");

		Subject subject = SecurityUtils.getSubject();
		UserAccount user = (UserAccount) subject.getPrincipal();
		if (user == null) {
			return HjkResponse.fail(MessageData.ERROR_NO_USER_AUTH);
		}

		return HjkResponse.success(user);
	}

	/**
	 * 获取用户信息
	 */
	@RequestMapping("logout")
	@ResponseBody
	public HjkResponse logout(String token) {
		logger.info("====logout, token: {}", token);
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return HjkResponse.success();
	}
}