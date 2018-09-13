package com.mbc.mbcmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mbc.mbcmanager.entity.MbcClient;
import com.mbc.mbcmanager.vo.ClientVo;

@RestController
@RequestMapping("/client")
public class ClientController {

	@Autowired
	private MongoTemplate mongoTemplate;

	@CrossOrigin
	@RequestMapping(path = "/", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public List<MbcClient> getAllClients() {

		Query query = new Query();
		query.fields().include("clientName");

		return mongoTemplate.find(query, MbcClient.class);

	}

	@CrossOrigin
	@RequestMapping(path = "/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public MbcClient getClient(@PathVariable String id) {

		return mongoTemplate.findById(id, MbcClient.class);

	}

	@CrossOrigin
	@RequestMapping(path = "/save", method = RequestMethod.POST, consumes = {
			MediaType.APPLICATION_JSON_UTF8_VALUE }, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public void saveClient(@RequestBody ClientVo clientVo) {

		MbcClient mbcClient = null;

		if (StringUtils.isEmpty(clientVo.getClientId())) {
			mbcClient = new MbcClient();

		} else {
			mbcClient = mongoTemplate.findById(clientVo.getClientId(), MbcClient.class);
		}

		mbcClient.setClientId(clientVo.getClientId());
		mbcClient.setClientName(clientVo.getClientName());
		mbcClient.setSiteDescription(clientVo.getSiteDescription());

		mongoTemplate.save(mbcClient);

	}

}
