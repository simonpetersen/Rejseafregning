package dtu.rejseafregning.server.dal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import dtu.rejseafregning.client.services.IAdresseDAO;

public class AdresseDAO extends RemoteServiceServlet implements IAdresseDAO {
	private static final long serialVersionUID = 1L;
	private JSONArray array;
	private JSONObject obj;
	private String url;
	
	public AdresseDAO () { }
	
	@Override
	public String getByNavn(String postnr) throws Exception {
		url = "http://dawa.aws.dk/postnumre/autocomplete?q="+postnr;
		array = readJsonFromUrl(url);
		if (array.length() > 0) {
			obj = array.getJSONObject(0);
			return new JSONObject(obj.getString("postnummer")).getString("navn");
		}
		return null;
	}

	@Override
	public List<String> getVejNavne(String postnr, String indtast) throws Exception {
		url = "http://dawa.aws.dk/vejnavne/autocomplete?q="+indtast+"&postnr="+postnr;
		List<String> list = new ArrayList<String>();
		array = readJsonFromUrl(url);
		for (int i = 0; i < array.length(); i++) {
			obj = array.getJSONObject(i);
			obj = obj.getJSONObject("vejnavn");
			list.add(obj.getString("navn"));
		}
		return list;
	}
	
	@Override
	public List<String> getHusnumre(String postnr, String vejnavn) throws Exception {
		url = "http://dawa.aws.dk/adgangsadresser?vejnavn="+vejnavn+"&postnr="+postnr;
		List<String> list = new ArrayList<String>();
		array = readJsonFromUrl(url);
		for (int i = 0; i < array.length(); i++) {
			obj = array.getJSONObject(i);
			list.add(obj.getString("husnr"));
		}
		return list;
	}
	
	public List<String> getEtageListe(String postnr, String husnr, String vejnavn) throws Exception {
		url = "http://dawa.aws.dk/adresser?vejnavn="+vejnavn+"&husnr="+husnr+"&postnr="+postnr;
		List<String> list = new ArrayList<String>();
		array = readJsonFromUrl(url);
		for (int i = 0; i < array.length(); i++) {
			obj = array.getJSONObject(i);
			String etage = obj.getString("etage"); 
			if (etage != null) list.add(etage);
		}
		return list;
	}
	
	public List<String> getEtageListe(String postnr, String husnr, String etage, String vejnavn) throws Exception {
		url = "http://dawa.aws.dk/adresser?vejnavn="+vejnavn+"&husnr="+husnr+"&etage="+etage+"&postnr="+postnr;
		List<String> list = new ArrayList<String>();
		array = readJsonFromUrl(url);
		for (int i = 0; i < array.length(); i++) {
			obj = array.getJSONObject(i);
			list.add(obj.getString("dør"));
		}
		return list;
	}
	
	private JSONArray readJsonFromUrl(String url) throws IOException, JSONException {
	    InputStream is = new URL(url).openStream();
	    BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
	    String jsonText = readAll(rd);
	    JSONArray array = new JSONArray(jsonText);
	    return array;
	  }
	
	private static String readAll(BufferedReader rd) throws IOException {
	    StringBuilder sb = new StringBuilder();
	    String nl = rd.readLine();
	    while (nl != null) {
	      sb.append(nl + "\n");
	      nl = rd.readLine();
	    }
	    return sb.toString();
	}

}
