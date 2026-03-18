package goods.au.sha256.service;

import goods.au.sha256.common.Key;
import org.apache.commons.codec.binary.Base64;

public class authorizationUtil {
    public static String getAuthorization(String secureKey){
        String authHash = "Basic ";
        String account = secureKey+":";
        byte[] accountBase64Byte = Base64.encodeBase64(account.getBytes());

        return authHash + new String(accountBase64Byte);
    }

    public static void main(String[] args) {
        System.out.printf("Auth : " + getAuthorization(Key.AUTH_KEY));
    }
}
