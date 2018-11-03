package nl.workshop2.dao;

import java.util.ArrayList;
import nl.workshop2.domain.Bestelregel;

/**
 *
 * @author Vosjes
 */
public interface BestelregelDAO {

    /*
    Alle ISUD (INSERT, SELECT, UPDATE, DELETE) methodes die door MySQLBestelregelDAO geïmplementeerd worden
    */
    
    public boolean insertBestelregel(Bestelregel bestelregel);
    public Bestelregel selectBestelregel (int id);
    public ArrayList<Bestelregel> selectBestelregels();
    public ArrayList<Bestelregel> selectBestelregels(int bestellingId);
    public boolean updateBestelregel (Bestelregel bestelregel);
    public boolean deleteBestelregel (int id);
}
