package com.prajcompany.journal.repository;

import com.prajcompany.journal.entity.journalentry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

public interface journalentryrepository extends MongoRepository<journalentry, ObjectId> {
}
