package kr.ac.sunmoon.shopface.businessman.occupation;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Alias("occupation")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Occupation {
    private int no;
    private int branchNo;
    private String name;
    private String color;
}
