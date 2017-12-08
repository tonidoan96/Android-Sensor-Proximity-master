package app.akexorcist.sensor_proximity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.widget.TextView;

public class Main extends Activity {
	TextView textProximity;
	SensorManager sensorManager;
	Sensor sensor;
 
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
 
		sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
		
		textProximity = (TextView) findViewById(R.id.textProximity);
	}
 
	public void onResume() {
		super.onResume();
		sensorManager.registerListener(proxiListener, sensor,
				SensorManager.SENSOR_DELAY_NORMAL);
	}
 
	public void onStop() {
		super.onStop();
		sensorManager.unregisterListener(proxiListener);
	}

	public SensorEventListener proxiListener = new SensorEventListener() {
		public void onAccuracyChanged(Sensor sensor, int acc) { }
 
		public void onSensorChanged(SensorEvent event) {
			float proximity = event.values[0];
			textProximity.setText("Proximity : " + (int)proximity);
		}
	};
}