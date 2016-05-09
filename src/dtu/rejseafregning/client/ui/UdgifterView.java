package dtu.rejseafregning.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.binder.EventBinder;

import dtu.rejseafregning.client.events.AfslutRejseafregningEvent;
import dtu.rejseafregning.client.events.GetGemOgNaesteEvent;
import dtu.rejseafregning.client.ui.celltables.RejsedageCellTable;
import dtu.rejseafregning.client.ui.celltables.UdgifterCellTable;

public class UdgifterView extends Composite {

	private UdgifterCellTable udgifterTable;
	private RejsedageCellTable rejsedageTable;
	
	private static UdgifterViewUiBinder uiBinder = GWT.create(UdgifterViewUiBinder.class);
	
	@UiField Button afslut;
	@UiField Button tilfoej;
	@UiField VerticalPanel rejsedagePanel, udgifterPanel;

	interface UdgifterViewUiBinder extends UiBinder<Widget, UdgifterView> {
	}
	
	
	private final EventBus eventBus;
	interface MyEventBinder extends EventBinder<UdgifterView> {
	}
	private final MyEventBinder eventBinder = GWT.create(MyEventBinder.class);

	public UdgifterView(EventBus eventBus) {
		udgifterTable = new UdgifterCellTable(eventBus);
		rejsedageTable = new RejsedageCellTable(eventBus);
		initWidget(uiBinder.createAndBindUi(this));
		this.eventBus = eventBus;
		eventBinder.bindEventHandlers(this, eventBus);
		udgifterPanel.add(udgifterTable);
		rejsedagePanel.add(rejsedageTable);
	}

	@UiHandler("nyRejsedag")
	void onNyRejsedagClick(ClickEvent event) {
		udgifterTable.addNyUdgift();
	}
	
	@UiHandler("nyUdgift")
	void onNyUdgiftClick(ClickEvent event) {
		rejsedageTable.addNyRejsedag();
	}
	@UiHandler("afslut")
	void onAfslutClick(ClickEvent event) {
		eventBus.fireEvent(new AfslutRejseafregningEvent());
	}
}