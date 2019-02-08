package com.mbc.mbcmanager.controller;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.mbc.mbcmanager.vo.ResponseVo;
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
import com.mbc.mbcmanager.entity.MoneyTransact;
import com.mbc.mbcmanager.vo.ClientVo;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private MongoTemplate mongoTemplate;

    @CrossOrigin
    @RequestMapping(path = "/list", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseVo<List<MbcClient>> getAllClients() {

        ResponseVo<List<MbcClient>> responseVo = new ResponseVo<>();

        try {
            Query query = new Query();
            query.fields().include("clientName");
            responseVo.setStatus("success");
            responseVo.setResponseObject(mongoTemplate.find(query, MbcClient.class));


        } catch (Exception e) {
            responseVo.setStatus("failed");
            responseVo.setErrorMessage(e.getMessage());

        }
        return responseVo;


    }

    @CrossOrigin
    @RequestMapping(path = "/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseVo<MbcClient> getClient(@PathVariable String id) {

        ResponseVo<MbcClient> responseVo = new ResponseVo<>();

        try {
            MbcClient mbcClient = mongoTemplate.findById(id, MbcClient.class);

            if (mbcClient.getPayments() != null) {
                Map<String, Double> groupedPayments = mbcClient.getPayments().stream().collect(Collectors.groupingBy(MoneyTransact::getVendorName, Collectors.summingDouble(MoneyTransact::getAmount)));
                mbcClient.setGroupedPayments(groupedPayments);
            }

            mbcClient.setPayments(null);

            responseVo.setStatus("success");
            responseVo.setResponseObject(mbcClient);


        } catch (Exception e) {
            responseVo.setStatus("failed");
            responseVo.setErrorMessage(e.getMessage());

        }
        return responseVo;


    }

    @CrossOrigin
    @RequestMapping(path = "/save", method = RequestMethod.POST, consumes = {
            MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseVo saveClient(@RequestBody ClientVo clientVo) {


        ResponseVo responseVo = new ResponseVo();

        try {
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

            responseVo.setStatus("success");


        } catch (Exception e) {
            responseVo.setStatus("failed");
            responseVo.setErrorMessage(e.getMessage());

        }
        return responseVo;


    }

}
