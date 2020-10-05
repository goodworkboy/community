package com.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.community.dto.AccessTokenDTO;
import com.community.dto.GithubUser;
import com.community.provider.GithubProvider;

@Controller
public class AuthorizeController {
	@Autowired
	private GithubProvider githubProvider;
	@Value("${github.client.id}")
	private String clientId;
	@Value("${github.client.secret}")
	private String clientSecret;
	@Value("${github.redirect.url}")
	private String redirectUrl;
	
	@GetMapping("/callback")
	
	public String callback(@RequestParam(value="code")String code,
			@RequestParam("state")String state) {
		AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
		accessTokenDTO.setCode(code);
		accessTokenDTO.setClient_id(clientId);
		accessTokenDTO.setState(state);
		accessTokenDTO.setClient_secret(clientSecret);
		accessTokenDTO.setRedirect_uri(redirectUrl);
		String accessToken=githubProvider.getAccessToken(accessTokenDTO);
		GithubUser user=githubProvider.getUser(accessToken);
		System.out.println(user.getName());
		return "index";
	}
}
