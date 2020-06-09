/**
 * KMP算法，返回字符串匹配的第一个子串的首字符下标，若匹配失败则返回-1
 */
package com.demo.algorithm;

import java.util.Arrays;

public class KMP {
	public static void main(String[] args) {
		String str = "ABCDABABAABCCA";
		String pattern  = "ABCAABC";
		KMP_ kmp = new KMP_();
		int[] next = kmp.getNext(pattern);
		System.out.println(Arrays.toString(next));
		System.out.println(kmp.match(str,pattern,next));
	}
	
}

class KMP_{
	int[] getNext(String str) {
		int len = str.length();
		int[] next = new int[len];
		int j = 0;
		for(int i = 1; i < len; i++) {
			while(j > 0 && str.charAt(i) != str.charAt(j)) {
				j = next[j];
			}
			if(str.charAt(i) == str.charAt(j)) {
				next[i] = ++j;
			}
		}
		return next;
	}
	
	int match(String str, String pattern, int[] next) {
		int i = 0, j = 0;
		for(;i < str.length(); i++) {
			while(j > 0 && str.charAt(i) != pattern.charAt(j)) {
				j = next[j];
			}
			if(str.charAt(i) == pattern.charAt(j)) {
				if(++j == next.length) {
					return i - j + 1;
				}
			}
		}
		return -1;
	}
}
