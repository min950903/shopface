package kr.ac.sunmoon.shopface.businessman;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BranchMapper {
	public void insert(Branch branch);
	public List<Branch> selectAll(Branch branch);
	public Branch select(int no);	
	public void update(Branch branch);
	public void delete(Branch branch);	
}
