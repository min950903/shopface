package kr.ac.sunmoon.shopface.businessman;

import java.util.List;

public interface BranchService {
	public boolean addBranch(Branch branch);
	public List<Branch> getBranchList(Branch branch);
	public Branch getBranch(int no);
	public boolean editBranch(Branch branch);
	public boolean removeBranch(Branch branch);
}
