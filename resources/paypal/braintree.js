var braintree = require('braintree')
var express = require('express')
var app = express()

var gateway = braintree.connect({
  accessToken: "access_token$sandbox$rrrnpdfp5mpm83zq$c4626caef58f28a29f18fdece41febc7"
});

app.listen(3000);


app.get("/client_token", function (req, res) {
  gateway.clientToken.generate({}, function (err, response) {
    res.send(response.clientToken);
  });
});

