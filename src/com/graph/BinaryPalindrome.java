package com.graph;

import java.util.Arrays;

public class BinaryPalindrome {

    public static void main(String[] args) {
        int n = 5005;
        int k = 20;

        int[] palindrome = new int[n];
        int [] key = new int[k];
        int last = n%k;

        palindrome[0] = 1;
        key[0] = 1;
        palindrome[n-1] = 1;

        for(int i=k; i<n;i=i+k) {
            palindrome[i] = 1;
        }

        if(last == 0) {
            key[k-1] = 1;
            for(int i=k-1; i<n; i = i+k){
                palindrome[i] = 1;
            }
        }

        if(last>0) {
            key[last - 1] = 1;
            for(int i=last-1; i<n; i = i+k){
                palindrome[i] = 1;
            }
        }

        boolean isPalindrome = true;
        for (int i=0, j=n-1; i<j; i++, j--) {
            if(palindrome[i] != palindrome[j]) {
                isPalindrome = false;
                break;
            }
        }

        System.out.println(isPalindrome);

        System.out.println(last);
        System.out.println(Arrays.toString(key));

        System.out.println(Arrays.toString(palindrome));
    }
}
