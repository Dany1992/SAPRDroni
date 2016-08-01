package it.uniroma2.sapr.persistence;

/**
 * Questa classe permette di ottenere una classe per scrivere su uno specifico db
 * se {1 ottieni la classe per il db Mysql}
 * Molto facilmente si puo aggiungere un nuovo db 
 * 
 * @author Danilo Butrico
 * @version 1.0
 * @mail dbutricod@gmail.com
 *
 */
public abstract class DAOFactory {
		 
	// List of DAO types supported by the factory
	public static final int MYSQL = 1;
		 
	public abstract PilotDAO getPilotDAO();
	public abstract SaprDAO getSaprDAO();
        public abstract FlightPlanDAO getFlightPlanDAO(); 

		  
	/**
	 * Questo metodo permette di ottentere una classe per scrivere su un db, attraverso un intero
	 * che gli viene passato esso sceglie quale classe selezionare
	 * 
	 * @param whichFactory indica quale db si vuole usare per scrivere
	 * @return a seconda del case una classe per scrivere su db
	 */
	public static DAOFactory getDAOFactory(int whichFactory) {
		switch (whichFactory) {
			case MYSQL: 
				return new MySQLDbDAOFactory();
		    default           : 
		        return null;
		}
	}
}
