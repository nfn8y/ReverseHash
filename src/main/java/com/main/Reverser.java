package com.main;

import com.exception.ImproperHashException;
import com.util.Constants;

/**
 * @author Yash
 */
public class Reverser {

	/**
	 * @author Yash
	 * @param hash
	 * @return deciphered text
	 * @throws ImproperHashException 
	 * @purpose Takes the value of the hash generated by the Hasher for a distinct string and 
	 * returns the String that might have been encrypted using the Hasher
	 */
	public String reverseHash(long hash) throws ImproperHashException {
		/*
		 * This method should convert the hash back into the 
		 * original string that might have been ciphered into it
		 */
		boolean unhashedProperly = false;
		StringBuilder outputString=new StringBuilder("");
		if(hash>Constants.MIN_DECIPHER_VAL) {
			//The deciphering will take place only if the value is greater than MIN
			//because otherwise the key will not be proper
			unhashLoop: while(hash != Constants.MIN_DECIPHER_VAL) {
				//At one point the value has to become MIN if the code is 
				//generated using the Hasher, hence loop breaks at hash == MIN 
				outputString.append(Constants.KEY_LETTER_STORE.charAt((int)((hash)%Constants.BASE_MULTIPLIER)));
				if((hash/=37) < Constants.MIN_DECIPHER_VAL){
					unhashedProperly=false;break unhashLoop;
				}
				unhashedProperly=true;
			}
		} 
		// If at any iteration, the value becomes lesser than MIN
		// The program will throw an ImproperHashException and exit
		if(unhashedProperly) {
			return outputString.reverse().toString();
		}
		else {
			throw new ImproperHashException("The hash is not recognizable");
		}
	}
}
