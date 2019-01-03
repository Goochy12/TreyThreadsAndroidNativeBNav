var braintree = require('braintree')

var gateway = braintree.connect({
  accessToken: "access_token$sandbox$rrrnpdfp5mpm83zq$c4626caef58f28a29f18fdece41febc7"
});

app.get("/client_token", function (req, res) {
  gateway.clientToken.generate({}, function (err, response) {
    res.send(response.clientToken);
	console.log("" + response.clientToken);
  });
});