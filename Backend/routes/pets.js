var express = require('express');
var router = express.Router();

var mysqlDB = require('../mysql-db');

// /pets


/* 애완동물 등록 /pets/register */
router.post('/register', function(req, res, next) {
    var sql = 'insert into pets(userID, name, species, gender, age, weight, speciesOfSpecies, avgCost) values (?,?,?,?,?,?,?,?);';
    mysqlDB.query(sql, [req.body.userID, req.body.name, req.body.species, req.body.gender, req.body.age, req.body.weight, req.body.speciesOfSpecies, req.body.avgCost], function(error, info) {
        if(error == null) {
            console.log(info);
            res.json({"result" : "success"});
        }
        else{
            console.log(error);
            res.json({"result" : "failed"});
        }
    });
});



module.exports = router;