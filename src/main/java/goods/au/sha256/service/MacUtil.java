package goods.au.sha256.service;

import goods.au.sha256.common.key;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Hex;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Slf4j
public class MacUtil {
    public static String makeHmacSHA256(String data, String secureKey) throws NoSuchAlgorithmException, InvalidKeyException {
        Mac mac = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKeySpec = new SecretKeySpec(secureKey.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
        mac.init(secretKeySpec);
        byte[] hashBytes = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));
        return Hex.encodeHexString(hashBytes);
    }

    public boolean verifyZaloPayMac(String data, String receivedMac, String callbackKey) throws NoSuchAlgorithmException, InvalidKeyException {
        String calculatedMac = makeHmacSHA256(data, callbackKey);
        log.info("calculatedMac {}", calculatedMac);
        log.info("receivedMac {}", receivedMac);

        return calculatedMac.equals(receivedMac);
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException {
        // app_id + "|" + app_trans_id + "|" + mac key
        String appId = "";
        String appTransId = "";
        String macKey = key.MAC_KEY;
        String hmacInput = String.join("|", appId, appTransId);
        String result = makeHmacSHA256(hmacInput, macKey);

        System.out.println("Generated MAC: " + result);
    }
}
