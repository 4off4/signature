package goods.au.sha256.service;

import org.apache.commons.codec.digest.DigestUtils;

public class SignatureUtil {
    public String getSignature(String partnerSecurekey, String partnerMid,
                               String refNo, String amount, String currency) {
        String signatureString = "||" + partnerSecurekey + "||" + partnerMid
                + "||" + refNo + "||" + amount + "||" + currency + "||";
        System.out.println("Signature Input: " + signatureString);
        return DigestUtils.sha256Hex(signatureString);
    }

    public static void main(String[] args) {
        SignatureUtil util = new SignatureUtil();
        String result = util.getSignature("securekey", "MID001", "REF001", "10000", "KRW");
        System.out.println("SHA256: " + result);
    }
}