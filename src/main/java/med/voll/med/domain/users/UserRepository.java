/**
 * @author :Gustavo
 * Date :04/04/2023
 * Time :17:13
 * Project Name :med
 **/
package med.voll.med.domain.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Long> {
    UserDetails findByLogin(String login);
}
