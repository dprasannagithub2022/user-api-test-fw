package com.apex.samples.test.Kirti;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Specifictargetnumber {
	public static int[] twoSum(int[] nums,int target)
	{
		Map<Integer,Integer> numMap= new HashMap<>();
		
		for(int i=0;i< nums.length;i++)
		{
			numMap.put(nums[i], i);
		}
		for(int i= 0;i<nums.length;i++) {
			int numToFind=target-nums[i];
			
		if(numMap.containsKey(numToFind) && numMap.get(numToFind) !=i) {
			
		return new  int[] {i,numMap.get(numToFind) };
		}
	}
		throw new IllegalArgumentException("No pair is found");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
       int[] nums = {2,7,11,15};
       int target =18;
       System.out.println("Using HashMap" + Arrays.toString(twoSum(nums,target)));
	}


}
