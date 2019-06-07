var express = require('express');
var router = express.Router();

var mysqlDB = require('../mysql-db');

// /hospital
/* 동물 병원 리스트 /hospital/list */
router.get('/list', function(req, res, next) {

    if(req.query.search == "") {
        var sql = 'select * from animalhospital;';

        mysqlDB.query(sql, [], function(error, hospital) {
            if(error == null) {
                console.log(hospital);
                res.json({
                    "code" : 200,
                    "result" : hospital
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
    }
    else {
        var sql = 'select * from animalhospital where name REGEXP ? or addressDo REGEXP ? or addressSi REGEXP ? or addressGu REGEXP ? or addressDetail REGEXP ?;';

        mysqlDB.query(sql, [req.query.search, req.query.search, req.query.search, req.query.search, req.query.search], function(error, hospital) {
            if(error == null) {
                console.log(hospital);
                res.json({
                    "code" : 200,
                    "result" : hospital
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
    }
});

/* 동물 병원 예약 /hospital/appointment */
router.post('/appointment', function(req, res, next) {
    var sql = 'insert into appointment(userID, petID, hospitalID, date, time, content) values (?,?,?,?,?,?)';
    mysqlDB.query(sql, [req.body.userID, req.body.petID, req.body.hospitalID, req.body.date, req.body.time, req.body.content], function(error, appointment) {
        if(error == null) {
            console.log(appointment);
            res.json({"result" : "success"});
        }
        else{
            console.log(error);
            res.json({"result" : "failed"});
        }
    });
});


/* 예약 리스트 가져오기 */
router.get('/appointment/list', function(req, res, next) {
    var sql = 'select * from appointment;';
    mysqlDB.query(sql, [], function(error, appointment) {
        if(error == null) {
            console.log(appointment);
            res.json({"result" : "success"});
        }
        else{
            console.log(error);
            res.json({"result" : "failed"});
        }
    });
});


/* 병원마다 예약된 강아지 수 고양이 수 조회 */
router.get('/count', function(req, res, next) {
    var sql = 'select species, count(species) as count from pets natural join appointment where hospitalID=? group by species;';
    mysqlDB.query(sql, [req.query.hospitalID], function(error, count) {
        if(error == null) {
            console.log(count);
            res.json({
                "code" : 200,
                "result" : count
            });
        }
        else {
            console.log(error);
            res.json({
                "code" : 400,
                "result" : "failed"
            });
        }
    });

});

module.exports = router;