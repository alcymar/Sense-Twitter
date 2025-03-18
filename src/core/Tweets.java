package core;

import java.util.List;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

public class Tweets {
	private String tweet;
	private twitter4j.Twitter twit;
	
	public Tweets(twitter4j.Twitter twitter_objeto, String tweet_p ) {
		this.tweet = tweet;
		this.twit = twitter_objeto;
	}
	
	public Tweets(twitter4j.Twitter twitter_objeto) {
		this.twit = twitter_objeto;
	}
	
	public String Show_tweet() {
		return this.tweet;
	}
	
	public void send_tweet(String tweet_p ) throws TwitterException{
		
		twit.updateStatus(tweet_p);
		
		
		System.out.println("Tweet Publicado");
	}
	
	public void timeline() throws TwitterException{
		
		//
		List<Status> status_timeline = twit.getHomeTimeline();
		
		//exibe dados
		String dados_json = "";
		for(Status st : status_timeline) {
			System.out.println("Usuario : "+st.getUser().getScreenName()+" Texto : "+st.getText());
		}
	}
	
}
