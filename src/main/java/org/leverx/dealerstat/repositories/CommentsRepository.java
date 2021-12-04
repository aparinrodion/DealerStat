package org.leverx.dealerstat.repositories;

import org.leverx.dealerstat.models.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentsRepository extends CrudRepository<Comment, Integer> {
}
