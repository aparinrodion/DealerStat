package org.leverx.dealerstat.repositories;

import com.sun.mail.util.LineInputStream;
import org.leverx.dealerstat.models.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentsRepository extends CrudRepository<Comment, Integer> {
    List<Comment> getAllByApproved(boolean approved);
}
