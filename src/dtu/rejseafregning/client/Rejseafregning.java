package dtu.rejseafregning.client;

import com.google.gwt.core.client.EntryPoint;

import dtu.rejseafregning.client.logic.Controller;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Rejseafregning implements EntryPoint {
		
	public void onModuleLoad() {
		new Controller();
	}
}
