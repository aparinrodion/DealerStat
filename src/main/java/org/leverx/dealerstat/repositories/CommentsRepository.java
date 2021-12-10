package org.leverx.dealerstat.repositories;

import org.leverx.dealerstat.model.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentsRepository extends CrudRepository<Comment, Integer> {

    List<Comment> getAllByTraderIdAndApprovedIsTrue(Integer traderId);
}
