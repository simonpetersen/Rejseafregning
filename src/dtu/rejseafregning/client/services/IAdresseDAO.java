package dtu.rejseafregning.client.services;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("adresseservice")
public interface IAdresseDAO extends RemoteService {
	public String getByNavn(String postnr) throws Exception;
	public List<String> getVejNavne(String postnr, String indtast) throws Exception;
	public List<String> getHusnumre(String postnr, String vejnavn) throws Exception;
	public List<String> getEtageListe(String postnr, String husnr, String vejnavn) throws Exception;
	public List<String> getEtageListe(String postnr, String husnr, String etage, String vejnavn) throws Exception;
}
