package dtu.rejseafregning.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

import dtu.rejseafregning.client.ui.MainView;
import dtu.rejseafregning.client.ui.MainView2;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Rejseafregning implements EntryPoint {
		
	public void onModuleLoad() {
		MainView2 mView = null;
		try {
			mView = new MainView2();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RootPanel.get().add(mView);
	}
}
