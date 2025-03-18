import java.awt.Color;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import graphics.Frame_menu;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

public class Aplication {
	

	public static void main(String[] args)  throws TwitterException{
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {//Oliva
					UIManager.setLookAndFeel(info.getClassName());
					UIManager.put("nimbusBase", new Color(255,255,255));
					UIManager.put("nimbusBlueGrey", new Color(255,255,255));
					UIManager.put("control", new Color(255,255,255));
				}	
			}	
		}catch (Exception e) {}
		
		ConfigurationBuilder cf = new ConfigurationBuilder();
		
		cf.setDebugEnabled(false);
		cf.setOAuthConsumerKey("");
		cf.setOAuthConsumerSecret("");
		cf.setOAuthAccessToken("");
		cf.setOAuthAccessTokenSecret("");
		
		
		Frame_menu f = new Frame_menu(cf);
		f.Open_Frame();
	}
	
	

}
