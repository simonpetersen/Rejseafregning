package dtu.rejseafregning.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

import dtu.rejseafregning.client.ui.MainView;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Rejseafregning implements EntryPoint {
		
	public void onModuleLoad() {
		MainView mView = null;
		try {
			mView = new MainView();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RootPanel.get().add(mView);
	}
}
