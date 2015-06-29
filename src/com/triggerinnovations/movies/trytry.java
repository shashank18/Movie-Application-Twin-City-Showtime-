package com.triggerinnovations.movies;

import java.io.BufferedReader;
import java.io.BufferedWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;


public class trytry extends Activity implements Runnable {
	static int index1,img;
	  float rate;
	  
	  
	  
	  StringBuffer buffer;
		byte[] data;
	   String []hindi;
	   String folder,timedwd,timehbl,txt,movname,hub,dwd,details;
	   TextView filmname,detaild,detailh,detail;
	   ProgressDialog dialog1;
	   BufferedReader in,ind,inh;
	   HttpPost httppost;
	   HttpResponse response;
		HttpClient httpclient;
		InputStream inputStream;
		SharedPreferences app_preferences ;
		List<NameValuePair> nameValuePairs;

		String []hubtemp;
	  int hublen=0,dwdlen=0;
	  
	  
	  // String []hindi;
	//   String folder,timedwd,timehbl,txt;
	//   TextView filmname,detaild,detailh,detail;
	//   ProgressDialog dialog1;
	//   BufferedReader in,ind,inh;
	//@SuppressWarnings("static-access")
	//@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.movies);
       // Toast.makeText(getApplicationContext(),"PAGE", Toast.LENGTH_LONG).show();
        
    final String urls="http://10.0.2.2/";
    
    //    final String urls="http://ti.2itb.com/";
   //     final String urls="http://tii.2itb.com/"; 
    	  
        
        // final String urls="ti.vacau.com/Films/"; 
        //final String urla="http://ti.2itb.com";
        
        Button next=(Button) findViewById(R.id.next);
        Button back=(Button) findViewById(R.id.back);
        Button reating=(Button) findViewById(R.id.raiting);
        Button comment=(Button) findViewById(R.id.commen);
        
        
        
        
        //////////////////////////////////////////////////////////////////////
        reating.setOnClickListener(new OnClickListener() {
        	
        	public void onClick(View v) {
        		
        		//submit.setEnabled(true);
        		final Dialog dialog = new Dialog(trytry.this);
                dialog.setContentView(R.layout.amain);
                dialog.setTitle("Comment and Rate the movie");
                dialog.setCancelable(true);
                //there are a lot of settings, for dialog, check them all out!
                //now that the dialog is set up, it's time to show it    
                final RatingBar ratingBar_default = (RatingBar)dialog.findViewById(R.id.rate);

                final Button sen =(Button) dialog.findViewById(R.id.sen); 
               final EditText name =(EditText) dialog.findViewById(R.id.name);
               final EditText comment =(EditText) dialog.findViewById(R.id.comt);
               
                
               sen.setOnClickListener(new OnClickListener() 
               {
					
					public void onClick(View v) 
					{
						// TODO Auto-generated method stub
						if((name.getText().toString().length()==0))
						{
							Toast.makeText(trytry.this, "Please Enter your name",Toast.LENGTH_LONG).show();
						}
						else if((comment.getText().toString().length()==0))
						{
							Toast.makeText(trytry.this, "Please Enter your comment",Toast.LENGTH_LONG).show();
						}
						else
						{	
						/////////////////////////////////////////////////////////
							
							try
							{
							httpclient = new DefaultHttpClient();
							httppost = new HttpPost("http://10.0.2.2/review.php");
					        // Add your data
									nameValuePairs = new ArrayList<NameValuePair>(4);
									nameValuePairs.add(new BasicNameValuePair("Moviename", hindi[index1]));
									nameValuePairs.add(new BasicNameValuePair("Rating", rate+""));
									nameValuePairs.add(new BasicNameValuePair("Comment",comment.getText().toString()));
									nameValuePairs.add(new BasicNameValuePair("Cname",name.getText().toString()));
									//nameValuePairs.add(new BasicNameValuePair("Password", pass.trim()));
									httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

					        // Execute HTTP Post Request
									response = httpclient.execute(httppost);
									inputStream = response.getEntity().getContent();
									data = new byte[256];
									buffer = new StringBuffer();
								
								}
								catch (Exception e)
								{
								//	Toast.makeText(Lect_sims_login1.this, "error"+e.toString(), Toast.LENGTH_LONG).show();
								}
							
							
					///////////////////////////////////////////////////////////////////////		
							
							
							
							
							
							
							
						
					    Toast.makeText(trytry.this, "name= "+name.getText().toString()+" comment:"+comment.getText().toString() +"rating:"+rate,Toast.LENGTH_LONG).show();
						dialog.dismiss();
					}}
				});
                
         ratingBar_default.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener(){
            public void onRatingChanged(RatingBar ratingBar, float rating,boolean fromUser) 
            {
             // TODO Auto-generated method stub
            // ratingBar_Small.setRating(rating);
             //ratingBar_Indicator.setRating(rating);
            	rate=rating;
            	
             Toast.makeText(trytry.this, "rating:"+String.valueOf(rating),Toast.LENGTH_LONG).show();
            }});
             // TODO Auto-generated method stub
            	
                dialog.show();
            }	
        		
        	
        });


        
        comment.setOnClickListener(new OnClickListener() {
	
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		Bundle b=new Bundle();
		b.putString("mname", hindi[index1]);
		Intent i=new Intent();
		
		i.setClass(getApplicationContext(), Comments.class);
		i.putExtras(b);
		startActivity(i);
	}
}); 
        
        
        
    //////////////////////////////////////////////////////////////////////////////////////    
        
        
        
        if(img==4)
        {
        	img=0;
        }
        // Loader image - will be shown before loading image
        int loader = R.drawable.loader;
        Intent in1 = getIntent();
         hindi = in1.getStringArrayExtra("hindi");
      //  final int index=in1.getIntExtra("index",0);
        final int len=in1.getIntExtra("len",0);
        folder=in1.getStringExtra("folder");
         detail=(TextView) findViewById(R.id.dtl);
         detailh=(TextView) findViewById(R.id.hubli);
         detaild=(TextView) findViewById(R.id.dwd);
        
         filmname=(TextView) findViewById(R.id.filmname);
        
        
        // Imageview to show
        ImageView image = (ImageView) findViewById(R.id.image);
        ImageView image1 = (ImageView) findViewById(R.id.ti);
        // Image url
        //String image_url =  "http://10.0.2.2/"+folder+"/"+hindi[index1]+"/film.jpg";
        //String image_url1 =  "http://10.0.2.2/Adds/"+(img++)+".png";
        
     //   Toast.makeText(getApplicationContext(),image_url +"    "+index1+"   "+len  , Toast.LENGTH_LONG).show();
        //String image_url = "http://api.androidhive.info/images/sample.jpg";
        
    
    
//    String	txt = "http://10.0.2.2/"+folder+"/"+hindi[index1]+"/details.txt";
//	String	timehbl = "http://10.0.2.2/"+folder+"/"+hindi[index1]+"/hubli.txt";
//	String	timedwd = "http://10.0.2.2/"+folder+"/"+hindi[index1]+"/dwd.txt";
//	

    	//txt = urls+folder+"/"+hindi[index1]+"/details.txt";
		//timehbl = urls+folder+"/"+hindi[index1]+"/hubli.txt";
		//timedwd = urls+folder+"/"+hindi[index1]+"/dwd.txt";
//	Toast.makeText(getApplicationContext(),txt +"    "+timehbl+"   "+timedwd  , Toast.LENGTH_LONG).show();
	
        //Toast.makeText(getApplicationContext(),hindi[index1]  , Toast.LENGTH_LONG).show();
    	
	
	
	//Html html = null;
	
//	filmname.setText(hindi[index1]);
	
	filmname.setText(hindi[index1]);
	
 dialog1 = ProgressDialog.show(trytry.this, "Loading", "Please Wait..", true, false); 
	Thread thread = new Thread(trytry.this);
	thread.start();
	
	
	
	
	
	
	
	
	
	
	
//	
//	try 
//	{
//		URL url=new URL(txt);
//		
//		BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
//		
//		String str="";
//		while((str=in.readLine())!=null)
//		{
//			detail.append(str);
//		}
//		
//		in.close();
//		
//		URL urld=new URL(timedwd);
//		
//		BufferedReader ind = new BufferedReader(new InputStreamReader(urld.openStream()));
//		
//		String strd="";
//		while((strd=ind.readLine())!=null)
//		{
//			detaild.append(strd+"\n");
//		}
//		
//		in.close();
//		
//		URL urlh=new URL(timehbl);
//		
//		BufferedReader inh = new BufferedReader(new InputStreamReader(urlh.openStream()));
//		
//		String strh="";
//		while((strh=inh.readLine())!=null)
//		{
//			detailh.append(strh+"\n");
//		}
//		
//		in.close();
//	} catch (MalformedURLException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	} catch (IOException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//
//	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	next.setOnClickListener(new OnClickListener() {
		
		////@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			if(index1>=len-1)
			{
			index1=-1;
			}
			Bundle b1=new Bundle();
			b1.putStringArray("hindi", hindi);
			b1.putInt("index",++index1);
			b1.putInt("len",len);
			b1.putString("folder", folder);
			Intent i1=new Intent();
			i1.setClass(getApplicationContext(),trytry.class);
			i1.putExtras(b1);
			startActivity(i1);	
			finish();
		}
	});
	
	
    
	
	
	
	
	
	
	
	
	back.setOnClickListener(new OnClickListener(){
		//@Override
		public void onClick(View arg0) {
			//Toast.makeText(getApplicationContext(), index1+"", 1).show();
			// TODO Auto-generated method stub
			if(index1==0)
			{
			index1=len;
			}
			Bundle b1=new Bundle();
			b1.putStringArray("hindi", hindi);
			b1.putInt("index",--index1);
			b1.putString("folder", folder);
			b1.putInt("len",len);
			Intent i1=new Intent();
			i1.setClass(getApplicationContext(),trytry.class);
			i1.putExtras(b1);
			startActivity(i1);
			finish();
		}
	});
     
     String image_url = urls+folder+"/"+hindi[index1]+"/film.jpg";
     String image_url1 =  urls+"Adds/"+(img++)+".png";
     // ImageLoader class instance
     ImageLoader imgLoader = new ImageLoader(getApplicationContext());
     ImageLoader imgLoader1 = new ImageLoader(getApplicationContext());
     // whenever you want to load an image from url
     // call DisplayImage function
     // url - image url to load
     // loader - loader image, will be displayed before getting image
     // image - ImageView 
     imgLoader.DisplayImage(image_url, loader, image);
     imgLoader1.DisplayImage(image_url1, loader, image1);       
  
     
    }
	 //Override the onKeyDown method  
    //@Override  
    public boolean onKeyDown(int keyCode, KeyEvent event)  
    {  
        //replaces the default 'Back' button action  
        if(keyCode==KeyEvent.KEYCODE_BACK)  
        {  
            //do whatever you want the 'Back' button to do  
            //as an example the 'Back' button is set to start a new Activity named 'NewActivity'  
            this.startActivity(new Intent(trytry.this,first.class));
            finish();
        }  
        return true;  
    }
    
    
    
    
    ////////////////////////////////////////////////////////////////////////////////////////////
    public void run() {
		process();
		handler.sendEmptyMessage(0);
	  }

	private Handler handler = new Handler() {
        //@Override
        public void handleMessage(Message msg) {
                dialog1.dismiss();
                
                   dialog1.dismiss();
                
    			   detail.setText(details);
    			   
    			//in.close();
                ///hubtemp
    			 //  Toast.makeText(getApplicationContext(), hubtemp[1]+"--", Toast.LENGTH_LONG).show();
    			   //detailh.append(hubtemp+);
    			   detailh.append(hub);
    			
    				detaild.append(dwd);

                
    			
		} 
//                Bundle b1=new Bundle();
//                Intent i1=new Intent();
//                if(movies[0].equalsIgnoreCase("no"))
//                {
//                	i1.setClass(getApplicationContext(),NoMovies.class);
//        			//i1.putExtras(b1);
//        			startActivity(i1);	
//                }
//                else{
//    			b1.putStringArray("hindi", movies);
//    			b1.putInt("index", i);
//    			b1.putInt("len", i-1);
//    			b1.putString("folder",folders);
//    			i1.setClass(getApplicationContext(),trytry.class);
//    			i1.putExtras(b1);
//    			startActivity(i1);
//                }
//    			finish();
	    };
		

		
    public void process()
	{		
    	try
 		{
 			httpclient = new DefaultHttpClient();
 			httppost = new HttpPost("http://10.0.2.2/details.php");
     // Add your data
 			nameValuePairs = new ArrayList<NameValuePair>(1);
 			nameValuePairs.add(new BasicNameValuePair("Moviename",hindi[index1]));
 			//nameValuePairs.add(new BasicNameValuePair("Comment", comment.trim()));
        //nameValuePairs.add(new BasicNameValuePair("Rating", rat+""));
 			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

     // Execute HTTP Post Request
 			response = httpclient.execute(httppost);
 			inputStream = response.getEntity().getContent();
 			data = new byte[256];
 			buffer = new StringBuffer();
 			int len = 0;
 			while (-1 != (len = inputStream.read(data)) )
 			{
 				buffer.append(new String(data, 0, len));
 			}
 			inputStream.close();
 		
 		String str=buffer.toString();
 		String []temp=str.split("@#");
 		
 		//hubtemp=temp[1].split("&?");
 		
 		//Toast.makeText(getApplicationContext(), hubtemp[0]+"--"+hubtemp[1], Toast.LENGTH_LONG).show();
 		//hublen=hubtemp.length;
 		
 		movname=temp[0];
 		hub=temp[1];
 		dwd=temp[2];
 		details=temp[3];
 		}
 		catch (Exception e)
 		{
 		//	Toast.makeText(review.this, "error"+e.toString(), Toast.LENGTH_LONG).show();
 		}

	}
    
}
