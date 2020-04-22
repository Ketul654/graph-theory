package com.graph;

import java.util.Arrays;

/**
 * Program to solve https://www.geeksforgeeks.org/construct-binary-palindrome-by-repeated-appending-and-trimming/
 */
public class BinaryPalindrome {

    public static void main(String[] args) {
        int n = 50000;
        int k = 9;

        int [] palindrome = new int[n];
        int [] key = new int[k];

        palindrome[0] = 1;
        key[0] = 1;
        palindrome[n-1] = 1;

        for(int i=0; i<n;i=i+k) {
            palindrome[i] = 1;
        }

        for (int i=n-1; i>0; i = i-k) {
            palindrome[i] = 1;
            if(i<k)
                key[i] = 1;
        }

        boolean isPalindrome = true;
        for (int i=0, j=n-1; i<j; i++, j--) {
            if(palindrome[i] != palindrome[j]) {
                isPalindrome = false;
                break;
            }
        }

        System.out.println("Is Palindrome ? : " + isPalindrome);
        System.out.println("Key ==> " + Arrays.toString(key));
        System.out.println("Palindrome ==> " + Arrays.toString(palindrome));
    }
}
