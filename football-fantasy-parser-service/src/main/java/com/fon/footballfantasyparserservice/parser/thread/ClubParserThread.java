package com.fon.footballfantasyparserservice.parser.thread;

import java.util.concurrent.Callable;

import com.fon.footballfantasyparserservice.domain.Club;
import com.fon.footballfantasyparserservice.parser.ClubPageHtmlParser;

public class ClubParserThread implements Callable<Club>{
	
	private final ClubPageHtmlParser clubParser = new ClubPageHtmlParser();
	private String clubUrl;
	
	public ClubParserThread(String teamUrl) {
		this.clubUrl = teamUrl;
	}

	@Override
	public Club call() throws Exception {
		return clubParser.parse(clubUrl);
	}

}
