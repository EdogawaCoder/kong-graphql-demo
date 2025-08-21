package com.edogawa.users.services;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

// This class is responsible for generating unique sequence numbers for user IDs.
@Service
public class UserSequenceGeneratorService {

	private final MongoOperations mongoOperations;
	
	public UserSequenceGeneratorService(MongoOperations mongoOperations) {
		this.mongoOperations = mongoOperations;
	}
	
	public long nextValue(String sequenceName) {
		var query =  new Query(Criteria.where("id").is(sequenceName));
		var update = new Update().inc("seq", 1);
		
		var counter = mongoOperations.findAndModify(
				query, 
				update, 
				options().returnNew(true).upsert(true),
				UserDbSequence.class);
		
		return (counter != null) ? counter.getSeq() : 1L;
	}
}
