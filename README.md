# MBC-Manager

API's List

    -Get Clientlist
    http://<ip>:<port>/client/list --GET
    
    -Get client by Id
    http://<ip>:<port>/client/<clientId> --GET
    
    -Add or edit income
    http://<ip>:<port>/account/addincome/<clientid> --post
    payload:
    {
      "transactionId":"<transactionId>",  --Only on update
      "transactionType":"Cheque 12345",
      "transactionDate":"2018-04-23",
      "amount":10000
    }
    
    -Delete income
    http://<ip>:<port>/account/delincome/<clientid> --post
    payload:
    {
     "transactionId":"<transactionId>"
    }
    
    -Add or edit expense
    http://<ip>:<port>/account/addexpense/<clientid> --post
    payload:
    {
      "transactionId":"<transactionId>",   --Only on update
      "transactionType":"Cheque vdfvdf",
      "transactionDate":"2018-04-23",
      "amount":1000000,
      "vendorName":"Srinath T"
    }
    
    -delete expense
    http://<ip>:<port>/account/delexpense/<clientid> --post
    payload:
    {
     "transactionId":"<transactionId>"
    }
    
    -Create Client
    http://<ip>:<port>/client/save --post
    payload:
    {
     "clientName":"<Client Name>",
     "siteDescription":"<Client description>"
    }
    
    -Get expenses for client by vendorname
    http://<ip>:<port>/account/expensesbyvendor/<clientid>/<vendorname> --GET
    
