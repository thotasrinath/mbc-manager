package com.mbc.mbcmanager.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mbc.mbcmanager.entity.MbcClient;

public interface ClientRepo extends MongoRepository<MbcClient, String> {

}
