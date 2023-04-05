/**
 * @author :Gustavo
 * Date :04/04/2023
 * Time :19:06
 * Project Name :med
 **/
package med.voll.med.infra.security;

import jakarta.validation.Valid;
import med.voll.med.domain.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity Login(@RequestBody @Valid AuthenticationData data) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var authentication = authenticationManager.authenticate(authenticationToken);

        var tokenJWT = tokenService.generateToken((User) authentication.getPrincipal());
        return ResponseEntity.ok(new JWTTokenData(tokenJWT));
    }
}
