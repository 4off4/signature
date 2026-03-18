package goods.au.sha256.service;

import goods.au.sha256.common.Key;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Hex;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Slf4j
public class MacUtil {

    public static String makeAuthenticateZaloPayMac(String appTransId, String appUser, BigDecimal amount, String appTime, String embedData, String item, String secretKey) throws NoSuchAlgorithmException, InvalidKeyException {
        // mac: HmacSHA256 (app_id | app_trans_id | app_user | amount | app_time | embedData | item )
        log.info("mac 생성 [appId:{}][appTransId:{}][appUser:{}][amount:{}][appTime:{}][embedData:{}][item:{}]",
                Key.APPID_KEY, appTransId, appUser, amount, appTime, embedData, item);

        String data = String.join("|"
                , Key.APPID_KEY
                , appTransId
                , appUser
                , String.valueOf(amount.longValue())
                , String.valueOf(appTime)
                , embedData
                , item
        );

        return makeHmacSHA256(data, secretKey);
    }

    public static boolean verifyZaloPayMac(String data, String secretKey,String receivedMac) throws NoSuchAlgorithmException, InvalidKeyException {
        String calculatedMac = makeHmacSHA256(data, secretKey);
        log.info("calculatedMac {}", calculatedMac);
        log.info("receivedMac {}", receivedMac);

        return calculatedMac.equals(receivedMac);
    }

    public static String makeHmacSHA256(String data, String secureKey) throws NoSuchAlgorithmException, InvalidKeyException {
        Mac mac = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKeySpec = new SecretKeySpec(secureKey.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
        mac.init(secretKeySpec);
        byte[] hashBytes = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));
        return Hex.encodeHexString(hashBytes);
    }

    public static void main(String[] args) {
        String dataFromServer = "";
        String receivedMac = "";
        String secretKey = Key.CALLBACK_KEY;

        try {
            System.out.println("=== MAC 검증 시작 ===");
            boolean isValid = verifyZaloPayMac(dataFromServer, secretKey, receivedMac);
            System.out.println("결과: " + (isValid ? "✅ 검증 성공" : "❌ 검증 실패"));
            System.out.println("=====================");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
