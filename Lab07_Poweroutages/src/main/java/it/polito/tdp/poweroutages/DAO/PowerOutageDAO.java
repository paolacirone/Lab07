package it.polito.tdp.poweroutages.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import it.polito.tdp.poweroutages.model.Nerc;
import it.polito.tdp.poweroutages.model.PowerOutages;

public class PowerOutageDAO {
	
	TreeMap<Integer, Nerc> nerc = new TreeMap<Integer, Nerc>();

	public List<Nerc> getNercList() {
		
		

		String sql = "SELECT id, value FROM nerc";
		List<Nerc> nercList = new ArrayList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Nerc n = new Nerc(res.getInt("id"), res.getString("value"));
				nercList.add(n);
				nerc.put(res.getInt("id"), n);
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return nercList;
	}

	public List<PowerOutages> getAllBlackoutAnni(int x, Nerc p) {

		final String sql = "SELECT id, nerc_id, customers_affected, date_event_began, date_event_finished FROM poweroutages WHERE nerc_id= ? AND YEAR(date_event_began)  < ?";

		List<PowerOutages> result = new ArrayList<PowerOutages>();
		
		for(Nerc n: nerc.values()) {
			if(p.getValue().compareTo(n.getValue())==0)
				p.setId(n.getId());
		}

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, p.getId());
			st.setInt(2, 2000+x+1);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				//Nerc c = new Nerc(res.getInt("id"));
				PowerOutages n = new PowerOutages(res.getInt("id"), p, res.getInt("customers_affected"),
						res.getTimestamp("date_event_began").toLocalDateTime(),
						res.getTimestamp("date_event_finished").toLocalDateTime());
				result.add(n);
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return result;

	}

}
