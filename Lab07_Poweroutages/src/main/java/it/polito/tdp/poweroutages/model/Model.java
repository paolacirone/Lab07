package it.polito.tdp.poweroutages.model;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.poweroutages.DAO.PowerOutageDAO;

public class Model {

	PowerOutageDAO podao;
	List<PowerOutages> soluzione;
	List<PowerOutages> blackOutAnni;

	public Model() {
		podao = new PowerOutageDAO();
		
	}

	public List<Nerc> getNercList() {
		return podao.getNercList();
	}

	public List<PowerOutages> getAllBlackoutAnni(int x, Nerc p) {
		return this.podao.getAllBlackoutAnni(x, p);
	}

	public List<PowerOutages> massimizzaSequenza(int x, int y, Nerc d) {

		soluzione = new ArrayList<PowerOutages>(); 
		
		List<PowerOutages> parziale = new ArrayList<PowerOutages>();

		int livello = 0;

		PowerOutageDAO p = new PowerOutageDAO();
		this.blackOutAnni = p.getAllBlackoutAnni(x, d);

		ricorsiva(parziale, livello, y);

		return soluzione;
	}

	private void ricorsiva(List<PowerOutages> parziale, int livello, int y) {

		// caso terminale
		if (calcolaPersoneCoinvolte(parziale) > calcolaPersoneCoinvolte(soluzione)) {
			soluzione = new ArrayList<PowerOutages>(parziale);
		}

		for (PowerOutages p : blackOutAnni) {
			if (verificaValida(p, parziale, y)) {
				parziale.add(p);
				ricorsiva(parziale, livello + 1, y);
				parziale.remove(parziale.size() - 1);
			}
		}
	}

	private boolean verificaValida(PowerOutages p, List<PowerOutages> parziale, int y) {

		int orarioTotale = 0;

		for (PowerOutages c : parziale) {
			orarioTotale += Duration.between(c.getDataInizio(), c.getDataFine()).toSecondsPart()/ 3600+
					+ Duration.between(c.getDataInizio(), c.getDataFine()).toMinutesPart() / 60
					+ Duration.between(c.getDataInizio(), c.getDataFine()).toHoursPart();
		
		}

		
		int orarioP = Duration.between(p.getDataInizio(), p.getDataFine()).toSecondsPart() / 3600
				+ Duration.between(p.getDataInizio(), p.getDataFine()).toMinutesPart() / 60
				+ Duration.between(p.getDataInizio(), p.getDataFine()).toHoursPart();

		//System.out.println(orarioTotale + orarioP);
		if (orarioTotale + orarioP < y)
			return true;

		return false;
	}

	private int calcolaPersoneCoinvolte(List<PowerOutages> parziale) {

		int conta = 0;

		for (PowerOutages p : parziale) {
			conta += p.getNumeroPersone();
		}
		return conta;
	}

	public boolean verificaValida2(PowerOutages p, PowerOutages l, int y) {

		int orarioTotaleL = 0;

		//for (PowerOutages c : parziale) {
			orarioTotaleL += Duration.between(l.getDataInizio(), l.getDataFine()).toSecondsPart()/ 3600+
					+ Duration.between(l.getDataInizio(), l.getDataFine()).toMinutesPart() / 60
					+ Duration.between(l.getDataInizio(), l.getDataFine()).toHoursPart();
		
		//}

		
		int orarioP = Duration.between(p.getDataInizio(), p.getDataFine()).toSecondsPart() / 3600
				+ Duration.between(p.getDataInizio(), p.getDataFine()).toMinutesPart() / 60
				+ Duration.between(p.getDataInizio(), p.getDataFine()).toHoursPart();

		System.out.println(orarioTotaleL + orarioP);
		if (orarioTotaleL + orarioP < y)
			return true;

		return false;
	}

}
