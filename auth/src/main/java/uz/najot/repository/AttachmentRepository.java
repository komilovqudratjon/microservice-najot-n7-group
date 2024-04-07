package uz.najot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.najot.entity.Attachment;

import java.util.List;

/**
 * @description: TODO
 * @date: 26 March 2024 $
 * @time: 7:54 PM 45 $
 * @author: Qudratjon Komilov
 */
@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, Long> {
    List<Attachment> findAllByUsersUsername(String username);
}
