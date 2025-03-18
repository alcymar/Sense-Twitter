package graphics;


import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.*;

import core.Tweets;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;


public class Frame_menu {
	private ImageIcon login = new ImageIcon(ClassLoader.getSystemResource("icons/lb_login.png"));
	private ImageIcon login_s = new ImageIcon(ClassLoader.getSystemResource("icons/lb_login_s.png"));
	
	private ImageIcon tweetar = new ImageIcon(ClassLoader.getSystemResource("icons/lb_tweet.png"));
	private ImageIcon timeline = new ImageIcon(ClassLoader.getSystemResource("icons/lb_timeline.png"));
    //
	private ImageIcon import_data = new ImageIcon(ClassLoader.getSystemResource("icons/lb_import_data.png"));
	private ImageIcon trends = new ImageIcon(ClassLoader.getSystemResource("icons/lb_trends.png"));
	private ImageIcon wordcloud = new ImageIcon(ClassLoader.getSystemResource("icons/lb_wordcloud.png"));
	//
	private ImageIcon feeling = new ImageIcon(ClassLoader.getSystemResource("icons/lb_feeling.png"));
    private ImageIcon settings = new ImageIcon(ClassLoader.getSystemResource("icons/lb_settings.png"));
   // private ImageIcon wordcloud = new ImageIcon(ClassLoader.getSystemResource("icons/lb_wordcloud.png"));
	
	
	private JFrame jf_sense_main;
	
	private JButton jb_twitter_login;
	private JButton jb_twitter_tweetar;
	private JButton jb_twitter_timeline;
	//
	private JButton jb_dm_import;
	private JButton jb_twitter_trends;
	private JButton jb_wordCloud;
	//
	private JButton jb_feeling;
	private JButton jb_settings;
	private JButton jb_exit;
	private JButton jb_search_hashtags;

	// recebe as credenciais e informacoes da conta do twitter
	private twitter4j.Twitter twit;
	private ConfigurationBuilder cf;
    //
	private Thread thread_update_timeline;
	
	public Frame_menu(ConfigurationBuilder configuracao) {
        this.cf = configuracao;
		
		jf_sense_main = new JFrame("Sense Twitter");
		jf_sense_main.setSize(470,190);
		jf_sense_main.setResizable(false);
		jf_sense_main.setLayout(null);

		jb_twitter_login = new JButton(login);
		jb_twitter_tweetar = new JButton(tweetar);
		jb_twitter_timeline = new JButton(timeline);
		jb_search_hashtags = new JButton("Lupa");
		//
		jb_dm_import = new JButton(import_data);
		jb_twitter_trends  = new JButton(trends);
		jb_wordCloud = new JButton(wordcloud);
        //
		jb_feeling   = new JButton(feeling);
		jb_settings  = new JButton(settings);
		jb_exit      = new JButton("Sair");

		jf_sense_main.add(jb_twitter_login);
		jf_sense_main.add(jb_twitter_tweetar);
		jf_sense_main.add(jb_twitter_timeline);
		//
		jf_sense_main.add(jb_search_hashtags);
		jf_sense_main.add(jb_twitter_trends);
		jf_sense_main.add(jb_wordCloud);
		//
//		jf_sense_main.add(jb_feeling);
//		jf_sense_main.add(jb_settings);
//		jf_sense_main.add(jb_exit);

		
		jb_twitter_trends.setBounds(50, 30, 96,96);
		jb_twitter_login.setBounds(5, 125, 32,20);
		jb_twitter_tweetar.setBounds(190,30,96,96);
		jb_twitter_timeline.setBounds(330,30,96,96);
		jb_search_hashtags.setBounds(45, 125, 32,20);

		jf_sense_main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		jb_twitter_trends.addMouseListener(new MouseAdapter (){
			public void mouseClicked(MouseEvent me){
				try {
					Trends trends = twit.getPlaceTrends(1);
			        int count = 0;
			        for (Trend trend : trends.getTrends()) {
			            if (count < 10) {
			                System.out.println(trend.getName());
			                count++;
			            }
			        }
			        jb_twitter_login.setIcon(login_s);
					
				}catch(TwitterException t) {}		
        	}
		});
		
		
		jb_twitter_timeline.addMouseListener(new MouseAdapter (){
			public void mouseClicked(MouseEvent me){
				try {
					Tweets twitter_timeline = new Tweets(twit);
					twitter_timeline.timeline();
				}catch(TwitterException t) {}		
        	}
		});
		
		
		jb_twitter_login.addMouseListener(new MouseAdapter (){
			public void mouseClicked(MouseEvent me){
				try {
					
					Connect_Twitter();
					
					jb_twitter_login.setIcon(login_s);	
					
				}catch(TwitterException t) {}		
        	}
		});
		
		
		jb_search_hashtags.addMouseListener(new MouseAdapter (){
			public void mouseClicked(MouseEvent me){
				try {
					
					String hashTag = JOptionPane.showInputDialog("Informe a HashTag");
					
				    Query query = new Query(hashTag);
				    QueryResult result = twit.search(query);
				    for (Status status : result.getTweets()) {
				        System.out.println("@" + status.getText());
				    }		      
					
				}catch(TwitterException t) {}		
        	}
		});
		

		jb_twitter_tweetar.addMouseListener(new MouseAdapter (){
			public void mouseClicked(MouseEvent me){
				try {
			
					String texto_tweet = JOptionPane.showInputDialog("O que Você esta pensando? ");
					
					Tweets twitter_tweetar = new Tweets(twit);
					twitter_tweetar.send_tweet(texto_tweet);
				}catch(TwitterException t) {}		
        	}
		});

	}
	
	public void Connect_Twitter() throws TwitterException{
		TwitterFactory tf = new TwitterFactory(this.cf.build());
		this.twit = tf.getInstance();
	}


	public void Open_Frame() {
		this.jf_sense_main.setVisible(true);
	}
	
	public void Update_Timeline_Twitter()  {
		new Thread() {
			
			@Override
			public void run() {
				Tweets twitter_timeline = new Tweets(twit);
				
			}
			
		}.start();
		
	}

	

}
