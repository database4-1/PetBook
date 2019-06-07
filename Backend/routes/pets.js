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
            res.json({
                "code" : 200,
                "result" : "success"
            });
        }
        else{
            console.log(error);
            res.json({
                "code" : 400,
                "result" : "failed"
            });
        }
    });
});

/* 사용자의 애완동물 불러오기 /pets/list */
router.get('/list', function(req, res, next) {

    var sql = 'select * from pets where userID=?;';

    mysqlDB.query(sql, [req.query.userID], function(error, pets) {
        if(error == null) {
            console.log(pets);
            res.json({
                "code" : 200,
                "result" : pets
            });
        }
        else{
            console.log(error);
            res.json({
                "code" : 400,
                "result" : "failed"
            });
        }
    });
    
});


/* 동물 종의 평균 병원 비용 */
router.get('/avgcost', function(req, res, next) {
    var sql = 'select speciesOfSpecies, age, Round(weight) as weight, avg(avgCost) as avgCost from Pets where speciesOfSpecies=? group by speciesOfSpecies, age, Round(weight);';
    mysqlDB.query(sql, [req.query.species], function(error, result) {
        if(error == null) {
            console.log(result);
            res.json({
                "code" : 200,
                "result" : result
            });
        }
        else{
            console.log(error);
            res.json({
                "code" : 400,
                "result" : "failed"
            });
        }
    })
})





module.exports = router;