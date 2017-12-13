package com.example.compans;


import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Menu;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import com.example.compans.R;

public class MainActivity extends Activity {

	private SensorManager sensorManager;
	private ImageView compassImg;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		compassImg=(ImageView)findViewById(R.id.compass_img);
		
		
		sensorManager=(SensorManager)getSystemService(Context.SENSOR_SERVICE);
		
		/*Sensor magneticSensor=sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
		Sensor accelerometerSensor=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		
		sensorManager.registerListener(listener, magneticSensor,SensorManager.SENSOR_DELAY_GAME);
		sensorManager.registerListener(listener, accelerometerSensor,SensorManager.SENSOR_DELAY_GAME);*/
		
		sensorManager.registerListener(listener, sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),SensorManager.SENSOR_DELAY_GAME);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void onDestroy(){
		super.onDestroy();
		if(sensorManager!=null){
			sensorManager.unregisterListener(listener);
		}
	}
	private SensorEventListener listener=new SensorEventListener() {
		
/*		float[] accelerometerValues=new float[3];
		float[] magneticValues=new float[3];*/
		private float lastRotateDegree=0;
		
		
		/*public void onSensorChanged(SensorEvent event) {

			if(event.sensor.getType()==Sensor.TYPE_ACCELEROMETER){
				accelerometerValues=event.values.clone();
			}else if(event.sensor.getType()==Sensor.TYPE_MAGNETIC_FIELD){
				magneticValues=event.values.clone();
			}
			float[] R=new float[9];
			float[] values=new float[3];
			SensorManager.getRotationMatrix(R, null, accelerometerValues, magneticValues);
			SensorManager.getOrientation(R, values);
			
				
				float rotateDegree=-(float) event.values[0]; //Math.toDegrees(values[0]);
				
				if(Math.abs(rotateDegree-lastRotateDegree)>1){
					RotateAnimation animation=new RotateAnimation(lastRotateDegree,rotateDegree,
								Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
					animation.setFillAfter(true);
					animation.setDuration(200);
					compassImg.startAnimation(animation);
					lastRotateDegree=rotateDegree;
				}
				Log.d("MainActivity", "values[0] is"+Math.toDegrees(values[0]));
			
		}*/
		
		@Override
		public void onSensorChanged(SensorEvent event) {
			if(event.sensor.getType()==Sensor.TYPE_ORIENTATION){
				float degree=-(float)event.values[0];
				RotateAnimation animation=new RotateAnimation(lastRotateDegree,degree,
						Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
				animation.setFillAfter(true);
				animation.setDuration(200000);
				compassImg.startAnimation(animation);
				lastRotateDegree=degree;
			}
		}
		
		@Override
		public void onAccuracyChanged(Sensor arg0, int arg1) {
			// TODO Auto-generated method stub
			
		}
	};

}
