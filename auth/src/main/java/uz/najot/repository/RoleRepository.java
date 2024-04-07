package uz.najot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.najot.entity.Roles;
import uz.najot.enums.RoleName;

/**
 * @description: TODO
 * @date: 26 March 2024 $
 * @time: 7:54 PM 45 $
 * @author: Qudratjon Komilov
 */
@Repository
public interface RoleRepository extends JpaRepository<Roles, RoleName> {
}
