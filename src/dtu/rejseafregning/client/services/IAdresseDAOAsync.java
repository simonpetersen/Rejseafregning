package dtu.rejseafregning.client.services;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface IAdresseDAOAsync {
	
	public void getByNavn(String postnr, AsyncCallback<String> result);
	public void getVejNavne(String postnr, String indtast, AsyncCallback<List<String>> callback);
	public void getHusnumre(String postnr, String vejnavn, AsyncCallback<List<String>> callback);
	public void getEtageListe(String postnr, String husnr, String vejnavn, AsyncCallback<List<String>> callback);
	public void getDoerListe(String postnr, String husnr, String etage, String vejnavn, AsyncCallback<List<String>> callback);
}
