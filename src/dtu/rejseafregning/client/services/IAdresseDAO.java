package dtu.rejseafregning.client.services;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("adresseservice")
public interface IAdresseDAO extends RemoteService {
	public String getByNavn(String postnr) throws Exception;
	public List<String> getVejNavne(String postnr, String indtast) throws Exception;

}
