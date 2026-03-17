package goods.au.sha256.common;

import org.apache.commons.codec.binary.Base64;

public class authorization {
    public static String getAuthorization(String secureKey){
        String authHash = "Basic ";
        String account = secureKey+":";
        byte[] accountBase64Byte = Base64.encodeBase64(account.getBytes());

        return authHash + new String(accountBase64Byte);
    }

    public static void main(String[] args) {
        System.out.printf("Auth : " + getAuthorization(key.AUTH_KEY));
    }
}
