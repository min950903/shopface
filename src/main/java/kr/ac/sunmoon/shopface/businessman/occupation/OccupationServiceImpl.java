package kr.ac.sunmoon.shopface.businessman.occupation;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OccupationServiceImpl implements OccupationService {
    private final OccupationMapper occupationMapper;

    @Override
    @Transactional
    public boolean addOccupation(Occupation occupation) {
        boolean isSuccess = false;

        //TODO branchNo 값 수신시 수정
        //if ((occupation.getName() != null && occupation.getName() != "") && (occupation.getBranchNo() != 0))
        if (occupation.getName() != null 
            && occupation.getName() != "") {
            occupationMapper.insert(occupation);

            isSuccess = true;
        }

        return isSuccess;
    }

    @Override
    public List<Occupation> getOccupationList(Occupation occupation) {
            return occupationMapper.selectAll(occupation);
    }

    @Override
    @Transactional
    public boolean editOccupation(Occupation occupation) {
        boolean isSuccess = false;
        if ((occupation.getName() != null && occupation.getName() != "")
            && (occupation.getNo() != 0)) {
            occupationMapper.update(occupation);

            isSuccess = true;
        }

        return isSuccess;
    }

    @Override
    @Transactional
    public boolean removeOccupation(Occupation occupation) {
        boolean isSuccess = false;
        if(occupation.getNo() != 0) {
            occupationMapper.delete(occupation);

            isSuccess = true;
        }

        return isSuccess;
    }
}