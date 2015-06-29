package com.triggerinnovations.movies;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import android.widget.TextView;

public class SlideShow extends Activity{
    private TextView txtStatus;
    private ImageView imageView;
    int i=0;
    static int img;
    int imgid[]={R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.d,R.drawable.e,R.drawable.f,R.drawable.g,R.drawable.h,R.drawable.ii,R.drawable.j};
    RefreshHandler refreshHandler=new RefreshHandler();
   
    
    class RefreshHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            SlideShow.this.updateUI();
        }
        public void sleep(long delayMillis){
            this.removeMessages(0);
            sendMessageDelayed(obtainMessage(0), delayMillis);
        }
    };
    public void updateUI(){
       // int currentInt=Integer.parseInt((String)txtStatus.getText())+10;
       // if(currentInt<=100){
            refreshHandler.sleep(2000);
            //txtStatus.setText(String.valueOf(currentInt));
            if(i<imgid.length){
            	
            	 imageView.setImageResource(imgid[i]);
                
                // imageView.setPadding(left, top, right, bottom);
                i++;
            }
            else
            {
            	i=0;
            }
        }
   // }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movieui);
        this.txtStatus=(TextView)this.findViewById(R.id.textView1);
        this.imageView=(ImageView)this.findViewById(R.id.imageView);
        updateUI();
    }

}
