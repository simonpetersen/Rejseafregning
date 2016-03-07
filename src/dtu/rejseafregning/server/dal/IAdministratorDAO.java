package dtu.rejseafregning.server.dal;


import java.util.List;
import dtu.rejseafregning.shared.DALException;

public interface IAdministratorDAO {

	AdministratorDTO getAdministrator(int administratorID) throws DALException;
	List<AdministratorDTO> getAdministratorList() throws DALException;
	void createAdministrator(AdministratorDTO administrator) throws DALException;
	void updateAdministrator(AdministratorDTO administrator) throws DALException;
	void deleteAdministrator(AdministratorDTO administrator) throws DALException;
}
