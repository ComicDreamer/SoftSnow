package cuculi.common;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtils {
    //设置私有密钥
    private static final String SECRET_KEY = "your-secret-key";
    //设置有效时间
    private static final long EXPIRATION_TIME = 864_000_000; // 10 days

    /**
     * 生成Token
     * @param userId
     * @return
     */
    public static String generateToken(Long userId) {
        Date now = new Date();
        //设置Token过期时间
        Date expiration = new Date(now.getTime() + EXPIRATION_TIME);

        return Jwts.builder()
                .setSubject(String.valueOf(userId))
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    /**
     * 从Token中获取用户id
     * @param token
     * @return
     */
    public static Long getUserIdFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();

        return Long.valueOf(claims.getSubject());
    }

    /**
     * 验证Token是否有效
     * @param token
     * @return
     */
    public static boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

