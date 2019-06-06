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

/* 동물 병원 예약 */
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





module.exports = router;