package goods.au.sha256.service;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
public class SignUtil {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    public static String createSign(String secretKey, String mid, BigDecimal amount, LocalDateTime reqDate) {
        final String reqDateStr = reqDate.format(FORMATTER);
        log.info("reqDateStr: {}", reqDateStr);
        String plainText = String.format("||%s||%s||%s||%s||", secretKey, mid, amount, reqDateStr);
        return DigestUtils.sha512Hex(plainText);
    }

    public static void main(String[] args) {
        final String secretKey = "0123456789123456";
        final String mid = "t_KRW_Test";
        final BigDecimal amount = BigDecimal.valueOf(5000);
        final LocalDateTime reqDate = LocalDateTime.now();
        String sign = createSign(secretKey, mid, amount, reqDate);
        log.info("sign : {}", sign);
    }
}