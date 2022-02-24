package it.gestionecurricula.dao.esperienza;

import java.util.Date;
import java.util.List;

import it.gestionecurricula.dao.IBaseDAO;
import it.gestionecurricula.model.Esperienza;

public interface EsperienzaDAO extends IBaseDAO<Esperienza> {

	public List<Date> cercaEsperienzePassate(Esperienza input) throws Exception;
	
	public int aggiornaFineEsperienza(Esperienza input) throws Exception;

}
