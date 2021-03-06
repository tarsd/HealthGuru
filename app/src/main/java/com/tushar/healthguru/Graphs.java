package com.tushar.healthguru;

import org.achartengine.ChartFactory;
import org.achartengine.model.TimeSeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.content.Context;
import android.content.Intent;

public class Graphs {

	public Intent getIntent(Context context)
	{
	  int[] x = {1,2,3,4,5,6,7,8,9,10};
	  int[] y = {30,34,45,57,77,89,100,111,123,145};
	  TimeSeries series = new TimeSeries("Line1");
	  for(int i=0;i<x.length;i++)
	  {
		  series.add(x[i],y[i]);
	  }
	  
	  XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
	  dataset.addSeries(series);
	  
	  XYMultipleSeriesRenderer xRenderer = new XYMultipleSeriesRenderer();
	  XYSeriesRenderer renderer = new XYSeriesRenderer();
	  xRenderer.addSeriesRenderer(renderer);
	  Intent intent = ChartFactory.getLineChartIntent(context, dataset, xRenderer,"HELLO");
	  return intent;
	}
	
   
	
}
