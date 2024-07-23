package com.prajcompany.journal.repository;

import com.prajcompany.journal.entity.journalentry;
import com.prajcompany.journal.entity.user;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface userrepository extends MongoRepository<user, ObjectId> {
    user findByUsername(String username);
    boolean deleteByUsername(String username);
}

