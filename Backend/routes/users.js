var express = require('express');
var mysql = require('mysql');
var router = express.Router();

var connection = mysql.createConnection({
  host : 'localhost',
  port : 3306,
  user : 'root',
  password : '51168496',
  database : 'petbook'
})

connection.connect(function(err) { 
  if (err) { 
    console.error('mysql connection error'); 
    console.error(err); throw err; 
  }else{ 
    console.log("연결에 성공하였습니다."); 
  } 
});


/* GET users listing. */
router.get('/', function(req, res, next) {
  res.send('respond with a resource');
});

router.post('/register/user', function(req, res, next) {
  connection.query('insert into user(id, pw, name, phone) values (?,?,?,?);', [req.body.id, req.body.pw, req.body.name, req.body.phone], function(error, info) {
    if(error == null) {
      res.send("register user success");
    }
    else{
      res.status(503).json(error);
    }
  })
});

router.post('/register/pets', function(req, res, next) {
  connection.query('insert into pets(ownerID, name, species, gender, age, weight, speciesOfSpecies) values (?,?,?,?,?,?,?);', [req.body.ownerID, req.body.name, req.body.species, req.body.gender, req.body.age, req.body.weight, req.body.speciesOfSpecies], function(error, info) {
    if(error == null) {
      res.send("register pets success");
    }
    else{
      res.status(503).json(error);
    }
  })
});



module.exports = router;
