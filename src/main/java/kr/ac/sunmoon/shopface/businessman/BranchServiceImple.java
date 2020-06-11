package kr.ac.sunmoon.shopface.businessman;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.sunmoon.shopface.employ.Employ;
import kr.ac.sunmoon.shopface.employ.EmployMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j 
@RequiredArgsConstructor
@Service
public class BranchServiceImple implements BranchService {
	private final BranchMapper branchMapper;
	private final EmployMapper employMapper;

	/*
	 * 지점 등록
	 * */
	@Override
	public boolean addBranch(Branch branch) {
		//1. 입력 값이 존재하는가?
		try {
			System.out.println(branch);
			if (branch.getBusinessmanId() != null
					&& !"".equals(branch.getBusinessmanId())
					&& branch.getName() != null
					&& !"".equals(branch.getName())
					&& branch.getPhone() != null
					&& !"".equals(branch.getPhone())
					&& branch.getAddress() != null
					&& !"".equals(branch.getAddress())
					&& branch.getDetailAddress() != null
					&& !"".equals(branch.getDetailAddress())
					&& branch.getZipNo() != null
					&& !"".equals(branch.getZipNo())) {
				//2. 존재하면 정보 등록
				this.branchMapper.insert(branch);
				return true;
			}
		} catch(Exception e) {
			//3. 아니면 false갑 반환
			e.getStackTrace();
			return false;
		}
		return false;
	}

	/*
	 * 지점 목록 조회
	 * */
	@Override
	public List<Branch> getBranchList(Branch branch) {
		//지점 목록 조회한다
		List<Branch> branches = new ArrayList<Branch>();
		try {
			//1. 입력 값 조회 - 사업자 id, 승인 여부 
			if (branch.getBusinessmanId() != null
					&& !"".equals(branch.getBusinessmanId())
					&& !"".equals(branch.getApprovalStatus())) {
				branches = this.branchMapper.selectAll(branch);	
				return branches;				
			}
			
			return null;
		} catch (Exception e) {
			//2. 조회 중 오류 발생 시 널값 반환
			return null;
		}
	}
	
	/*
	 * 지점 조회
	 * */
	@Override
	public Branch getBranch(int no) {
		//지점 조회한다
		Branch branch = new Branch();
		try {
			//1. 입력 값 조회(일련번호 0 이상)
			if (no > 0) {
				branch = this.branchMapper.select(no);	
				return branch;				
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
					&& !"".equals(branch.getName())) {
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
		try{
			//1. 입력 값 검증
			if (branch.getNo() > 0
					&& branch.getBusinessmanId() != null
					&& !"".equals(branch.getBusinessmanId())){
				//2. 입력 값 검증 완료 시 지점 조회
				Branch result = this.branchMapper.select(branch.getNo());
				//3. 사업자 본인이 등록한 지점인지 확인 (전달받은 사업자id와 조회한 지점의 사업자id가 같은지 비교)
				if (result != null) { //지점 조회 시 결과가 있는지 검사
					String resultId = result.getBusinessmanId();
					if (resultId.equals(branch.getBusinessmanId())) {
						//4. 같을 시 고용 목록 조회 (지점의 일련번호로 조회)
						//5. 연관된 고용관계가 있는지 확인(지점 일련 번호와 고용정보의 지점 일련번호 일치 여부 확인)
						Employ employ = new Employ();
						employ.setBranchNo(branch.getNo());
						
						List<Employ> employs = this.employMapper.selectAll(employ);
						if (employs != null && employs.size() >= 1) {
							//6. 연결된 고용 정보 미존재 시 삭제
							this.branchMapper.update(branch);
							return true;
						} else {
							//7. 연결된 고용 정보 존재 시 삭제하지 않고 비활성화
							this.branchMapper.delete(branch);
							return true;
						} 
					} return false;
				} return false;
			} return false;
		} catch(Exception e) {
			return false;
		}
	}
}
