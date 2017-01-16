package Tweets;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import twitter4j.*;
public class Searcher {
	public static void Searcher(String keys[],String filename){
		 TwitterStream twitterStream = new TwitterStreamFactory().getInstance();
		 String languages[]={"es","en"};
		 FilterQuery filterq = new FilterQuery();
	/*String[] keys = {"Telefonica","Telefónica","Abertis","Acciona","Acerinox","Aena",
				 "Amadeus","Arcelor Mital","Banco Popular","Banco de Sabadell","Banco de Santander"
				 ,"Bankia","Bankinter","CaixaBank","Cellnex","Enagas","Endesa","Ferrovial",
				 "Gamesa","Gas Natural","Grifols","Iberdrola","Inditex","Indra"
				 ,"Mapfre","Mediaset","Red Eléctica Española","Repsol","Viscofan"};
		*/
		
		 filterq.track(keys);
		 filterq.language(languages);
	        StatusListener listener = new StatusListener() {
	            @Override
	            public void onStatus(Status status) {
	                System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
	                BufferedWriter writer = null;
	                
	                try {
	                    
	                    File logtweets = new File(filename);

	                    // This will output the full path where the file will be written to...
	                    System.out.println(logtweets.getCanonicalPath());

	                    writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(logtweets,true),"UTF-8"));
	                   
	                    //writer.write(status.toString()+"\n");
	                    writer.write("@"+status.getUser().getName()+";"+status.getText() + ";"+"\n");
	                   
	                    
	                } catch (Exception e) {
	                    e.printStackTrace();
	                } finally {
	                    try {
	                        // Close the writer regardless of what happens...
	                        writer.close();
	                    } catch (Exception e) {
	                    }
	                }
	            
	            }

	            @Override
	            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
	                System.out.println("Got a status deletion notice id:" + statusDeletionNotice.getStatusId());
	            }

	            @Override
	            public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
	                System.out.println("Got track limitation notice:" + numberOfLimitedStatuses);
	            }

	            @Override
	            public void onScrubGeo(long userId, long upToStatusId) {
	                System.out.println("Got scrub_geo event userId:" + userId + " upToStatusId:" + upToStatusId);
	            }

	            @Override
	            public void onStallWarning(StallWarning warning) {
	                System.out.println("Got stall warning:" + warning);
	            }

	            @Override
	            public void onException(Exception ex) {
	                ex.printStackTrace();
	            }
	        };
	        twitterStream.addListener(listener);
	        twitterStream.filter(filterq);
	     
	    }
	}

