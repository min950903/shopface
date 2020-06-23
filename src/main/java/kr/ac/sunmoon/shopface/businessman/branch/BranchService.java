package kr.ac.sunmoon.shopface.businessman.branch;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface BranchService {
	public boolean addBranch(Branch branch);
	public List<Branch> getBranchList(Branch branch);
	public Branch getBranch(int no);
	public boolean editBranch(Branch branch, MultipartFile licenseImage);
	public boolean removeBranch(Branch branch);
}
