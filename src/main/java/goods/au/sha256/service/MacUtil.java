package goods.au.sha256.service;

import goods.au.sha256.common.key;
import org.apache.commons.codec.binary.Hex;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class MacUtil {
    public static String makeHmacSHA256(String data, String secureKey) throws NoSuchAlgorithmException, InvalidKeyException {
        Mac mac = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKeySpec = new SecretKeySpec(secureKey.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
        mac.init(secretKeySpec);
        byte[] hashBytes = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));
        return Hex.encodeHexString(hashBytes);
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException {
        // app_id + "|" + app_trans_id + "|" + mac key
        String appId = "";
        String appTransId = "";
        String macKey = key.MAC_KEY;
        String hmacInput = String.join("|", appId, appTransId, macKey);
        String result = makeHmacSHA256(hmacInput, macKey);
        System.out.println("Generated MAC: " + result);
    }
}
