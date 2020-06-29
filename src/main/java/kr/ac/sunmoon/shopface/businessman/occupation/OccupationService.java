package kr.ac.sunmoon.shopface.businessman.occupation;

import java.util.List;

import org.springframework.stereotype.Service;

public interface OccupationService {
    public boolean addOccupation(Occupation occupation);
    public List<Occupation> getOccupationList(Occupation occupation);
    public boolean editOccupation(Occupation occupation);
    public boolean removeOccupation(Occupation occupation);
    
}
