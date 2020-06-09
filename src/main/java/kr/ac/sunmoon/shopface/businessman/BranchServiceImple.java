package kr.ac.sunmoon.shopface.businessman;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j 
@Service
public class BranchServiceImple implements BranchService {
	@Autowired
	private BranchMapper branchMapper;

	/*
	 * 지점 등록
	 * */
	@Override
	public boolean addBranch(Branch branch) {
		//1. 입력 값이 존재하는가?
		log.info("+++++++++++++branchService - addBranch - start");
		try {
			System.out.println(branch);
			if (branch.getBusinessmanId() != null
					&& !"".equals(branch.getBusinessmanId())
					&& branch.getName() != null
					&& !"".equals(branch.getName())
					&& branch.getPhone() != null
					&& !"".equals(branch.getPhone())
					&& branch.getAddress() != null
					&& !"".equals(branch.getAddress())) {
				//2. 존재하면 정보 등록
				log.info("--------------------------------if문 통과---------------------------------");
				this.branchMapper.insert(branch);
				log.info("--------------------------------Mapper통과---------------------------------");
				return true;
			}
		} catch(Exception e) {
			//3. 아니면 false갑 반환
			e.getStackTrace();
			log.info("-----------------실패1----------------");
			return false;
		}
		log.info("-----------------실패2----------------");
		return false;
	}

	/*
	 * 지점 목록 조회
	 * */
	@Override
	public List<Branch> getBranch(Branch branch) {
		//지점 목록 조회한다
		List<Branch> branches = new ArrayList<Branch>();
		try {
			//1. 입력 값 조회 - 사업자 id, 승인 여부 
			if (branch.getBusinessmanId() != null
					&& !"".equals(branch.getBusinessmanId())
					&& !"".equals(branch.getApprovalStatus())) {
				branches = this.branchMapper.select(branch);	
				return branches;				
			}
			
			return null;
		} catch (Exception e) {
			//2. 조회 중 오류 발생 시 널값 반환
			return null;
		}
	}

	/*
	 * 지점 수정
	 * */
	@Override
	public boolean editBranch(Branch branch) {
		//1. 입력값 검증
		try {
			if (branch.getNo() != 0
					&& branch.getBusinessmanId() != null
					&& !"".equals(branch.getBusinessmanId())
					&& branch.getName() != null
					&& !"".equals(branch.getName())
					&& branch.getPhone() != null
					&& !"".equals(branch.getPhone())
					&& branch.getAddress() != null
					&& !"".equals(branch.getAddress())) {
				//2. 존재 시 지점 정보 수정 후 true값 밪환
				this.branchMapper.update(branch);
				return true;
			}
			
		} catch(Exception e) {
			//3. 존재 안할 시 false값 반환
			return false;
		}
		return false;
	}

	/*
	 * 지점 삭제
	 * */
	@Override
	public boolean removeBranch(Branch branch) {
		//1. 입력 값 검증
		try {
			if (branch.getNo() != 0
					&& branch.getBusinessmanId() != null
					&& !"".equals(branch.getBusinessmanId())
					&& branch.getName() != null
					&& !"".equals(branch.getName())
					&& branch.getPhone() != null
					&& !"".equals(branch.getPhone())
					&& branch.getRegisterDate() != null
					&& branch.getAddress() != null
					&& !"".equals(branch.getAddress())
					&& branch.getBusinessLicensePath() != null
					&& !"".equals(branch.getBusinessLicensePath())
					&& !"".equals(branch.getState())
					&& !"".equals(branch.getApprovalStatus())){
				//2. 존재 시  연관된 고용 있는지 확인 
				if (true) {
					//3. 연관된 고용 정보 없으면 삭제
					this.branchMapper.delete(branch);
					return true;					
				} 
				//4. 연관된 고용 정보 있으면 비활성화 
				this.branchMapper.update(branch);
				return true;
			}
		} catch(Exception e) {
			//3. 존재 안할 시 false값 반환
			return false;
		}
		return false;
	}
}
